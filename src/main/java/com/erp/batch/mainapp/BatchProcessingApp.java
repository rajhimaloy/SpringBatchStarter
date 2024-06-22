package com.erp.batch.mainapp;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.erp.batch.controllers"
        , "com.erp.batch.services"
        , "com.erp.batch.mainapp.config"
        , "com.erp.batch.domain"})
public class BatchProcessingApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessingApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BatchProcessingApp.class);
    }
}
