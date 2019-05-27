package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_monitor_task_team_ef")
public class TwmpMonitorTaskTeamEf extends BaseEntity {
    @Id
    @Column(name = "team_id")
    private Long teamId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 监控任务参数变更id
     */
    @Column(name = "task_change_id")
    private Integer taskChangeId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像照片url
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * 与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)
     */
    @Column(name = "relation_type")
    private Byte relationType;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系邮件
     */
    private String email;

    /**
     * 联系地址
     */
    private String address;

    /**
     * @return team_id
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId 监护人员Id
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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
    public Integer getTaskChangeId() {
        return taskChangeId;
    }

    /**
     * 设置监控任务参数变更id
     *
     * @param taskChangeId 监控任务参数变更id
     */
    public void setTaskChangeId(Integer taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取头像照片url
     *
     * @return photo_url - 头像照片url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 设置头像照片url
     *
     * @param photoUrl 头像照片url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)
     *
     * @return relation_type - 与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)
     */
    public Byte getRelationType() {
        return relationType;
    }

    /**
     * 设置与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)
     *
     * @param relationType 与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)
     */
    public void setRelationType(Byte relationType) {
        this.relationType = relationType;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系邮件
     *
     * @return email - 联系邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置联系邮件
     *
     * @param email 联系邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取联系地址
     *
     * @return address - 联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置联系地址
     *
     * @param address 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

}