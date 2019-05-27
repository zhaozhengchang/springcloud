package com.ceiec.twmp.tmp.vo.message.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: MessageListQueryVO </br>
 * @createDate：2019/3/11 14:46 </br>
 * @author：shihsh </br>
 * @description: 消息查询VO </br>
 **/


public class MessageListQueryVO extends PageParentVO {


    private static final long serialVersionUID = -5024618948530314503L;

    private Long userId;

    private Byte messageType;

    private Byte messageSubType;

    private Byte messageStatus;

    private String startTime;

    private Date startDate;

    private String endTime;

    private Date endDate;

    private Integer number;

    private List<Long> ownDepartmentIds;

    private List<Byte> messageSubTypes;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Byte getMessageSubType() {
        return messageSubType;
    }

    public void setMessageSubType(Byte messageSubType) {
        this.messageSubType = messageSubType;
    }

    public Byte getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Long> getOwnDepartmentIds() {
        return ownDepartmentIds;
    }

    public void setOwnDepartmentIds(List<Long> ownDepartmentIds) {
        this.ownDepartmentIds = ownDepartmentIds;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Byte> getMessageSubTypes() {
        return messageSubTypes;
    }

    public void setMessageSubTypes(List<Byte> messageSubTypes) {
        this.messageSubTypes = messageSubTypes;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
