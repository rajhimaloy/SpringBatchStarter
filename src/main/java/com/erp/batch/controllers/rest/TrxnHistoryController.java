package com.erp.batch.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.erp.batch.domain.entities.TrxnHistory;
import com.erp.batch.rest.models.ItemCount;
import com.erp.batch.services.iServices.iTrxnHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trxnhistory")
public class TrxnHistoryController {

    private iTrxnHistoryService service;
    private ObjectMapper mapper;

    public TrxnHistoryController(iTrxnHistoryService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() throws JsonProcessingException {
        ItemCount count = new ItemCount();
        count.setCount(12l);
        return ResponseEntity.ok(mapper.writeValueAsString(count));
    }

    @GetMapping("/rowCount")
    public ItemCount getRowCount(){
        ItemCount count = new ItemCount();
        count.setCount(service.totalCount());
        return count;
    }

    @GetMapping
    public List<TrxnHistory> query(@RequestParam("limit") Integer size
            , @RequestParam("offset") Integer page){
        //TODO: Test with RestExecutor
        List<TrxnHistory> trxnHistorys = service.findAll(page, size);
        return trxnHistorys;
    }

    @PostMapping
    public TrxnHistory insert(@Valid @RequestBody TrxnHistory trxnHistory){
        //TODO: Test with RestExecutor
        TrxnHistory nTrxnHistory = service.add(trxnHistory);
        return nTrxnHistory;
    }

    @PutMapping
    public TrxnHistory update(@Valid @RequestBody TrxnHistory trxnHistory){
        //TODO: Test with RestExecutor
        TrxnHistory old = service.update(trxnHistory);
        return old;
    }

    @DeleteMapping
    public Boolean delete(@RequestParam("userid") Integer userid){
        //TODO: Test with RestExecutor
        boolean deleted = service.remove(userid);
        return deleted;
    }

}
