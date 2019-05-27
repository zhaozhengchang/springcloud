package com.ceiec.twmp.tmp.vo.taskOutsideRecord;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-23 11:38
 **/
public class TaskOutSideRecordVO {

    private Long taskOutsideRecordId;

    private Long taskId;

    private Byte taskOutsideRecordType;

    private String eventTimeStr;

    private Date eventTime;

    private String eventAddress;

    private String eventComment;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskOutsideRecordId() {
        return taskOutsideRecordId;
    }

    public void setTaskOutsideRecordId(Long taskOutsideRecordId) {
        this.taskOutsideRecordId = taskOutsideRecordId;
    }

    public Byte getTaskOutsideRecordType() {
        return taskOutsideRecordType;
    }

    public void setTaskOutsideRecordType(Byte taskOutsideRecordType) {
        this.taskOutsideRecordType = taskOutsideRecordType;
    }

    public String getEventTimeStr() {
        return eventTimeStr;
    }

    public void setEventTimeStr(String eventTimeStr) {
        this.eventTimeStr = eventTimeStr;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventComment() {
        return eventComment;
    }

    public void setEventComment(String eventComment) {
        this.eventComment = eventComment;
    }
}
