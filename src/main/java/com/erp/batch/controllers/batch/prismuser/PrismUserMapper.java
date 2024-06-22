package com.erp.batch.controllers.batch.prismuser;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrismUserMapper implements RowMapper<PrismUser> {

    @Override
    public PrismUser mapRow(ResultSet resultSet, int i) throws SQLException {
        PrismUser prismUser = new PrismUser();
        prismUser.setPkUserID(resultSet.getLong("pk_user_id"));
        prismUser.setFkUserID(resultSet.getLong("fk_user_id"));
        prismUser.setFirstName(resultSet.getString("u_firstname"));
        prismUser.setLastName(resultSet.getString("u_lastname"));
        prismUser.setUserName(resultSet.getString("u_username"));
        prismUser.setEmail(resultSet.getString("u_email"));
        prismUser.setContactNumber(resultSet.getString("u_contact_number"));
        prismUser.setPassword(resultSet.getString("u_password"));
        prismUser.setRole(resultSet.getShort("u_role"));
        prismUser.setPermissions(resultSet.getString("u_permissions"));
        prismUser.setStatus(resultSet.getString("u_status"));
        prismUser.setToken(resultSet.getString("u_token"));
        prismUser.setChangePassword(resultSet.getShort("u_change_password"));
        prismUser.setPosition(resultSet.getString("u_position"));
        prismUser.setJoinDate(resultSet.getDate("_join_date"));
        prismUser.setResignDate(resultSet.getDate("_resign_date"));
        prismUser.setIsMetro(resultSet.getShort("_is_metro"));
        prismUser.setIsNonMetro(resultSet.getShort("_is_nonmetro"));
        prismUser.setUsingGlobalTargets(resultSet.getShort("_using_global_targets"));
        prismUser.setCurrentHomeText(resultSet.getString("_current_home_text"));
        prismUser.setCurrentHomeLat(resultSet.getString("_current_home_lat"));
        prismUser.setCurrentHomeLong(resultSet.getString("_current_home_long"));
        prismUser.setCurrentHomeFkID(resultSet.getInt("_current_home_fk_id"));
        prismUser.setCreateDate(resultSet.getDate("create_date"));
        prismUser.setCreateTime(resultSet.getDate("create_time"));
        prismUser.setCreateBy(resultSet.getShort("create_by"));
        prismUser.setUpdateDate(resultSet.getDate("update_date"));
        prismUser.setUpdateTime(resultSet.getDate("update_time"));
        prismUser.setUpdateBy(resultSet.getShort("update_by"));

        return prismUser;
    }
}
