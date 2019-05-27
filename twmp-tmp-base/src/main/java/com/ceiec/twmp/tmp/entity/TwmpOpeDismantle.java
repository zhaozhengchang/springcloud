package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_ope_dismantle")
public class TwmpOpeDismantle {
    @Id
    @Column(name = "dismantle_id")
    private Long dismantleId;

    /**
     * 被监控人员id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    /**
     * 拆机安装示意图
     */
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
     * 拆机时间
     */
    @Column(name = "dismantle_time")
    private Date dismantleTime;


    @Column(name = "create_time")
    private Date createTime;

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
     * @return dismantle_id
     */
    public Long getDismantleId() {
        return dismantleId;
    }

    /**
     * @param dismantleId
     */
    public void setDismantleId(Long dismantleId) {
        this.dismantleId = dismantleId;
    }

    /**
     * 获取被监控人员id
     *
     * @return person_id - 被监控人员id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置被监控人员id
     *
     * @param personId 被监控人员id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
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
     * 获取拆机安装示意图
     *
     * @return photo_url - 拆机安装示意图
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 设置拆机安装示意图
     *
     * @param photoUrl 拆机安装示意图
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
     * 获取拆机时间
     *
     * @return dismantle_time - 拆机时间
     */
    public Date getDismantleTime() {
        return dismantleTime;
    }

    /**
     * 设置拆机时间
     *
     * @param dismantleTime 拆机时间
     */
    public void setDismantleTime(Date dismantleTime) {
        this.dismantleTime = dismantleTime;
    }
}