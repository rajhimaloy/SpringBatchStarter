package com.erp.batch.domain.repositories;

import com.erp.batch.domain.entities.TrxnHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrxnHistoryRepository extends JpaRepository<TrxnHistory, Integer> {
    List<TrxnHistory> findByName(String name);
    long countByName(String name);

    @Override
    long count();

    @Override
    Page<TrxnHistory> findAll(Pageable pageable);
}
