package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "twmp_ope_change")
public class TwmpOpeChange {
    @Id
    @Column(name = "change_id")
    private Long changeId;

    /**
     * 旧的设备id
     */
    @Column(name = "old_device_id")
    private Long oldDeviceId;

    /**
     * 旧的设备编号
     */
    @Column(name = "old_device_number")
    private String oldDeviceNumber;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * 运维人员
     */
    private String ops;

    /**
     * 运维人员id
     */
    @Column(name = "ops_id")
    private Long opsId;

    /**
     * 变更时间
     */
    @Column(name = "change_time")
    private Date changeTime;

    /**
     * 校验通过功能扩展属性(json格式)
     */
    @Column(name = "function_extend")
    private String functionExtend;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "deleted")
    private Byte deleted;

    /**
     * 人员编号
     */
    @Column(name = "person_number")
    private String personNumber;

    /**
     * 身份证号
     */
    @Column(name = "person_id_card")
    private String personIdCard;

    /**
     * 被监控人姓名
     */
    @Column(name = "person_name")
    private String personName;

    /**
     * 曾用名
     */
    @Column(name = "person_former_name")
    private String personFormerName;

    /**
     * 照片url(多张图片地址，以，分割)
     */
    @Column(name = "person_url")
    private String personUrl;

    /**
     * 出生日期
     */
    @Column(name = "person_birth_date")
    private String personBirthDate;

    /**
     * 性别（1男，2女，3其他）
     */
    private Byte personGender;

    /**
     * 婚姻状态（1，已婚 2，未婚）
     */
    @Column(name = "person_marital_status")
    private Byte personMaritalStatus;

    /**
     * 职业
     */
    private String personCareer;

    /**
     * 联系电话
     */
    private String personPhone;

    /**
     * 联系邮件
     */
    private String personEmail;

    /**
     * 联系家庭地址
     */
    private String personAddress;

    /**
     * 备注
     */
    private String personComment;

    /**
     * 组织机构id
     */
    @Column(name = "person_department_id")
    private Long personDepartmentId;


    @Column(name = "person_department_name")
    private String personDepartmentName;

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonFormerName() {
        return personFormerName;
    }

    public void setPersonFormerName(String personFormerName) {
        this.personFormerName = personFormerName;
    }

    public String getPersonUrl() {
        return personUrl;
    }

    public void setPersonUrl(String personUrl) {
        this.personUrl = personUrl;
    }

    public String getPersonBirthDate() {
        return personBirthDate;
    }

    public void setPersonBirthDate(String personBirthDate) {
        this.personBirthDate = personBirthDate;
    }

    public Byte getPersonGender() {
        return personGender;
    }

    public void setPersonGender(Byte personGender) {
        this.personGender = personGender;
    }

    public Byte getPersonMaritalStatus() {
        return personMaritalStatus;
    }

    public void setPersonMaritalStatus(Byte personMaritalStatus) {
        this.personMaritalStatus = personMaritalStatus;
    }

    public String getPersonCareer() {
        return personCareer;
    }

    public void setPersonCareer(String personCareer) {
        this.personCareer = personCareer;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonComment() {
        return personComment;
    }

    public void setPersonComment(String personComment) {
        this.personComment = personComment;
    }

    public Long getPersonDepartmentId() {
        return personDepartmentId;
    }

    public void setPersonDepartmentId(Long personDepartmentId) {
        this.personDepartmentId = personDepartmentId;
    }

    public String getPersonDepartmentName() {
        return personDepartmentName;
    }

    public void setPersonDepartmentName(String personDepartmentName) {
        this.personDepartmentName = personDepartmentName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * @return change_id
     */
    public Long getChangeId() {
        return changeId;
    }

    /**
     * @param changeId
     */
    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    /**
     * 获取旧的设备id
     *
     * @return old_device_id - 旧的设备id
     */
    public Long getOldDeviceId() {
        return oldDeviceId;
    }

    /**
     * 设置旧的设备id
     *
     * @param oldDeviceId 旧的设备id
     */
    public void setOldDeviceId(Long oldDeviceId) {
        this.oldDeviceId = oldDeviceId;
    }

    /**
     * 获取旧的设备编号
     *
     * @return old_device_number - 旧的设备编号
     */
    public String getOldDeviceNumber() {
        return oldDeviceNumber;
    }

    /**
     * 设置旧的设备编号
     *
     * @param oldDeviceNumber 旧的设备编号
     */
    public void setOldDeviceNumber(String oldDeviceNumber) {
        this.oldDeviceNumber = oldDeviceNumber;
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
     * @return person_id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * @param personId
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * @return task_id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return department_id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return photo_url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取运维人员
     *
     * @return ops - 运维人员
     */
    public String getOps() {
        return ops;
    }

    /**
     * 设置运维人员
     *
     * @param ops 运维人员
     */
    public void setOps(String ops) {
        this.ops = ops;
    }

    /**
     * 获取运维人员id
     *
     * @return ops_id - 运维人员id
     */
    public Long getOpsId() {
        return opsId;
    }

    /**
     * 设置运维人员id
     *
     * @param opsId 运维人员id
     */
    public void setOpsId(Long opsId) {
        this.opsId = opsId;
    }

    /**
     * 获取变更时间
     *
     * @return change_time - 变更时间
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * 设置变更时间
     *
     * @param changeTime 变更时间
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * 获取校验通过功能扩展属性(json格式)
     *
     * @return function_extend - 校验通过功能扩展属性(json格式)
     */
    public String getFunctionExtend() {
        return functionExtend;
    }

    /**
     * 设置校验通过功能扩展属性(json格式)
     *
     * @param functionExtend 校验通过功能扩展属性(json格式)
     */
    public void setFunctionExtend(String functionExtend) {
        this.functionExtend = functionExtend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}