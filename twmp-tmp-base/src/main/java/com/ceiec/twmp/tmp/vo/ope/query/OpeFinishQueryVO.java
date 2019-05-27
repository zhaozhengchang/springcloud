package com.ceiec.twmp.tmp.vo.ope.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: query vo for finished ope
 * @create 2019-04-15 14:25
 **/
public class OpeFinishQueryVO extends PageParentVO {

    private Long departmentId;

    private List<Long> ownDepartmentId;

    private String personName;

    private String taskCode;

    private String deviceNumber;

    private Date beginTime;

    private Date endTime;

    private String beginTimeStr;

    private String endTimeStr;

    private Byte opeType;

    public Byte getOpeType() {
        return opeType;
    }

    public void setOpeType(Byte opeType) {
        this.opeType = opeType;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
