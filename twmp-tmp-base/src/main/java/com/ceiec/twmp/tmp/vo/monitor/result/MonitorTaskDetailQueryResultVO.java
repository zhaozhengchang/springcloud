package com.ceiec.twmp.tmp.vo.monitor.result;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonCriminalEf;
import com.ceiec.twmp.tmp.vo.person.result.PersonCriminalResultVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: MonitorTaskDetailQueryResultVO </br>
 * @createDate: 2019/3/17 20:10 </br>
 * @author: shihsh </br>
 * @description: 监控任务详情查询结果 </br>
 **/


public class MonitorTaskDetailQueryResultVO implements Serializable {

    private static final long serialVersionUID = -7400262176597287868L;

    private Long personId;

    private String personName;
    private String personNumber;
    private String formerName;
    private String personUrl;
    private String birthDate;
    private Byte gender;
    private Byte maritalStatus;
    private String personIdCard;
    private Long departmentId;
    private String phone;
    private String address;
    private String career;
    private Long taskId;
    private String taskCode;
    private Date startTimeDate;
    private String startTime;
    private String endTime;
    private Date endTimeDate;
    private Byte taskLevel;
    private String documentUrl;
    private String taskReason;
    private String taskType;
    private Date reportStartTimeDate;
    private String reportStartTime;
    private Date reportEndTimeDate;
    private String reportEndTime;
    private int reportTimeInterval;
    private String reportLocation;
    private Date reportTimeDate;
    private String reportTime;

    private List<PersonCriminalResultVO> criminalList;
    private List<TwmpBsPersonCriminalEf> criminalEfList;

    private List<FenceResultVO> fence;


    private List<MonitorGuardianResultVO> guardianList;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public List<PersonCriminalResultVO> getCriminalList() {
        return criminalList;
    }

    public void setCriminalList(List<PersonCriminalResultVO> criminalList) {
        this.criminalList = criminalList;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonUrl() {
        return personUrl;
    }

    public void setPersonUrl(String personUrl) {
        this.personUrl = personUrl;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Byte getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Byte maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }


    public List<FenceResultVO> getFence() {
        return fence;
    }

    public void setFence(List<FenceResultVO> fence) {
        this.fence = fence;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getTaskReason() {
        return taskReason;
    }

    public void setTaskReason(String taskReason) {
        this.taskReason = taskReason;
    }

    public Date getReportStartTimeDate() {
        return reportStartTimeDate;
    }

    public void setReportStartTimeDate(Date reportStartTimeDate) {
        this.reportStartTimeDate = reportStartTimeDate;
    }

    public Date getReportEndTimeDate() {
        return reportEndTimeDate;
    }

    public void setReportEndTimeDate(Date reportEndTimeDate) {
        this.reportEndTimeDate = reportEndTimeDate;
    }

    public int getReportTimeInterval() {
        return reportTimeInterval;
    }

    public void setReportTimeInterval(int reportTimeInterval) {
        this.reportTimeInterval = reportTimeInterval;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public Date getReportTimeDate() {
        return reportTimeDate;
    }

    public void setReportTimeDate(Date reportTimeDate) {
        this.reportTimeDate = reportTimeDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReportStartTime() {
        return reportStartTime;
    }

    public void setReportStartTime(String reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    public String getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(String reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public List<MonitorGuardianResultVO> getGuardianList() {
        return guardianList;
    }

    public void setGuardianList(List<MonitorGuardianResultVO> guardianList) {
        this.guardianList = guardianList;
    }

    public List<TwmpBsPersonCriminalEf> getCriminalEfList() {
        return criminalEfList;
    }

    public void setCriminalEfList(List<TwmpBsPersonCriminalEf> criminalEfList) {
        this.criminalEfList = criminalEfList;
    }
}
