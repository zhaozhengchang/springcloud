package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "twmp_monitor_task_fence_ef")
public class TwmpMonitorTaskFenceEf extends BaseEntity {
    @Id
    @Column(name = "fence_id")
    private Long fenceId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 监控任务参数变更id
     */
    @Column(name = "task_change_id")
    private Long taskChangeId;

    /**
     * 启动状态(1,启动 2，停用)
     */
    @Column(name = "fence_status")
    private Byte fenceStatus;

    /**
     * 围栏类型(1,禁止入内 2，禁止外出)
     */
    @Column(name = "fence_type")
    private Byte fenceType;

    /**
     * 围栏开始时间
     */
    @Column(name = "start_time")
    private String startTime;

    /**
     * 围栏结束时间
     */
    @Column(name = "end_time")
    private String endTime;

    /**
     * 围栏描述
     */
    private String comment;

    /**
     * @return fence_id
     */
    public Long getFenceId() {
        return fenceId;
    }

    /**
     * @param fenceId 围栏Id
     */
    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
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
     * 获取监控任务参数变更id
     *
     * @return task_change_id - 监控任务参数变更id
     */
    public Long getTaskChangeId() {
        return taskChangeId;
    }

    /**
     * 设置监控任务参数变更id
     *
     * @param taskChangeId 监控任务参数变更id
     */
    public void setTaskChangeId(Long taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    /**
     * 获取启动状态(1,启动 2，停用)
     *
     * @return fence_status - 启动状态(1,启动 2，停用)
     */
    public Byte getFenceStatus() {
        return fenceStatus;
    }

    /**
     * 设置启动状态(1,启动 2，停用)
     *
     * @param fenceStatus 启动状态(1,启动 2，停用)
     */
    public void setFenceStatus(Byte fenceStatus) {
        this.fenceStatus = fenceStatus;
    }

    /**
     * 获取围栏类型(1,禁止入内 2，禁止外出)
     *
     * @return fence_type - 围栏类型(1,禁止入内 2，禁止外出)
     */
    public Byte getFenceType() {
        return fenceType;
    }

    /**
     * 设置围栏类型(1,禁止入内 2，禁止外出)
     *
     * @param fenceType 围栏类型(1,禁止入内 2，禁止外出)
     */
    public void setFenceType(Byte fenceType) {
        this.fenceType = fenceType;
    }

    /**
     * 获取围栏开始时间
     *
     * @return startTime - 围栏开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置围栏开始时间
     *
     * @param startTime - 围栏开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }



    /**
     * 获取围栏结束时间
     *
     * @param endTime 围栏结束时间
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置围栏结束时间
     *
     * @param times 围栏结束时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取围栏描述
     *
     * @return comment - 围栏描述
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置围栏描述
     *
     * @param comment 围栏描述
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}