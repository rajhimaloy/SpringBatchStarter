package com.erp.batch.services.impl;

import com.erp.batch.domain.entities.TrxnHistory;
import com.erp.batch.domain.repositories.TrxnHistoryRepository;
import com.erp.batch.services.iServices.iTrxnHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrxnHistoryService implements iTrxnHistoryService {

    private TrxnHistoryRepository repository;

    public TrxnHistoryService(TrxnHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrxnHistory add(TrxnHistory aTrxnHistory) {
        return repository.save(aTrxnHistory);
    }

    @Override
    public TrxnHistory update(TrxnHistory aTrxnHistory) {
        return repository.save(aTrxnHistory);
    }

    @Override
    public boolean remove(Integer userid) {
        if (repository.existsById(userid)){
            repository.deleteById(userid);
            return true;
        }
        return false;
    }

    @Override
    public Long totalCount() {
        return repository.count();
    }

    @Override
    public TrxnHistory findByUserID(Integer userid) {
        Optional<TrxnHistory> isFound = repository.findById(userid);
        if (isFound.isPresent()) return isFound.get();
        else return null;
    }

    @Override
    public List<TrxnHistory> findAllByUserID(List<Integer> userid) {
        return repository.findAllById(userid);
    }

    @Override
    public List<TrxnHistory> findAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }
}
