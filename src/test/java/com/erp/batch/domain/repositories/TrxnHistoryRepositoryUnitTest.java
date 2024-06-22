package com.erp.batch.domain.repositories;

import com.erp.batch.domain.entities.Gender;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistory;
import com.erp.batch.mainapp.config.TestJPAConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestJPAConfig.class})
@Transactional
@TestPropertySource(locations = {"classpath:h2-db.properties"})
public class TrxnHistoryRepositoryUnitTest {

    @Autowired
    TrxnHistoryRepository repository;

    @Test
    public void insert(){
        TrxnHistory trxnHistory = new TrxnHistory("Rajib The Coder", Gender.MALE, 24);
        repository.save(trxnHistory);

        List<TrxnHistory> list = repository.findByName("Rajib The Coder");
        Assert.assertTrue(Objects.nonNull(list));

        if (list != null && list.size() > 0){
            TrxnHistory trxnHistory2 = list.get(0);
            Assert.assertTrue(Objects.equals(trxnHistory.getName(), trxnHistory2.getName()));
        }
    }

    @Test
    public void update(){
        //TODO
    }

    @Test
    public void delete(){
        //TODO
    }

    @Test
    public void count(){
        //
        TrxnHistory trxnHistory = new TrxnHistory("Rajib The Coder", Gender.MALE, 24);
        repository.save(trxnHistory);

        long count = repository.count();
        Assert.assertTrue(count == 1);
    }

    @Test
    public void fetch(){
        //
        repository.save(new TrxnHistory("Rajib The Coder", Gender.MALE, 24));
        repository.save(new TrxnHistory("Raju The Pankha Coder", Gender.MALE, 24));
        repository.save(new TrxnHistory("Sandani The Coder", Gender.MALE, 26));
        //
        Page<TrxnHistory> paged = repository.findAll(PageRequest.of(0
                , 10
                , Sort.by(Sort.Order.asc("name"))));
        paged.get().forEach(trxnHistory -> System.out.println(trxnHistory.getName()));
    }
}