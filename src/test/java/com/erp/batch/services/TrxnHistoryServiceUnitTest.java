package com.erp.batch.services;

import com.erp.batch.domain.entities.Gender;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistory;
import com.erp.batch.domain.repositories.TrxnHistoryRepository;
import com.erp.batch.webapp.config.TestJPAConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestJPAConfig.class})
public class TrxnHistoryServiceUnitTest {

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    TrxnHistoryRepository repository;

    @InjectMocks
    TrxnHistoryService service;

    @Test
    public void happyPathTest(){
        //Defining Mock Object:
        TrxnHistory aTrxnHistory = new TrxnHistory("Rajib", Gender.MALE, 36);
        when(repository.save(any(TrxnHistory.class))).thenReturn(aTrxnHistory);

        //Call controller to make the save:
        TrxnHistory nTrxnHistory = service.add(aTrxnHistory);

        //Verify:
        assertNotNull(nTrxnHistory);
        assertNotNull(nTrxnHistory.getId());
        assertEquals("Rajib", nTrxnHistory.getName());
    }
}
