package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "twmp_dev_lifecycle")
public class TwmpDevLifecycle {
    @Id
    @Column(name = "lifecycle_id")
    private Long lifecycleId;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）
     */
    @Column(name = "lifecycle_event_type")
    private Byte lifecycleEventType;

    /**
     * 事件
     */
    private String event;

    private String creator;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return lifecycle_id
     */
    public Long getLifecycleId() {
        return lifecycleId;
    }

    /**
     * @param lifecycleId
     */
    public void setLifecycleId(Long lifecycleId) {
        this.lifecycleId = lifecycleId;
    }

    /**
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）
     *
     * @return LifecycleEventType - 事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）
     */
    public Byte getLifecycleEventType() {
        return lifecycleEventType;
    }

    /**
     * 设置事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）
     *
     * @param lifecycleEventType 事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）
     */
    public void setLifecycleEventType(Byte lifecycleEventType) {
        this.lifecycleEventType = lifecycleEventType;
    }

    /**
     * 获取事件
     *
     * @return event - 事件
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置事件
     *
     * @param event 事件
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return creator_id
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}