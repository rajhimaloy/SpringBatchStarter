package com.erp.batch.webapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.erp.batch.domain.entities.TrxnHistory;
import com.erp.batch.rest.models.Message;
import com.it.soul.lab.data.simple.SimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("trxnHistoryDatasource")
    public SimpleDataSource<String, TrxnHistory> getTrxnHistoryDatasource(){
        return new SimpleDataSource<>();
    }

    @Bean
    ObjectMapper getMapper(){
        return Message.getJsonSerializer();
    }

}
