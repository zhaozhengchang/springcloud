package com.ceiec.twmp.tmp.vo.device.result;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-04 17:10
 **/
public class DeviceListVO {

    private Long deviceId;

    private String deviceNumber;

    private Long typeId;

    private Byte opeStatus;

    private String opeStatusStr;

    private Byte onlineStatus;

    private String devicePower;

    private String attributeExtend;

    private Long departmentId;

    private String departmentName;

    private String comment;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Byte getOpeStatus() {
        return opeStatus;
    }

    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
    }

    public String getOpeStatusStr() {
        return opeStatusStr;
    }

    public void setOpeStatusStr(String opeStatusStr) {
        this.opeStatusStr = opeStatusStr;
    }

    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getDevicePower() {
        return devicePower;
    }

    public void setDevicePower(String devicePower) {
        this.devicePower = devicePower;
    }

    public String getAttributeExtend() {
        return attributeExtend;
    }

    public void setAttributeExtend(String attributeExtend) {
        this.attributeExtend = attributeExtend;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
