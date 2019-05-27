package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_monitor_task_outside_record_ef")
public class TwmpMonitorTaskOutsideRecordEf extends BaseEntity {
    @Id
    @Column(name = "task_outside_record_id")
    private Long taskOutsideRecordId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）
     */
    @Column(name = "task_outside_record_type")
    private Byte taskOutsideRecordType;

    /**
     * 事件发生地址
     */
    @Column(name = "event_address")
    private String eventAddress;

    /**
     * 事件发生事件
     */
    @Column(name = "event_time")
    private Date eventTime;

    /**
     * 事件情况描述
     */
    @Column(name = "event_comment")
    private String eventComment;

    /**
     * @return task_outside_record_id
     */
    public Long getTaskOutsideRecordId() {
        return taskOutsideRecordId;
    }

    /**
     * @param taskOutsideRecordId
     */
    public void setTaskOutsideRecordId(Long taskOutsideRecordId) {
        this.taskOutsideRecordId = taskOutsideRecordId;
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
     * 获取类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）
     *
     * @return task_outside_record_type - 类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）
     */
    public Byte getTaskOutsideRecordType() {
        return taskOutsideRecordType;
    }

    /**
     * 设置类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）
     *
     * @param taskOutsideRecordType 类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）
     */
    public void setTaskOutsideRecordType(Byte taskOutsideRecordType) {
        this.taskOutsideRecordType = taskOutsideRecordType;
    }

    /**
     * 获取事件发生地址
     *
     * @return event_address - 事件发生地址
     */
    public String getEventAddress() {
        return eventAddress;
    }

    /**
     * 设置事件发生地址
     *
     * @param eventAddress 事件发生地址
     */
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    /**
     * 获取事件发生事件
     *
     * @return event_time - 事件发生事件
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * 设置事件发生事件
     *
     * @param eventTime 事件发生事件
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * 获取事件情况描述
     *
     * @return event_comment - 事件情况描述
     */
    public String getEventComment() {
        return eventComment;
    }

    /**
     * 设置事件情况描述
     *
     * @param eventComment 事件情况描述
     */
    public void setEventComment(String eventComment) {
        this.eventComment = eventComment;
    }
}