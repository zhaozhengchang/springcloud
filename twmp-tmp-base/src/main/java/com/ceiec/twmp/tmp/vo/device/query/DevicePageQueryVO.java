package com.ceiec.twmp.tmp.vo.device.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-02 10:19
 **/
public class DevicePageQueryVO extends PageParentVO {

    private Long departmentId;

    private String deviceNumber;

    private Byte opeStatus;

    private Long typeId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Byte getOpeStatus() {
        return opeStatus;
    }

    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
    }
}
