package com.ceiec.twmp.tmp.vo.person.add;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate：2019/3/1 15:46 </br>
 * Author：shihsh  </br>
 * Description: 被监控人员新建/编辑 </br>
 **/


public class PersonDataVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -5825776598373396504L;

    /** person Id **/
    private Long personId;

    /** person name **/
    private String personName;

    /** person number **/
    private String personNumber;

    /** department Id **/
    private Long departmentId;

    /** person ID card **/
    private String personIdCard;

    private String phone;

    private String career;

    private Byte gender;

    private Byte maritalStatus;

   private String  birthDate;

   private String formerName;

    private String address;

    private String email;

    private String comment;

    private String personUrl;

    // 删除的犯罪记录，多个用逗号(,)分隔
    private String criminalId;

    private List<PersonCriminalVO> criminalList;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPersonUrl() {
        return personUrl;
    }

    public void setPersonUrl(String personUrl) {
        this.personUrl = personUrl;
    }

    public String getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public List<PersonCriminalVO> getCriminalList() {
        return criminalList;
    }

    public void setCriminalList(List<PersonCriminalVO> criminalList) {
        this.criminalList = criminalList;
    }
}
