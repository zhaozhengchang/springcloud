package com.ceiec.twmp.tmp.vo.monitor.result;

import com.ceiec.twmp.tmp.vo.person.result.PersonCriminalResultVO;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate：2019/3/7 11:30 </br>
 * Author：shihsh  </br>
 * Description: 人员基本信息 </br>
 **/


public class PersonBasicDetailResultVO implements Serializable {
    private static final long serialVersionUID = 8830222579991297644L;

    private Long personId;

    private String personName;

    private String personNumber;

    private String personUrl;

    private String birthDate;

    private Byte gender;

    private Byte maritalStatus;

    private String longtitude;

    private String latitude;

    private String career;

    private String personIdCard;

    private String formerName;

    private String departmentId;

    private String phone;

    private String email;

    private String address;

    private Long deviceId;

    private String deviceNumber;

    private List<PersonCriminalResultVO> criminalList;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Byte  getGender() {
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

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonCriminalResultVO> getCriminalList() {
        return criminalList;
    }

    public void setCriminalList(List<PersonCriminalResultVO> criminalList) {
        this.criminalList = criminalList;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}
