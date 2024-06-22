package com.erp.batch.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.erp.batch.domain.entities.Gender;
import com.erp.batch.controllers.batch.trxnhistory.TrxnHistory;
import com.erp.batch.mainapp.BatchProcessingAppTest;
import com.erp.batch.mainapp.config.BeanConfig;
import com.erp.batch.mainapp.config.TestJPAConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {BatchProcessingAppTest.class, TestJPAConfig.class, BeanConfig.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:h2-db.properties")
public class TrxnHistoryControllerUnitTest {

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    iTrxnHistoryService service;

    @InjectMocks
    TrxnHistoryController controller;

    @Test
    public void helloGetTest() throws Exception {
        //Call controller to make the save:
        MvcResult result = mockMvc.perform(get("/trxnhistory/hello")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        String str = "Status: " + result.getResponse().getStatus();
        System.out.println(str);
        String responseBody = result.getResponse().getContentAsString();
        System.out.println(responseBody);
        //
    }

    @Test
    public void happyPathTest() throws Exception {
        //Defining Mock Object:
        TrxnHistory aTrxnHistory = new TrxnHistory("Rajib", Gender.MALE, 36);

        when(service.add(any(TrxnHistory.class))).thenReturn(aTrxnHistory);

        //Call controller to make the save:
        MvcResult result = mockMvc.perform(post("/trxnhistory")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(aTrxnHistory))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andExpect(status().isOk())
                .andReturn();
        String str = "Status: " + result.getResponse().getStatus();
        System.out.println(str);
        //
    }

    @Test
    public void rowCountGetTest() throws Exception {
        //Call controller to make the save:
        MvcResult result = mockMvc.perform(get("/trxnhistory/rowCount")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                //.andExpect(status().isOk())
                .andReturn();
        String str = "Status: " + result.getResponse().getStatus();
        System.out.println(str);
        //
    }

}
