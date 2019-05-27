package com.ceiec.twmp.tmp.vo.message.result;

import java.io.Serializable;

/**
 * @version V1.0
 * @title: MessageListResultVO </br>
 * @createDate：2019/3/11 14:55 </br>
 * @author：shihsh </br>
 * @description: 消息查询结果 </br>
 **/


public class MessageListResultVO implements Serializable {

    private static final long serialVersionUID = -2078367948125990790L;

    private Long messageId;

    /*
     * 消息类型
     */
    private String messageType;

    private String messageComment;

    private String messageTime;

    private  Long readerId;

    private String reader;

    private  String readerTime;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageComment() {
        return messageComment;
    }

    public void setMessageComment(String messageComment) {
        this.messageComment = messageComment;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getReaderTime() {
        return readerTime;
    }

    public void setReaderTime(String readerTime) {
        this.readerTime = readerTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
