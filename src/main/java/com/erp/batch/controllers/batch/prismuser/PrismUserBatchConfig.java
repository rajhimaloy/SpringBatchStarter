package com.erp.batch.controllers.batch.prismuser;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//@PropertySource("classpath:prism-user-batch-job.properties")
public class PrismUserBatchConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;

    public PrismUserBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
        this.jobs = jobs;
        this.steps = steps;
    }

    @Value("5")
    private Integer batchSize;

    @Value("0")
    private Integer batchOffSet;

    @Value("100")
    private Integer batchMaxSize;

    @Bean("prismUserJob")
    public Job prismUserJob(@Qualifier("TmrMysqlDataSource") DataSource readDataSource, @Qualifier("TmrOracleDataSource") DataSource writeDataSource) throws SQLException {
        ItemReader<PrismUser> prismUserItemReader = new JdbcCursorItemReaderBuilder<PrismUser>()
                .dataSource(readDataSource)
                .name("PrismUser")
                .sql("SELECT pk_user_id, fk_user_id, u_firstname, u_lastname, u_username, u_email, u_contact_number, \n" +
                        "u_password, u_role, u_permissions, u_status, u_token, u_change_password, u_position, \n" +
                        "CASE WHEN _join_date = \"0000-00-00\" THEN null ELSE _join_date END _join_date, \n" +
                        "CASE WHEN _resign_date = \"0000-00-00\" THEN null ELSE _resign_date END _resign_date, \n" +
                        "_is_metro, _is_nonmetro, _using_global_targets, \n" +
                        "_current_home_text, _current_home_lat, _current_home_long, _current_home_fk_id, \n" +
                        "create_date, create_time, create_by, update_date, update_time, update_by \n" +
                        "FROM _users")
                .rowMapper(new PrismUserMapper())
                .fetchSize(batchSize)
                .build();

        Step one = steps.get("prismUserJob-stepOne")
                .<PrismUser, PrismUser>chunk(batchSize)
                .reader(prismUserItemReader)
                .processor(new PrismUserProcessor())
                .writer(new PrismUserWriter(writeDataSource))
                .build();

        return jobs.get("prismUserJob")
                .incrementer(new RunIdIncrementer())
                .start(one)
                .build();
    }
}
