package com.ceiec.twmp.tmp.vo.monitor.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 16:10
 **/
public class TaskQueryVO extends PageParentVO {

    private String taskCode;

    private String personName;

    private String deviceNumber;

    private Long departmentId;

    private List<Long> ownDepartmentId;

    private Byte taskStatus;

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getOwnDepartmentId() {
        return ownDepartmentId;
    }

    public void setOwnDepartmentId(List<Long> ownDepartmentId) {
        this.ownDepartmentId = ownDepartmentId;
    }
}
