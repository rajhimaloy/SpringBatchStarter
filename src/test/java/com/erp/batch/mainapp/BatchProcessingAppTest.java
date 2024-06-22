package com.erp.batch.webapp;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.erp.batch.controllers"
        , "com.erp.batch.services"})
public class BatchProcessingAppTest {
    @Test
    public void contextLoads() {
        System.out.println("ApplicationContext Loaded Successfully");
    }
}