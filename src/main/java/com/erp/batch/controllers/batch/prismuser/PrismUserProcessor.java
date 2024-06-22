package com.erp.batch.controllers.batch.prismuser;

import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;

public class PrismUserProcessor implements ItemProcessor<PrismUser, PrismUser> {

    @Override
    public PrismUser process(PrismUser prismUser) throws Exception {
        System.out.println("Prism User ETL Processor:- " + Thread.currentThread().getName());

        if (prismUser.getPkUserID() != null) {
            prismUser.setEventsType("Prism User ETL Process");
            prismUser.setDataProcessDate(new Date(new java.util.Date().getTime()));
        }

        return prismUser;
    }
}
