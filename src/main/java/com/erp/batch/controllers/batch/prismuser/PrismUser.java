package com.erp.batch.controllers.batch.prismuser;

import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.entity.PrimaryKey;
import com.it.soul.lab.sql.entity.TableName;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@javax.persistence.Entity
@TableName(value = "PRISM_USERS", acceptAll = false)
public class PrismUser extends Entity {

    @Id
    @PrimaryKey(name = "PK_USER_ID")
    private Long pkUserID;

    @Column(name = "FK_USER_ID")
    private Long fkUserID;

    @Column(name = "U_FIRSTNAME")
    private String firstName;

    @Column(name = "U_LASTNAME")
    private String lastName;

    @Column(name = "U_USERNAME")
    private String userName;

    @Column(name = "U_EMAIL")
    private String email;

    @Column(name = "U_CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "U_PASSWORD")
    private String password;

    @Column(name = "U_ROLE")
    private Short role;

    @Column(name = "U_PERMISSIONS")
    private String permissions;

    @Column(name = "U_STATUS")
    private String status;

    @Column(name = "U_TOKEN")
    private String token;

    @Column(name = "U_CHANGE_PASSWORD")
    private Short changePassword;

    @Column(name = "U_POSITION")
    private String position;

    @Column(name = "PRISM_JOIN_DATE")
    private Date joinDate;

    @Column(name = "PRISM_RESIGN_DATE")
    private Date resignDate;

    @Column(name = "PRISM_IS_METRO")
    private Short isMetro;

    @Column(name = "PRISM_IS_NONMETRO")
    private Short isNonMetro;

    @Column(name = "PRISM_USING_GLOBAL_TARGETS")
    private Short usingGlobalTargets;

    @Column(name = "PRISM_CURRENT_HOME_TEXT")
    private String currentHomeText;

    @Column(name = "PRISM_CURRENT_HOME_LAT")
    private String currentHomeLat;

    @Column(name = "PRISM_CURRENT_HOME_LONG")
    private String currentHomeLong;

    @Column(name = "PRISM_CURRENT_HOME_FK_ID")
    private Integer currentHomeFkID;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CREATE_BY")
    private Short createBy;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_BY")
    private Short updateBy;

    @Column(name = "EVENTS_TYPE")
    private String eventsType;

    @Column(name = "DATA_PROCESS_DATE")
    private Date dataProcessDate;

    public Long getPkUserID() {
        return pkUserID;
    }

    public void setPkUserID(Long pkUserID) {
        this.pkUserID = pkUserID;
    }

    public Long getFkUserID() {
        return fkUserID;
    }

    public void setFkUserID(Long fkUserID) {
        this.fkUserID = fkUserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Short getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Short changePassword) {
        this.changePassword = changePassword;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public Short getIsMetro() {
        return isMetro;
    }

    public void setIsMetro(Short isMetro) {
        this.isMetro = isMetro;
    }

    public Short getIsNonMetro() {
        return isNonMetro;
    }

    public void setIsNonMetro(Short isNonMetro) {
        this.isNonMetro = isNonMetro;
    }

    public Short getUsingGlobalTargets() {
        return usingGlobalTargets;
    }

    public void setUsingGlobalTargets(Short usingGlobalTargets) {
        this.usingGlobalTargets = usingGlobalTargets;
    }

    public String getCurrentHomeText() {
        return currentHomeText;
    }

    public void setCurrentHomeText(String currentHomeText) {
        this.currentHomeText = currentHomeText;
    }

    public String getCurrentHomeLat() {
        return currentHomeLat;
    }

    public void setCurrentHomeLat(String currentHomeLat) {
        this.currentHomeLat = currentHomeLat;
    }

    public String getCurrentHomeLong() {
        return currentHomeLong;
    }

    public void setCurrentHomeLong(String currentHomeLong) {
        this.currentHomeLong = currentHomeLong;
    }

    public Integer getCurrentHomeFkID() {
        return currentHomeFkID;
    }

    public void setCurrentHomeFkID(Integer currentHomeFkID) {
        this.currentHomeFkID = currentHomeFkID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Short createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Short updateBy) {
        this.updateBy = updateBy;
    }

    public String getEventsType() {
        return eventsType;
    }

    public void setEventsType(String eventsType) {
        this.eventsType = eventsType;
    }

    public Date getDataProcessDate() {
        return dataProcessDate;
    }

    public void setDataProcessDate(Date dataProcessDate) {
        this.dataProcessDate = dataProcessDate;
    }
}
