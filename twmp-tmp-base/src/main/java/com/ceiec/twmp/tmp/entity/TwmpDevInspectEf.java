package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_dev_inspect_ef")
public class TwmpDevInspectEf {
    @Id
    @Column(name = "inspect_id")
    private Long inspectId;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 设备编号
     */
    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "type_id")
    private Long typeId;

    /**
     * 自检时间
     */
    @Column(name = "inspect_time")
    private Date inspectTime;

    /**
     * 扩展属性(json格式)
     */
    @Column(name = "function_extend")
    private String functionExtend;

    /**
     * @return inspect_id
     */
    public Long getInspectId() {
        return inspectId;
    }

    /**
     * @param inspectId
     */
    public void setInspectId(Long inspectId) {
        this.inspectId = inspectId;
    }

    /**
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备编号
     *
     * @return device_number - 设备编号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置设备编号
     *
     * @param deviceNumber 设备编号
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
     * 获取自检时间
     *
     * @return inspect_time - 自检时间
     */
    public Date getInspectTime() {
        return inspectTime;
    }

    /**
     * 设置自检时间
     *
     * @param inspectTime 自检时间
     */
    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    /**
     * 获取扩展属性(json格式)
     *
     * @return function_extend - 扩展属性(json格式)
     */
    public String getFunctionExtend() {
        return functionExtend;
    }

    /**
     * 设置扩展属性(json格式)
     *
     * @param functionExtend 扩展属性(json格式)
     */
    public void setFunctionExtend(String functionExtend) {
        this.functionExtend = functionExtend;
    }
}