package com.erp.batch.controllers.batch.trxnhistory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:batch-job.properties")
public class TrxnHistoryBatchConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;

    public TrxnHistoryBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
        this.jobs = jobs;
        this.steps = steps;
    }

    @Value("${batch.processing.batch.size}")
    private Integer batchSize;

    @Value("${batch.processing.batch.offset}")
    private Integer batchOffset;

    @Value("${batch.processing.batch.max.size}")
    private Integer batchMaxSize;


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
