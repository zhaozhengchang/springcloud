package com.ceiec.twmp.tmp.vo.ope.result;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-15 14:31
 **/
public class OpeFinishResultVO {

    private Long opeId;

    private String taskCode;

    private String personName;

    private String deviceNumber;

    private String oldDeviceNumber;

    private String departmentName;

    private Long departmentId;

    private String ops;

    private Date recordTime;

    private String recordTimeStr;

    public Long getOpeId() {
        return opeId;
    }

    public void setOpeId(Long opeId) {
        this.opeId = opeId;
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

    public String getOldDeviceNumber() {
        return oldDeviceNumber;
    }

    public void setOldDeviceNumber(String oldDeviceNumber) {
        this.oldDeviceNumber = oldDeviceNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordTimeStr() {
        return recordTimeStr;
    }

    public void setRecordTimeStr(String recordTimeStr) {
        this.recordTimeStr = recordTimeStr;
    }
}
