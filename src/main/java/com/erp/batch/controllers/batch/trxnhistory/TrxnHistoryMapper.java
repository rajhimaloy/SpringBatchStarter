package com.erp.batch.controllers.batch.trxnhistory;

import com.erp.batch.domain.entities.TrxnHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrxnHistoryMapper implements RowMapper<TrxnHistory> {
    @Override
    public TrxnHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        TrxnHistory trxnHistory = new TrxnHistory();
        trxnHistory.setId(resultSet.getInt("id"));
        trxnHistory.setName(resultSet.getString("name"));
        trxnHistory.setActive(resultSet.getBoolean("active"));
        trxnHistory.setAge(resultSet.getInt("age"));
        trxnHistory.setSex(resultSet.getString("sex"));
        trxnHistory.setDob(resultSet.getDate("dob"));
        return trxnHistory;
    }
}
