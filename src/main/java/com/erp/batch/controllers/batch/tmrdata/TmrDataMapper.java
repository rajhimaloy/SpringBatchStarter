package com.erp.batch.controllers.batch.tmrdata;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TmrDataMapper implements RowMapper<TmrData> {

    @Override
    public TmrData mapRow(ResultSet resultSet, int i) throws SQLException {
        TmrData tmrData = new TmrData();
        tmrData.setPkDataID(BigInteger.valueOf(resultSet.getLong("pk_data_id")));
        tmrData.setDataID(resultSet.getString("data_id"));
        tmrData.setMarketType(resultSet.getString("u_market_type"));
        tmrData.setTmoID(resultSet.getShort("fk_tmo_id"));
        tmrData.setUddoktaID(BigInteger.valueOf(resultSet.getLong("fk_uddokta_id")));
        tmrData.setDhID(resultSet.getShort("fk_dh_id"));
        tmrData.setTmID(resultSet.getShort("fk_tm_id"));
        tmrData.setBeforeInsideMedia(resultSet.getString("before_inside_media"));
        tmrData.setBeforeOutsideMedia(resultSet.getString("before_outside_media"));
        tmrData.setAfterInsideMedia(resultSet.getString("after_inside_media"));
        tmrData.setAfterOutsideMedia(resultSet.getString("after_outside_media"));
        tmrData.setLng(resultSet.getBigDecimal("lattitude"));
        tmrData.setLng(resultSet.getBigDecimal("longitude"));
        tmrData.setDistrict(resultSet.getString("_district"));
        tmrData.setThana(resultSet.getString("_thana"));
        tmrData.setDistanceFlag(resultSet.getString("_distance_flag"));
        tmrData.setDistanceFromUddokta(resultSet.getFloat("_distance_from_uddokta"));
        tmrData.setStartTime(resultSet.getTime("_start_time"));
        tmrData.setEndTime(resultSet.getTime("_end_time"));
        tmrData.setDurationText(resultSet.getString("_duration_text"));
        tmrData.setDurationMinutes(resultSet.getLong("_duration_minutes"));
        tmrData.setDurationSeconds(resultSet.getLong("_duration_seconds"));
        tmrData.setCreateDate(resultSet.getDate("create_date"));
        tmrData.setCreateTime(resultSet.getTime("create_time"));
        tmrData.setCreateDateTime(resultSet.getDate("create_date_time"));
        tmrData.setCreateBy(resultSet.getShort("create_by"));
        tmrData.setUpdateDate(resultSet.getDate("update_date"));
        tmrData.setUpdateTime(resultSet.getTime("update_time"));
        tmrData.setUpdateBy(resultSet.getShort("update_by"));
        return tmrData;
    }
}
