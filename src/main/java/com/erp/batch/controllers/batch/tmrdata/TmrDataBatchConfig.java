package com.erp.batch.controllers.batch.tmrdata;

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

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class TmrDataBatchConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;

    public TmrDataBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
        this.jobs = jobs;
        this.steps = steps;
    }

    @Value("${tdata.batch.processing.batch.size}")
    private Integer batchSize;

    @Value("${tdata.batch.processing.batch.offset}")
    private Integer batchOffSet;

    @Value("${tdata.batch.processing.batch.max.size}")
    private Integer batchMaxSize;

    @Bean("tmrDataJob")
    public Job tmrDataPagingJob(@Qualifier("pagedTmrDataReader") JdbcPagingItemReader<TmrData> itemReader, @Qualifier("TmrOracleDataSource") DataSource writeDataSource)
            throws SQLException {
        //
        Step one = steps.get("tmrDataJob-stepPaging")
                .<TmrData, TmrData>chunk(batchSize)
                .reader(itemReader)
                .processor(new TmrDataProcessor())
                .writer(new TmrDataWriter(writeDataSource))
                .build();

        return jobs.get("tmrDataPageJob")
                .incrementer(new RunIdIncrementer())
                .start(one)
                .build();
    }

    @Bean("pagedTmrDataReader")
    @StepScope
    public JdbcPagingItemReader<TmrData> pagingItemReader(@Qualifier("TmrMysqlDataSource") DataSource readDataSource) {
        //Set Field for Order By Clause in Query
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("pk_data_id", Order.ASCENDING);
        //Create Query
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("pk_data_id, data_id, u_market_type, fk_tmo_id, fk_uddokta_id, fk_dh_id, fk_tm_id, " +
                "BEFORE_INSIDE_MEDIA, BEFORE_OUTSIDE_MEDIA, AFTER_INSIDE_MEDIA, AFTER_OUTSIDE_MEDIA, " +
                "lattitude, longitude, _district, _thana, _distance_flag, _distance_from_uddokta, _start_time, _end_time, " +
                " _duration_text, _duration_minutes, _duration_seconds, " +
                "create_date, create_time, create_date_time, create_by, update_date, update_time, update_by ");
        queryProvider.setFromClause(" _datas ");
        //queryProvider.setWhereClause("where pk_data_id >= " + minValue + " and id < " + maxValue);
        //queryProvider.setWhereClause(" create_date BETWEEN '2022-09-01' AND '2022-09-11' ");
        queryProvider.setWhereClause(" create_date = CURDATE() - INTERVAL 1 DAY ");
        queryProvider.setSortKeys(sortKeys);

        //Set Reader
        JdbcPagingItemReader<TmrData> reader = new JdbcPagingItemReader<>();
        reader.setName("pagingItemReader");
        reader.setDataSource(readDataSource);
        reader.setFetchSize(batchSize);
        //reader.setRowMapper(new BeanPropertyRowMapper<>(TmrData.class));  /*Built in Mapper */
        reader.setRowMapper(new TmrDataMapper());
        reader.setQueryProvider(queryProvider);
        return reader;
    }
}
