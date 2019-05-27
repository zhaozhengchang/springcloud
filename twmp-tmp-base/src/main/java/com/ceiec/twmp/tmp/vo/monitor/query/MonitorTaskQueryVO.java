package com.ceiec.twmp.tmp.vo.monitor.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.List;

/**
 * @version V1.0
 * @title: MonitorTaskQueryVO </br>
 * @createDate：2019/3/15 11:45 </br>
 * @author：shihsh </br>
 * @description:  监控任务列表查询</br>
 **/


public class MonitorTaskQueryVO extends PageParentVO {


    private static final long serialVersionUID = 7257022895732405332L;
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 被监控人
     */
    private String personName;

    /**
     * 设备编号
     */
    private String deviceNumber;

    /**
     * 监控任务编号
     */
    private String taskCode;

    /**
     * 监控任务状态Id
     */
    private Byte taskStatus;

    private List<Byte> taskStatusList;

    /**
     * 登陆用户所拥有的组织机构权限
     */
    private List<Long> departmentIds;

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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<Byte> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<Byte> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
