package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_dev_device")
public class TwmpDevDevice extends BaseEntity {
    @Id
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "ope_status")
    private Byte opeStatus;

    @Column(name = "online_status")
    private Byte onlineStatus;

    @Column(name = "device_power")
    private String devicePower;


    /**
     * 扩展属性
     */
    @Column(name = "attribute_extend")
    private String attributeExtend;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "comment")
    private String comment;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return device_id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return device_number
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * @param deviceNumber
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * @return type_id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * @return ope_status
     */
    public Byte getOpeStatus() {
        return opeStatus;
    }

    /**
     * @param opeStatus
     */
    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
    }

    /**
     * @return online_status
     */
    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * @param onlineStatus
     */
    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    /**
     * @return device_power
     */
    public String getDevicePower() {
        return devicePower;
    }

    /**
     * @param devicePower
     */
    public void setDevicePower(String devicePower) {
        this.devicePower = devicePower;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取扩展属性
     *
     * @return attribute_extend - 扩展属性
     */
    public String getAttributeExtend() {
        return attributeExtend;
    }

    /**
     * 设置扩展属性
     *
     * @param attributeExtend 扩展属性
     */
    public void setAttributeExtend(String attributeExtend) {
        this.attributeExtend = attributeExtend;
    }
}