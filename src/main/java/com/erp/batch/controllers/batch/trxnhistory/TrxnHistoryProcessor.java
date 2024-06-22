package com.erp.batch.controllers.batch.trxnhistory;

import com.erp.batch.domain.entities.TrxnHistory;
import org.springframework.batch.item.ItemProcessor;

public class TrxnHistoryProcessor implements ItemProcessor<TrxnHistory, TrxnHistory> {
    @Override
    public TrxnHistory process(TrxnHistory trxnHistory) throws Exception {
        //System.out.println("TrxnHistoryProcessor: " + Thread.currentThread().getName());
        //System.out.println("Processing: " + trxnHistory.getName());
        return trxnHistory;
    }
}
