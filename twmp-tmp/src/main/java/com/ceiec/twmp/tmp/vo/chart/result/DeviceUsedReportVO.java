package com.ceiec.twmp.tmp.vo.chart.result;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

public class DeviceUsedReportVO {
    private Long reportId;

    /**
     * 使用率0-100
     */
    private Double utilizationRate;

    /**
     * 组织机构id
     */
    private Long departmentId;

    /**
     * 使用时间
     */
//    @JsonFormat(pattern = "yyyy",timezone="GMT+8")
    private DateTimeLiteralExpression.DateTime utilityTime;

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

    public DateTimeLiteralExpression.DateTime getUtilityTime() {
        return utilityTime;
    }

    public void setUtilityTime(DateTimeLiteralExpression.DateTime utilityTime) {
        this.utilityTime = utilityTime;
    }
}