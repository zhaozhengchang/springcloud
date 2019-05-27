package com.ceiec.twmp.tmp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sm_device_used_report")
public class TwmpSmDeviceUsedReport {
    @Id
    @Column(name = "report_id")
    private Long reportId;

    /**
     * 使用率0-100
     */
    @Column(name = "utilization_rate")
    private Double utilizationRate;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Column(name = "utility_time")
    private Date utilityTime;

    /**
     * @return report_id
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * @param reportId
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取使用率0-100
     *
     * @return utilization_rate - 使用率0-100
     */
    public Double getUtilizationRate() {
        return utilizationRate;
    }

    /**
     * 设置使用率0-100
     *
     * @param utilizationRate 使用率0-100
     */
    public void setUtilizationRate(Double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取使用时间
     *
     * @return utility_time - 使用时间
     */
    public Date getUtilityTime() {
        return utilityTime;
    }

    /**
     * 设置使用时间
     *
     * @param utilityTime 使用时间
     */
    public void setUtilityTime(Date utilityTime) {
        this.utilityTime = utilityTime;
    }
}