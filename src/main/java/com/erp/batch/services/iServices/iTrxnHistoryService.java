package com.erp.batch.services.iServices;

import com.erp.batch.domain.entities.TrxnHistory;

import java.util.List;

public interface iTrxnHistoryService {
    TrxnHistory add(TrxnHistory aTrxnHistory);
    TrxnHistory update(TrxnHistory aTrxnHistory);
    boolean remove(Integer userid);
    Long totalCount();
    TrxnHistory findByUserID(Integer userid);
    List<TrxnHistory> findAllByUserID(List<Integer> userid);
    List<TrxnHistory> findAll(Integer page, Integer size);
}
