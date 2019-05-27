package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sys_file")
public class TwmpSysFile {
    @Id
    @Column(name = "file_id")
    private Long fileId;

    /**
     * 文件类型
     */
    @Column(name = "file_type")
    private Byte fileType;

    /**
     * 关联的id
     */
    @Column(name = "link_id")
    private Long linkId;

    /**
     * 关联表的名
     */
    @Column(name = "link_table_name")
    private String linkTableName;

    /**
     * 关联表的关联字段
     */
    @Column(name = "link_table_column")
    private String linkTableColumn;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件存放地址
     */
    @Column(name = "file_path")
    private String filePath;

    private String creator;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标志（1未删除 0删除）
     */
    private Byte deleted;

    /**
     * @return file_id
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取文件类型
     *
     * @return file_type - 文件类型
     */
    public Byte getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取关联的id
     *
     * @return link_id - 关联的id
     */
    public Long getLinkId() {
        return linkId;
    }

    /**
     * 设置关联的id
     *
     * @param linkId 关联的id
     */
    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    /**
     * 获取关联表的名
     *
     * @return link_table_name - 关联表的名
     */
    public String getLinkTableName() {
        return linkTableName;
    }

    /**
     * 设置关联表的名
     *
     * @param linkTableName 关联表的名
     */
    public void setLinkTableName(String linkTableName) {
        this.linkTableName = linkTableName;
    }

    /**
     * 获取关联表的关联字段
     *
     * @return link_table_column - 关联表的关联字段
     */
    public String getLinkTableColumn() {
        return linkTableColumn;
    }

    /**
     * 设置关联表的关联字段
     *
     * @param linkTableColumn 关联表的关联字段
     */
    public void setLinkTableColumn(String linkTableColumn) {
        this.linkTableColumn = linkTableColumn;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件存放地址
     *
     * @return file_path - 文件存放地址
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件存放地址
     *
     * @param filePath 文件存放地址
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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