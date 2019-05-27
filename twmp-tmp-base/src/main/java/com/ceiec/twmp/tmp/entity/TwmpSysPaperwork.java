package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-11 17:38
 * Description:
 **/
@Table(name = "twmp_sys_paperwork")
public class TwmpSysPaperwork {

    /**
     * 参数id
     */
    @Id
    @Column(name = "paperwork_id")
    private Long paperworkId;

    @Column(name = "paperwork_name")
    private String paperworkName;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "comment")
    private String   comment;

    @Column(name = "creator")
    private String creator ;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "deleted")
    private Byte deleted;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "updater_id")
    private Long updaterId;

    public Long getPaperworkId() {
        return paperworkId;
    }

    public void setPaperworkId(Long paperworkId) {
        this.paperworkId = paperworkId;
    }

    public String getPaperworkName() {
        return paperworkName;
    }

    public void setPaperworkName(String paperworkName) {
        this.paperworkName = paperworkName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }
}
