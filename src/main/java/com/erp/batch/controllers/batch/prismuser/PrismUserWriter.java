package com.erp.batch.controllers.batch.prismuser;

import com.it.soul.lab.sql.SQLExecutor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class PrismUserWriter implements ItemWriter<PrismUser> {

    private final SQLExecutor sqlExecutor;

    public PrismUserWriter(@Qualifier("TmrOracleDataSource") DataSource dataSource) throws SQLException {
        this.sqlExecutor = new SQLExecutor(dataSource.getConnection());
    }

    @Override
    public void write(List<? extends PrismUser> list) throws Exception {
        System.out.println("Prism User ETL Writer:- " + Thread.currentThread().getName());

        list.forEach(
                msg -> System.out.println(
                        msg.getPkUserID()
                                + msg.getFkUserID()
                                + msg.getFirstName()
                                + msg.getLastName()
                                + msg.getContactNumber()
                                + msg.getPosition()
                                + msg.getEventsType()
                                + msg.getDataProcessDate()
                )
        );

        list.forEach(prismUser -> {
            try {
                prismUser.insert(sqlExecutor);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
