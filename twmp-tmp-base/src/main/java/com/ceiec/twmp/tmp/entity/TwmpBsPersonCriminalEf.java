package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "twmp_bs_person_criminal_ef")
public class TwmpBsPersonCriminalEf extends BaseEntity{
    @Id
    @Column(name = "criminal_id")
    private Long criminalId;

    /**
     * 被监控人员id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * 违法类型（1财产侵犯？后续询问用户）
     */
    @Column(name = "criminal_type")
    private Byte criminalType;

    /**
     * 违法时间
     *
     */
    @Column(name = "criminal_time")
    private Date criminalTime;

    /**
     * 违法地点
     */
    @Column(name = "criminal_site")
    private String criminalSite;

    /**
     * 违法行为
     */
    @Column(name = "criminal_act")
    private String criminalAct;

    /**
     * 执法机关
     */
    @Column(name = "law_enforcement_agency")
    private String lawEnforcementAgency;

    /**
     * 违法代码
     */
    @Column(name = "criminal_number")
    private String criminalNumber;

    /**
     * 处理时间
     *  Date类型改为String类型 shihsh 2019/3/6 16:30
     */
    @Column(name = "dispose_time")
    private String disposeTime;

    /**
     * 处理结果
     */
    @Column(name = "dispose_result")
    private String disposeResult;


    /**
     * @return criminal_id
     */
    public Long getCriminalId() {
        return criminalId;
    }

    /**
     * @param criminalId 犯罪记录Id
     */
    public void setCriminalId(Long criminalId) {
        this.criminalId = criminalId;
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
     * 获取违法类型（1财产侵犯？后续询问用户）
     *
     * @return criminal_type - 违法类型（1财产侵犯？后续询问用户）
     */
    public Byte getCriminalType() {
        return criminalType;
    }

    /**
     * 设置违法类型（1财产侵犯？后续询问用户）
     *
     * @param criminalType 违法类型（1财产侵犯？后续询问用户）
     */
    public void setCriminalType(Byte criminalType) {
        this.criminalType = criminalType;
    }

    /**
     * 获取违法时间
     *
     * @return criminal_time - 违法时间
     */
    public Date getCriminalTime() {
        return criminalTime;
    }

    /**
     * 设置违法时间
     *
     * @param criminalTime 违法时间
     */
    public void setCriminalTime(Date criminalTime) {
        this.criminalTime = criminalTime;
    }

    /**
     * 获取违法地点
     *
     * @return criminal_site - 违法地点
     */
    public String getCriminalSite() {
        return criminalSite;
    }

    /**
     * 设置违法地点
     *
     * @param criminalSite 违法地点
     */
    public void setCriminalSite(String criminalSite) {
        this.criminalSite = criminalSite;
    }

    /**
     * 获取违法行为
     *
     * @return criminal_act - 违法行为
     */
    public String getCriminalAct() {
        return criminalAct;
    }

    /**
     * 设置违法行为
     *
     * @param criminalAct 违法行为
     */
    public void setCriminalAct(String criminalAct) {
        this.criminalAct = criminalAct;
    }

    /**
     * 获取执法机关
     *
     * @return law_enforcement_agency - 执法机关
     */
    public String getLawEnforcementAgency() {
        return lawEnforcementAgency;
    }

    /**
     * 设置执法机关
     *
     * @param lawEnforcementAgency 执法机关
     */
    public void setLawEnforcementAgency(String lawEnforcementAgency) {
        this.lawEnforcementAgency = lawEnforcementAgency;
    }

    /**
     * 获取违法代码
     *
     * @return criminal_number - 违法代码
     */
    public String getCriminalNumber() {
        return criminalNumber;
    }

    /**
     * 设置违法代码
     *
     * @param criminalNumber 违法代码
     */
    public void setCriminalNumber(String criminalNumber) {
        this.criminalNumber = criminalNumber;
    }

    /**
     * 获取处理时间
     *
     * @return dispose_time - 处理时间
     */
    public String getDisposeTime() {
        return disposeTime;
    }

    /**
     * 设置处理时间
     *
     * @param disposeTime 处理时间
     */
    public void setDisposeTime(String disposeTime) {
        this.disposeTime = disposeTime;
    }

    /**
     * 获取处理结果
     *
     * @return dispose_result - 处理结果
     */
    public String getDisposeResult() {
        return disposeResult;
    }

    /**
     * 设置处理结果
     *
     * @param disposeResult 处理结果
     */
    public void setDisposeResult(String disposeResult) {
        this.disposeResult = disposeResult;
    }

}