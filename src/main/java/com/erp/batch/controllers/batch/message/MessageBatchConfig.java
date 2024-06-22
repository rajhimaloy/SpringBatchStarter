package com.erp.batch.controllers.batch.message;

import com.infoworks.lab.rest.models.Message;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@PropertySource("classpath:batch-job.properties")
public class MessageBatchConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;

    public MessageBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
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

}
