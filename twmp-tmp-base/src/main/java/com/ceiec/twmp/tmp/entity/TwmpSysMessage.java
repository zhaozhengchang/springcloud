package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sys_message")
public class TwmpSysMessage {
    @Id
    @Column(name = "message_id")
    private Long messageId;

    /**
     * 业务id
     */
    @Column(name = "business_id")
    private Long businessId;

    /**
     * 消息内容
     */
    @Column(name = "message_comment")
    private String messageComment;

    /**
     * 消息类型
     */
    @Column(name = "message_type")
    private Byte messageType;

    /**
     * 消息子类型
     */
    @Column(name = "message_sub_type")
    private Byte messageSubType;

    /**
     * 消息状态
     */
    @Column(name = "message_status")
    private Byte messageStatus;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 消息时间
     */
    @Column(name = "message_time")
    private Date messageTime;

    /**
     * 已读人
     */
    private String reader;

    /**
     * 已读人id
     */
    @Column(name = "reader_id")
    private Long readerId;

    /**
     * 已读时间
     */
    @Column(name = "read_time")
    private Date readTime;

    /**
     * @return message_id
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取业务id
     *
     * @return business_id - 业务id
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * 设置业务id
     *
     * @param businessId 业务id
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取消息内容
     *
     * @return message_comment - 消息内容
     */
    public String getMessageComment() {
        return messageComment;
    }

    /**
     * 设置消息内容
     *
     * @param messageComment 消息内容
     */
    public void setMessageComment(String messageComment) {
        this.messageComment = messageComment;
    }

    /**
     * 获取消息类型
     *
     * @return message_type - 消息类型
     */
    public Byte getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型
     *
     * @param messageType 消息类型
     */
    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取消息子类型
     *
     * @return message_sub_type - 消息子类型
     */
    public Byte getMessageSubType() {
        return messageSubType;
    }

    /**
     * 设置消息子类型
     *
     * @param messageSubType 消息子类型
     */
    public void setMessageSubType(Byte messageSubType) {
        this.messageSubType = messageSubType;
    }

    /**
     * 获取消息状态
     *
     * @return message_status - 消息状态
     */
    public Byte getMessageStatus() {
        return messageStatus;
    }

    /**
     * 设置消息状态
     *
     * @param messageStatus 消息状态
     */
    public void setMessageStatus(Byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取消息时间
     *
     * @return message_time - 消息时间
     */
    public Date getMessageTime() {
        return messageTime;
    }

    /**
     * 设置消息时间
     *
     * @param messageTime 消息时间
     */
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * 获取已读人
     *
     * @return reader - 已读人
     */
    public String getReader() {
        return reader;
    }

    /**
     * 设置已读人
     *
     * @param reader 已读人
     */
    public void setReader(String reader) {
        this.reader = reader;
    }

    /**
     * 获取已读人id
     *
     * @return reader_id - 已读人id
     */
    public Long getReaderId() {
        return readerId;
    }

    /**
     * 设置已读人id
     *
     * @param readerId 已读人id
     */
    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    /**
     * 获取已读时间
     *
     * @return read_time - 已读时间
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * 设置已读时间
     *
     * @param readTime 已读时间
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}