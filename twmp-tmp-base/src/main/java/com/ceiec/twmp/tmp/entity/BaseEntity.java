package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: base entity
 * @create 2019-03-05 9:17
 **/
public class BaseEntity {

    private String creator;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "create_time")
    private Date createTime;

    private String updater;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除标志（1未删除 0删除）
     */
    private Byte deleted;


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 获取删除标志（1未删除 0删除）
     *
     * @return deleted - 删除标志（1未删除 0删除）
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志（1未删除 0删除）
     *
     * @param deleted 删除标志（1未删除 0删除）
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}
