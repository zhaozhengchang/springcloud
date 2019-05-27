package com.ceiec.twmp.tmp.vo.person.result;

import java.io.Serializable;

/**
 * @version V1.0
 * @title: PersonAndDeviceVO </br>
 * @createDate：2019/3/13 19:25 </br>
 * @author：shihsh </br>
 * @description: 人员和设备信息 </br>
 **/


public class PersonAndDeviceVO implements Serializable {

    private static final long serialVersionUID = 8591148689760131022L;

    private Long personId;

    private String personName;

    private Long departmentId;

    private String departmentName;

    private Long deviceId;

    private String deviceNumber;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}
