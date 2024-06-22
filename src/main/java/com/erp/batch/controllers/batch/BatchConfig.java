package com.erp.batch.controllers.batch;

import com.erp.batch.controllers.batch.message.MessageProcessor;
import com.erp.batch.controllers.batch.message.MessageReader;
import com.erp.batch.controllers.batch.message.MessageWriter;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistoryMapper;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistoryProcessor;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistoryWriter;
import com.erp.batch.controllers.batch.tasks.MyTaskOne;
import com.erp.batch.controllers.batch.tasks.MyTaskTwo;
import com.erp.batch.domain.entities.TrxnHistory;
import com.erp.batch.rest.models.Message;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:batch-job.properties")
public class BatchConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;

    public BatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
        this.jobs = jobs;
        this.steps = steps;
    }

    @Value("${batch.processing.batch.size}")
    private Integer batchSize;

    @Value("${batch.processing.batch.offset}")
    private Integer batchOffset;

    @Value("${batch.processing.batch.max.size}")
    private Integer batchMaxSize;

    @Bean("simpleJob")
    public Job simpleJob(){

        TaskExecutor executor = new SimpleAsyncTaskExecutor();
        //int numberOfCore = Runtime.getRuntime().availableProcessors();
        //((SimpleAsyncTaskExecutor)executor).setConcurrencyLimit((numberOfCore / 2) + 1);

        Step one = steps.get("stepOne")
                .<Message, Message>chunk(batchSize)
                .reader(new MessageReader())
                .processor(new MessageProcessor())
                .writer(new MessageWriter())
                //.taskExecutor(new SimpleAsyncTaskExecutor())
                //.throttleLimit(5)
                .build();

        return jobs.get("simpleJob")
                .incrementer(new RunIdIncrementer())
                .start(one)
                .build();
    }

    @Bean("taskletJobSample")
    public Job demoJob(){

        Step one = steps.get("stepOne")
                .tasklet(new MyTaskOne())
                .build();

        Step two = steps.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();

        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(one)
                .next(two)
                .build();
    }

    @Bean("jdbcJobSample")
    public Job sampleJob(DataSource dataSource) throws SQLException {

        ItemReader<TrxnHistory> itemReader = new JdbcCursorItemReaderBuilder<TrxnHistory>()
                .dataSource(dataSource)
                .name("TrxnHistoryReader")
                .sql("SELECT * FROM TrxnHistory")
                .rowMapper(new TrxnHistoryMapper()) //OR Next Line
                //.rowMapper(new BeanPropertyRowMapper(TrxnHistory.class))
                .fetchSize(batchSize)
                //.maxRows(batchSize)
                .build();

        Step one = steps.get("stepOne")
                .<TrxnHistory, TrxnHistory>chunk(batchSize)
                .reader(itemReader)
                .processor(new TrxnHistoryProcessor())
                .writer(new TrxnHistoryWriter())
                .build();

        return jobs.get("sampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(one)
                .end()
                .build();
    }

    @Bean("jdbcMultiStepJobSample")
    public Job multiStepSampleJob(DataSource dataSource) throws SQLException {

        JobFlowBuilder batchJobBuilder = null;

        String entity = TrxnHistory.tableName(TrxnHistory.class);
        int cursor = batchOffset;
        while (batchMaxSize != -1 && cursor < batchMaxSize){

            String query = String.format("SELECT * FROM %s LIMIT %s, %s", entity, cursor,  batchSize);
            System.out.println(query);

            ItemReader<TrxnHistory> itemReader = new JdbcCursorItemReaderBuilder<TrxnHistory>()
                    .dataSource(dataSource)
                    .name(String.format("%s_Reader", entity))
                    .sql(query)
                    .rowMapper(new TrxnHistoryMapper())//OR Next Line
                    //.rowMapper(new BeanPropertyRowMapper(TrxnHistory.class))
                    .fetchSize(batchSize)
                    .build();

            Step batch = steps.get(String.format("batchStep_%s", cursor))
                    .<TrxnHistory, TrxnHistory>chunk(batchSize/2)
                    .reader(itemReader)
                    .processor(new TrxnHistoryProcessor())
                    .writer(new TrxnHistoryWriter())
                    .build();

            if (batchJobBuilder == null){
                batchJobBuilder = jobs.get("multiSampleJob")
                        .incrementer(new RunIdIncrementer())
                        .flow(batch);
            }else {
                batchJobBuilder.next(batch);
            }
            cursor = cursor + batchSize; //Loop-Increment
        }

        return batchJobBuilder.end().build();
    }

    @Bean("jdbcMultiStepPagingJobSample")
    public Job multiStepPagingJob(@Qualifier("pagedTrxnHistoryReader") JdbcPagingItemReader<TrxnHistory> itemReader)
            throws SQLException {
        //
        Step one = steps.get("stepPaging")
                .<TrxnHistory, TrxnHistory>chunk(batchSize)
                .reader(itemReader)
                .processor(new TrxnHistoryProcessor())
                .writer(new TrxnHistoryWriter())
                .build();

        return jobs.get("samplePageJob")
                .incrementer(new RunIdIncrementer())
                .start(one)
                .build();
    }

    @Bean("pagedTrxnHistoryReader")
    @StepScope
    public JdbcPagingItemReader<TrxnHistory> pagingItemReader(DataSource dataSource) {
        //
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        //
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, sex, age, dob, active");
        queryProvider.setFromClause("TrxnHistory");
        //queryProvider.setWhereClause("where id >= " + minValue + " and id < " + maxValue);
        queryProvider.setSortKeys(sortKeys);
        //
        JdbcPagingItemReader<TrxnHistory> reader = new JdbcPagingItemReader<>();
        reader.setName("pagingItemReader");
        reader.setDataSource(dataSource);
        reader.setFetchSize(batchSize);
        reader.setRowMapper(new BeanPropertyRowMapper<>(TrxnHistory.class));
        reader.setQueryProvider(queryProvider);
        return reader;
    }

}
