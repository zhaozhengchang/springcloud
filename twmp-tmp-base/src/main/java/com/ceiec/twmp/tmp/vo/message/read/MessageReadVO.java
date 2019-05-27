package com.ceiec.twmp.tmp.vo.message.read;

import com.ceiec.twmp.tmp.common.dict.MessageStatus;

import java.util.Date;

/**
 * @version V1.0
 * @title: MessageReadVO </br>
 * @createDate：2019/3/12 10:14 </br>
 * @author：shihsh </br>
 * @description: 标记消息已读VO </br>
 **/


public class MessageReadVO {
    private String messageIds;

    private String[] messageIdArr;

    private String reader;

    private Long readerId;

    private Date readTime = new Date();

    private byte messageStatus = MessageStatus.read.value;

    public String getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(String messageIds) {
        this.messageIds = messageIds;
    }

    public String[] getMessageIdArr() {
        return messageIdArr;
    }

    public void setMessageIdArr(String[] messageIdArr) {
        this.messageIdArr = messageIdArr;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public byte getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
