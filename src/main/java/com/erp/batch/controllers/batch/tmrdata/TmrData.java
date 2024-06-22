package com.erp.batch.controllers.batch.tmrdata;

import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.entity.PrimaryKey;
import com.it.soul.lab.sql.entity.TableName;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@javax.persistence.Entity
//@Table(name = "TMR_DATAS")
@TableName(value = "TMR_DATAS", acceptAll = false)
public class TmrData extends Entity {

    @Id
    @PrimaryKey(name = "PK_DATA_ID")
    private BigInteger pkDataID;

    @Column(name = "DATA_ID")
    private String dataID;

    @Column(name = "U_MARKET_TYPE")
    private String marketType;

    @Column(name = "FK_TMO_ID")
    private Short tmoID;

    @PrimaryKey(name = "FK_UDDOKTA_ID")
    private BigInteger uddoktaID;

    @Column(name = "FK_DH_ID")
    private Short dhID;

    @Column(name = "FK_TM_ID")
    private Short tmID;

    @Column(name = "BEFORE_INSIDE_MEDIA")
    private String beforeInsideMedia;

    @Column(name = "BEFORE_OUTSIDE_MEDIA")
    private String beforeOutsideMedia;

    @Column(name = "AFTER_INSIDE_MEDIA")
    private String afterInsideMedia;

    @Column(name = "AFTER_OUTSIDE_MEDIA")
    private String afterOutsideMedia;

    @Column(name = "LATTITUDE")
    private BigDecimal lat;

    @Column(name = "LONGITUDE")
    private BigDecimal lng;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "THANA")
    private String thana;

    @Column(name = "DISTANCE_FLAG")
    private String distanceFlag;

    @Column(name = "DISTANCE_FROM_UDDOKTA")
    private Float distanceFromUddokta;

    @Column(name = "START_TIME")
    private Time startTime;

    @Column(name = "END_TIME")
    private Time endTime;

    @Column(name = "DURATION_TEXT")
    private String durationText;

    @PrimaryKey(name = "DURATION_MINUTES")
    private Long durationMinutes;

    @PrimaryKey(name = "DURATION_SECONDS")
    private Long durationSeconds;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CREATE_TIME")
    private Time createTime;

    @Column(name = "CREATE_DATE_TIME")
    private Date createDateTime;

    @Column(name = "CREATE_BY")
    private Short createBy;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "UPDATE_TIME")
    private Time updateTime;

    @Column(name = "UPDATE_BY")
    private Short updateBy;

    @Column(name = "EVENTS_TYPE")
    private String eventsType;

    @Column(name = "DATA_PROCESS_DATE")
    private Date dataProcessDate;

    public BigInteger getPkDataID() {
        return pkDataID;
    }

    public void setPkDataID(BigInteger pkDataID) {
        this.pkDataID = pkDataID;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Short getTmoID() {
        return tmoID;
    }

    public void setTmoID(Short tmoID) {
        this.tmoID = tmoID;
    }

    public BigInteger getUddoktaID() {
        return uddoktaID;
    }

    public void setUddoktaID(BigInteger uddoktaID) {
        this.uddoktaID = uddoktaID;
    }

    public Short getDhID() {
        return dhID;
    }

    public void setDhID(Short dhID) {
        this.dhID = dhID;
    }

    public Short getTmID() {
        return tmID;
    }

    public void setTmID(Short tmID) {
        this.tmID = tmID;
    }

    public String getBeforeInsideMedia() {
        return beforeInsideMedia;
    }

    public void setBeforeInsideMedia(String beforeInsideMedia) {
        this.beforeInsideMedia = beforeInsideMedia;
    }

    public String getBeforeOutsideMedia() {
        return beforeOutsideMedia;
    }

    public void setBeforeOutsideMedia(String beforeOutsideMedia) {
        this.beforeOutsideMedia = beforeOutsideMedia;
    }

    public String getAfterInsideMedia() {
        return afterInsideMedia;
    }

    public void setAfterInsideMedia(String afterInsideMedia) {
        this.afterInsideMedia = afterInsideMedia;
    }

    public String getAfterOutsideMedia() {
        return afterOutsideMedia;
    }

    public void setAfterOutsideMedia(String afterOutsideMedia) {
        this.afterOutsideMedia = afterOutsideMedia;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getDistanceFlag() {
        return distanceFlag;
    }

    public void setDistanceFlag(String distanceFlag) {
        this.distanceFlag = distanceFlag;
    }

    public Float getDistanceFromUddokta() {
        return distanceFromUddokta;
    }

    public void setDistanceFromUddokta(Float distanceFromUddokta) {
        this.distanceFromUddokta = distanceFromUddokta;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDurationText() {
        return durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
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

    public Time getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Time updateTime) {
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
