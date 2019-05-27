package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sm_alarm_ef")
public class TwmpSmAlarmEf extends BaseEntity {
    @Id
    @Column(name = "alarm_id")
    private Long alarmId;

    @Column(name = "alarm_number")
    private String alarmNumber;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "task_level")
    private Byte taskLevel;

    @Column(name = "last_dispose_time")
    private Date lastDisposeTime;

    /**
     * 设备编号
     */
    @Column(name = "device_number")
    private String deviceNumber;

    /**
     * 告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）
     */
    @Column(name = "alarm_type")
    private Byte alarmType;

    /**
     * 告警时间
     */
    @Column(name = "alarm_time")
    private Date alarmTime;

    /**
     * 告警状态（1：未分配；2：待处置；3：已处置）
     */
    @Column(name = "alarm_status")
    private Byte alarmStatus;

    /**
     * 接警开始时间
     */
    @Column(name = "incident_appeal_time")
    private Date incidentAppealTime;

    /**
     * 处警开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 处警结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 处置人
     */
    @Column(name = "incident_disposal_person_name")
    private String incidentDisposalPersonName;

    /**
     * 事发地
     */
    @Column(name = "incident_address")
    private String incidentAddress;

    /**
     * 真假警（0假警情，1真警情）
     */
    @Column(name = "alarm_flag")
    private Byte alarmFlag;

    /**
     * 报警相关值
     */
    @Column(name = "tag_value")
    private String tagValue;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 组织机构名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 告警地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 速度
     */
    private String speed;

    /**
     * 方向
     */
    private String direction;

    /**
     * gps时间
     */
    @Column(name = "gps_time")
    private Date gpsTime;

    /**
     * 用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 
     */
    @Column(name = "reject_user_id")
    private String rejectUserId;

    /**
     * 当前处理该条报警信息的用户id
     */
    @Column(name = "handle_user_id")
    private Long handleUserId;

    /**
     * 当前处理该条报警信息的用户名
     */
    @Column(name = "handle_user")
    private String handleUser;

    @Column(name = "person_name")
    private String personName;
    @Column(name = "person_id")
    private Long personId;
    @Column(name = "device_id")
    private Long deviceId;

    @Column
    private String remark;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return alarm_id
     */
    public Long getAlarmId() {
        return alarmId;
    }

    /**
     * @param alarmId
     */
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * 获取监控任务id
     *
     * @return task_id - 监控任务id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置监控任务id
     *
     * @param taskId 监控任务id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return last_dispose_time
     */
    public Date getLastDisposeTime() {
        return lastDisposeTime;
    }

    /**
     * @param lastDisposeTime
     */
    public void setLastDisposeTime(Date lastDisposeTime) {
        this.lastDisposeTime = lastDisposeTime;
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
     * 获取告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）
     *
     * @return alarm_type - 告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）
     */
    public Byte getAlarmType() {
        return alarmType;
    }

    /**
     * 设置告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）
     *
     * @param alarmType 告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）
     */
    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * 获取告警时间
     *
     * @return alarm_time - 告警时间
     */
    public Date getAlarmTime() {
        return alarmTime;
    }

    /**
     * 设置告警时间
     *
     * @param alarmTime 告警时间
     */
    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    /**
     * 获取告警状态（1：未分配；2：待处置；3：已处置）
     *
     * @return alarm_status - 告警状态（1：未分配；2：待处置；3：已处置）
     */
    public Byte getAlarmStatus() {
        return alarmStatus;
    }

    /**
     * 设置告警状态（1：未分配；2：待处置；3：已处置）
     *
     * @param alarmStatus 告警状态（1：未分配；2：待处置；3：已处置）
     */
    public void setAlarmStatus(Byte alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    /**
     * 获取接警开始时间
     *
     * @return incident_appeal_time - 接警开始时间
     */
    public Date getIncidentAppealTime() {
        return incidentAppealTime;
    }

    /**
     * 设置接警开始时间
     *
     * @param incidentAppealTime 接警开始时间
     */
    public void setIncidentAppealTime(Date incidentAppealTime) {
        this.incidentAppealTime = incidentAppealTime;
    }

    /**
     * 获取处警开始时间
     *
     * @return start_time - 处警开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置处警开始时间
     *
     * @param startTime 处警开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取处警结束时间
     *
     * @return end_time - 处警结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置处警结束时间
     *
     * @param endTime 处警结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取处置人
     *
     * @return incident_disposal_person_name - 处置人
     */
    public String getIncidentDisposalPersonName() {
        return incidentDisposalPersonName;
    }

    /**
     * 设置处置人
     *
     * @param incidentDisposalPersonName 处置人
     */
    public void setIncidentDisposalPersonName(String incidentDisposalPersonName) {
        this.incidentDisposalPersonName = incidentDisposalPersonName;
    }

    /**
     * 获取事发地
     *
     * @return incident_address - 事发地
     */
    public String getIncidentAddress() {
        return incidentAddress;
    }

    /**
     * 设置事发地
     *
     * @param incidentAddress 事发地
     */
    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    /**
     * 获取真假警（0假警情，1真警情）
     *
     * @return alarm_flag - 真假警（0假警情，1真警情）
     */
    public Byte getAlarmFlag() {
        return alarmFlag;
    }

    /**
     * 设置真假警（0假警情，1真警情）
     *
     * @param alarmFlag 真假警（0假警情，1真警情）
     */
    public void setAlarmFlag(Byte alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    /**
     * 获取报警相关值
     *
     * @return tag_value - 报警相关值
     */
    public String getTagValue() {
        return tagValue;
    }

    /**
     * 设置报警相关值
     *
     * @param tagValue 报警相关值
     */
    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取组织机构名称
     *
     * @return department_name - 组织机构名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置组织机构名称
     *
     * @param departmentName 组织机构名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 获取告警地址
     *
     * @return address - 告警地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置告警地址
     *
     * @param address 告警地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取速度
     *
     * @return speed - 速度
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * 设置速度
     *
     * @param speed 速度
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * 获取方向
     *
     * @return direction - 方向
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置方向
     *
     * @param direction 方向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 获取gps时间
     *
     * @return gps_time - gps时间
     */
    public Date getGpsTime() {
        return gpsTime;
    }

    /**
     * 设置gps时间
     *
     * @param gpsTime gps时间
     */
    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    /**
     * 获取用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 
     *
     * @return used_user_id - 用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 
     */
    public String getRejectUserId() {
        return rejectUserId;
    }


    /**
     * 设置用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 
     *
     * @param usedUserId 用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 
     */
    public void setRejectUserId(String rejectUserId) {
        this.rejectUserId = rejectUserId;
    }

    /**
     * 获取当前处理该条报警信息的用户id
     *
     * @return handle_user_id - 当前处理该条报警信息的用户id
     */
    public Long getHandleUserId() {
        return handleUserId;
    }

    /**
     * 设置当前处理该条报警信息的用户id
     *
     * @param handleUserId 当前处理该条报警信息的用户id
     */
    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    /**
     * 获取当前处理该条报警信息的用户名
     *
     * @return handle_user - 当前处理该条报警信息的用户名
     */
    public String getHandleUser() {
        return handleUser;
    }

    /**
     * 设置当前处理该条报警信息的用户名
     *
     * @param handleUser 当前处理该条报警信息的用户名
     */
    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }
}