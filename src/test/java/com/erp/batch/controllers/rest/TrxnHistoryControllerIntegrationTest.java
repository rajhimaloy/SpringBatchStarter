package com.erp.batch.controllers.rest;

import com.erp.batch.domain.entities.Gender;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistory;
import com.erp.batch.rest.models.ItemCount;
import com.erp.batch.mainapp.BatchProcessingAppTest;
import com.erp.batch.mainapp.config.BeanConfig;
import com.erp.batch.mainapp.config.TestJPAConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchProcessingAppTest.class, TrxnHistoryController.class, BeanConfig.class, TestJPAConfig.class})
@TestPropertySource(locations = {"classpath:h2-db.properties"})
public class TrxnHistoryControllerIntegrationTest {

    @Value("${app.db.name}")
    private String dbName;

    @Rule
    public final EnvironmentVariables env = new EnvironmentVariables();

    @Before
    public void before() {
        env.set("my.system.env", "my-env");
        env.set("app.db.name", dbName);
    }

    @Test
    public void envTest(){
        Assert.assertTrue(System.getenv("my.system.env").equalsIgnoreCase("my-env"));
    }

    @Autowired
    private TrxnHistoryController controller;

    @Test
    public void initiateTest(){
        System.out.println("Integration Tests");
    }

    @Test
    public void count(){
        //
        controller.insert(new TrxnHistory("Rajib The Coder", Gender.MALE, 24));
        //
        ItemCount count = controller.getRowCount();
        System.out.println(count.getCount());
    }

    @Test
    public void query(){
        //
        controller.insert(new TrxnHistory("Samdani The Coder", Gender.MALE, 24));
        controller.insert(new TrxnHistory("Raju The Pankha Coder", Gender.MALE, 24));
        controller.insert(new TrxnHistory("Rajib The Coder", Gender.MALE, 26));
        //
        int size = Long.valueOf(controller.getRowCount().getCount()).intValue();
        List<TrxnHistory> items = controller.query(size, 0);
        items.stream().forEach(trxnHistory -> System.out.println(trxnHistory.getName()));
    }

}