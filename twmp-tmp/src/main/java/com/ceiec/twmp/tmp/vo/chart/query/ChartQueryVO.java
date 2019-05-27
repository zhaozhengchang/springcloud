package com.ceiec.twmp.tmp.vo.chart.query;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 9:27
 * Description:
 **/

public class ChartQueryVO implements Serializable {

    private static final long serialVersionUID = -2535334589614671637L;

    private Long departmentId;
    private String startTime;
    private String endTime;
    private List<Long> departmentIdList;
    private List<Integer> alarmTypes ;

    public List<Long> getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List<Long> departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(List<Integer> alarmTypes) {
        this.alarmTypes = alarmTypes;
    }
}
