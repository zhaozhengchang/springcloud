package com.ceiec.twmp.tmp.vo.device.update;

import com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-02 11:17
 **/
public class DeviceUpdateVO {

    private Long deviceId;

    private String deviceNumber;

    private List<DeviceAttributeExtend> attributeExtend;

    private Long departmentId;

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

    public List<DeviceAttributeExtend> getAttributeExtend() {
        return attributeExtend;
    }

    public void setAttributeExtend(List<DeviceAttributeExtend> attributeExtend) {
        this.attributeExtend = attributeExtend;
    }
}
