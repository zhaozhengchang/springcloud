package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;

@Table(name = "twmp_monitor_task_criminal_ef")
public class TwmpMonitorTaskCriminalEf {
    @Id
    @Column(name = "task_criminal_id")
    private Long taskCriminalId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_change_id")
    private Long taskChangeId;

    /**
     * 犯罪记录id
     */
    @Column(name = "criminal_id")
    private Long criminalId;

    /**
     * @return task_criminal_id
     */
    public Long getTaskCriminalId() {
        return taskCriminalId;
    }

    /**
     * @param taskCriminalId
     */
    public void setTaskCriminalId(Long taskCriminalId) {
        this.taskCriminalId = taskCriminalId;
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
     * @return task_change_id
     */
    public Long getTaskChangeId() {
        return taskChangeId;
    }

    /**
     * @param taskChangeId
     */
    public void setTaskChangeId(Long taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    /**
     * 获取犯罪记录id
     *
     * @return criminal_id - 犯罪记录id
     */
    public Long getCriminalId() {
        return criminalId;
    }

    /**
     * 设置犯罪记录id
     *
     * @param criminalId 犯罪记录id
     */
    public void setCriminalId(Long criminalId) {
        this.criminalId = criminalId;
    }
}