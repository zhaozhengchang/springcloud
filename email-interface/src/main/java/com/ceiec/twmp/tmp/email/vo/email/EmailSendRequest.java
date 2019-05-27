package com.ceiec.twmp.tmp.email.vo.email;

import java.util.List;

/**
 * CreateDate：2018/4/24
 * Author：wenliang
 * Description: 该类主要用于提供邮件发送服务请求对象类封装
 */
public class EmailSendRequest {
    /** 收件人地址*/
    private String[] recipients ;
    /** 邮件主题*/
    private String subject ;
    /** 是否包含HTML文本*/
    private boolean htmlFlag;
    /** 邮件正文*/
    private String text ;
    /** 插入的图片对象集*/
    private List<AttachedImage> attachedImages ;
    /** 插入的附件对象集*/
    private List<AttendedFiles> attendedFiles;

    /**
     * Instantiates a new Email send request.
     */
    public EmailSendRequest(){
    }


    /**
     * Get recipients string [ ].
     *
     * @return the string [ ]
     */
    public String[] getRecipients() {
        return recipients;
    }

    /**
     * Sets recipients.
     *
     * @param recipients the recipients
     */
    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Is html flag boolean.
     *
     * @return the boolean
     */
    public boolean getHtmlFlag() {
        return htmlFlag;
    }

    /**
     * Sets html flag.
     *
     * @param htmlFlag the html flag
     */
    public void setHtmlFlag(boolean htmlFlag) {
        this.htmlFlag = htmlFlag;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets attached images.
     *
     * @return the attached images
     */
    public List<AttachedImage> getAttachedImages() {
        return attachedImages;
    }

    /**
     * Sets attached images.
     *
     * @param attachedImages the attached images
     */
    public void setAttachedImages(List<AttachedImage> attachedImages) {
        this.attachedImages = attachedImages;
    }

    /**
     * Gets attended files.
     *
     * @return the attended files
     */
    public List<AttendedFiles> getAttendedFiles() {
        return attendedFiles;
    }

    /**
     * Sets attended files.
     *
     * @param attendedFiles the attended files
     */
    public void setAttendedFiles(List<AttendedFiles> attendedFiles) {
        this.attendedFiles = attendedFiles;
    }

}

