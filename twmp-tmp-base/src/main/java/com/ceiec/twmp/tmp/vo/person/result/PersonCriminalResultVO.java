package com.ceiec.twmp.tmp.vo.person.result;

import java.io.Serializable;

/**
 * CreateDate：2019/3/6 11:42 </br>
 * Author：shihsh  </br>
 * Description: 犯罪记录VO </br>
 **/


public class PersonCriminalResultVO implements Serializable {

    private static final long serialVersionUID = -5300959545359726520L;

    private Long criminalId;

    private Long personId;

    private String criminalTime;

    private int criminalType;

    private String criminalSite;

    private String criminalAct;

    private String lawEnforcementAgency;

    private String disposeTime;

    private String disposeResult;

    private String criminalNumber;

    public Long getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(Long criminalId) {
        this.criminalId = criminalId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getCriminalTime() {
        return criminalTime;
    }

    public void setCriminalTime(String criminalTime) {
        this.criminalTime = criminalTime;
    }

    public int getCriminalType() {
        return criminalType;
    }

    public void setCriminalType(int criminalType) {
        this.criminalType = criminalType;
    }

    public String getCriminalSite() {
        return criminalSite;
    }

    public void setCriminalSite(String criminalSite) {
        this.criminalSite = criminalSite;
    }

    public String getCriminalAct() {
        return criminalAct;
    }

    public void setCriminalAct(String criminalAct) {
        this.criminalAct = criminalAct;
    }

    public String getLawEnforcementAgency() {
        return lawEnforcementAgency;
    }

    public void setLawEnforcementAgency(String lawEnforcementAgency) {
        this.lawEnforcementAgency = lawEnforcementAgency;
    }

    public String getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(String disposeTime) {
        this.disposeTime = disposeTime;
    }

    public String getDisposeResult() {
        return disposeResult;
    }

    public void setDisposeResult(String disposeResult) {
        this.disposeResult = disposeResult;
    }

    public String getCriminalNumber() {
        return criminalNumber;
    }

    public void setCriminalNumber(String criminalNumber) {
        this.criminalNumber = criminalNumber;
    }
}
