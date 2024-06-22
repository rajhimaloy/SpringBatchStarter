package com.erp.batch.controllers.batch.trxnhistory;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class TrxnHistoryWriter implements ItemWriter<TrxnHistory> {
    @Override
    public void write(List<? extends TrxnHistory> list) throws Exception {
        System.out.println("TrxnHistoryWriter: " + Thread.currentThread().getName());
        System.out.println("Chunk Size: " + list.size());
        list.forEach(trxnHistory -> System.out.println(trxnHistory.getName()));
        System.out.println("\n");
    }
}
