package com.ceiec.twmp.tmp.vo.incident.push;

/**
 * @title: PushAlarmVO </br>
 * @createDate: 2019/4/19 17:16 </br>
 * @author: shihsh  </br>
 * @description: 推送警情到接处警系统 </br>
 * @version: V1.0
 **/

// TODO 警情级别和警情级别名称？从接处警获取？
public class PushAlarmVO {

    private Long alarmId;

    private Integer alarmType;

    private String alarmTypeName;

    private String deviceNumber;

    private String personIdCarc;

    private String taskCode;

    private String incidentTypeId;

    private String incidentTime;

    private String incidentAddress;

    // 事发地址纬度
    private String incidentLatitude;

    // 事发地址经度
    private String incidentLongitude;

    // 报警人
    private String incidentReporter;

    private String incidentReportPhone;

    // 警情级别
    private Integer incidentGradeId;

    // 警情级别名称
    private String incidentGradeIdName;

    // 警情说明
    private String incidentDescription;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getPersonIdCarc() {
        return personIdCarc;
    }

    public void setPersonIdCarc(String personIdCarc) {
        this.personIdCarc = personIdCarc;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setIncidentTypeId(String incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public String getIncidentLatitude() {
        return incidentLatitude;
    }

    public void setIncidentLatitude(String incidentLatitude) {
        this.incidentLatitude = incidentLatitude;
    }

    public String getIncidentLongitude() {
        return incidentLongitude;
    }

    public void setIncidentLongitude(String incidentLongitude) {
        this.incidentLongitude = incidentLongitude;
    }

    public String getIncidentReporter() {
        return incidentReporter;
    }

    public void setIncidentReporter(String incidentReporter) {
        this.incidentReporter = incidentReporter;
    }

    public String getIncidentReportPhone() {
        return incidentReportPhone;
    }

    public void setIncidentReportPhone(String incidentReportPhone) {
        this.incidentReportPhone = incidentReportPhone;
    }

    public Integer getIncidentGradeId() {
        return incidentGradeId;
    }

    public void setIncidentGradeId(Integer incidentGradeId) {
        this.incidentGradeId = incidentGradeId;
    }

    public String getIncidentGradeIdName() {
        return incidentGradeIdName;
    }

    public void setIncidentGradeIdName(String incidentGradeIdName) {
        this.incidentGradeIdName = incidentGradeIdName;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }
}
