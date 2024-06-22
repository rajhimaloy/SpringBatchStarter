package com.erp.batch.controllers.batch.tmrdata;

import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;

public class TmrDataProcessor implements ItemProcessor<TmrData, TmrData> {

    @Override
    public TmrData process(TmrData tmrData) throws Exception {
        System.out.println("TMR Data ETL Processor:- " + Thread.currentThread().getName());

        if (tmrData.getPkDataID() != null) {
            tmrData.setEventsType("TMR Data ETL Process");
            tmrData.setDataProcessDate(new Date(new java.util.Date().getTime()));
        }
        return tmrData;
    }
}
