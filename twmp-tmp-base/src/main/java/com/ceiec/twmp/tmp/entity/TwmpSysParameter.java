package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "twmp_sys_parameter")
public class TwmpSysParameter {
    /**
     * 参数id
     */
    @Id
    @Column(name = "parameter_id")
    private Long parameterId;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_range")
    private String parameterRange;

    @Column(name = "parameter_unit")
    private String parameterUnit;

    @Column(name = "parameter_value")
    private String parameterValue;

    @Column(name = "deleted")
    private Byte deleted;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "updater_id")
    private Long updaterId;

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterRange() {
        return parameterRange;
    }

    public void setParameterRange(String parameterRange) {
        this.parameterRange = parameterRange;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }
}