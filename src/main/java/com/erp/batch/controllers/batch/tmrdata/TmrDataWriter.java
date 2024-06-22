package com.erp.batch.controllers.batch.tmrdata;

import com.erp.batch.services.impl.JDBCBatchWriter;
import com.it.soul.lab.sql.SQLExecutor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class TmrDataWriter implements ItemWriter<TmrData> {

    private final SQLExecutor sqlExecutor;
    private final JDBCBatchWriter<TmrData> writer;

    public TmrDataWriter(@Qualifier("TmrOracleDataSource") DataSource dataSource) throws SQLException {
        this.sqlExecutor = new SQLExecutor(dataSource.getConnection());
        /*this.sqlExecutor = new SQLExecutor(DataSourceUtils.getConnection(dataSource));*/
        this.writer = new JDBCBatchWriter<>(sqlExecutor, TmrData.class);
    }

    @Override
    public void write(List<? extends TmrData> list) throws Exception {
        System.out.println("TMR Data ETL Writer:- " + Thread.currentThread().getName());

        writer.batchInsert(list);
    }
}
