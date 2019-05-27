package com.ceiec.twmp.tmp.vo.person.result;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

/**
 * CreateDate：2019/3/6 23:01 </br>
 * Author：shihsh  </br>
 * Description: 人员信息列表 </br>
 **/


public class PersonInfoListResultVO extends PageParentVO {
    private static final long serialVersionUID = -8896201055758645631L;

    /** person Id **/
    private Long personId;

    /** person name **/
    private String personName;

    /** person number **/
    private String personNumber;

    private String personUrl;

    /** 监控状态 **/
    private Byte personStatus;

    private String personStatusName;

    private Byte departmentId;

    private String departmentName;

    private Byte taskDeleted;

    private String birthDate;

    private Integer gender;

    private String personIdCard;

    private String phone;

    private String email;

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

    public Byte getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(Byte personStatus) {
        this.personStatus = personStatus;
    }

    public String getPersonStatusName() {
        return personStatusName;
    }

    public void setPersonStatusName(String personStatusName) {
        this.personStatusName = personStatusName;
    }

    public Byte getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Byte departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Byte getTaskDeleted() {
        return taskDeleted;
    }

    public void setTaskDeleted(Byte taskDeleted) {
        this.taskDeleted = taskDeleted;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
