package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_bs_person_ef")
public class TwmpBsPersonEf extends BaseEntity {
    @Id
    @Column(name = "person_id")
    private Long personId;

    /**
     * 设备编号
     */
    @Column(name = "device_id")
    private Long deviceId;

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
    @Column(name = "birth_date")
    private String birthDate;

    /**
     * 性别（1男，2女，3其他）
     */
    private Byte gender;

    /**
     * 婚姻状态（1，已婚 2，未婚）
     */
    @Column(name = "marital_status")
    private Byte maritalStatus;

    /**
     * 职业
     */
    private String career;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系邮件
     */
    private String email;

    /**
     * 联系家庭地址
     */
    private String address;

    /**
     * 备注
     */
    private String comment;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;



    /**
     * @return person_id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * @param personId 被监控人员id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * @return deviceId
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId 设备Id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取人员编号
     *
     * @return person_number - 人员编号
     */
    public String getPersonNumber() {
        return personNumber;
    }

    /**
     * 设置人员编号
     *
     * @param personNumber 人员编号
     */
    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    /**
     * 获取身份证号
     *
     * @return person_id_card - 身份证号
     */
    public String getPersonIdCard() {
        return personIdCard;
    }

    /**
     * 设置身份证号
     *
     * @param personIdCard 身份证号
     */
    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    /**
     * 获取被监控人姓名
     *
     * @return person_name - 被监控人姓名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置被监控人姓名
     *
     * @param personName 被监控人姓名
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取曾用名
     *
     * @return person_former_name - 曾用名
     */
    public String getPersonFormerName() {
        return personFormerName;
    }

    /**
     * 设置曾用名
     *
     * @param personFormerName 曾用名
     */
    public void setPersonFormerName(String personFormerName) {
        this.personFormerName = personFormerName;
    }

    /**
     * 获取照片url(多张图片地址，以，分割)
     *
     * @return person_url - 照片url(多张图片地址，以，分割)
     */
    public String getPersonUrl() {
        return personUrl;
    }

    /**
     * 设置照片url(多张图片地址，以，分割)
     *
     * @param personUrl 照片url(多张图片地址，以，分割)
     */
    public void setPersonUrl(String personUrl) {
        this.personUrl = personUrl;
    }

    /**
     * 获取出生日期
     *
     * @return birth_date - 出生日期
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 设置出生日期
     *
     * @param birthDate 出生日期
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取性别（1男，2女，3其他）
     *
     * @return gender - 性别（1男，2女，3其他）
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别（1男，2女，3其他）
     *
     * @param gender 性别（1男，2女，3其他）
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取婚姻状态（1，已婚 2，未婚）
     *
     * @return marital_status - 婚姻状态（1，已婚 2，未婚）
     */
    public Byte getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状态（1，已婚 2，未婚）
     *
     * @param maritalStatus 婚姻状态（1，已婚 2，未婚）
     */
    public void setMaritalStatus(Byte maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取职业
     *
     * @return career - 职业
     */
    public String getCareer() {
        return career;
    }

    /**
     * 设置职业
     *
     * @param career 职业
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系邮件
     *
     * @return email - 联系邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置联系邮件
     *
     * @param email 联系邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取联系家庭地址
     *
     * @return address - 联系家庭地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置联系家庭地址
     *
     * @param address 联系家庭地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
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

}