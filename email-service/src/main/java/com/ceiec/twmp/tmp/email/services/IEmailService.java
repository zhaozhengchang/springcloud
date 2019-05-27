package com.ceiec.twmp.tmp.email.services;


import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;

/**
 * CreateDate：2018/4/24
 * Author：wenliang
 * Description: 该类主要用于提供邮件发送服务接口类
 */
public interface IEmailService {

    /**
     * Send mail.
     *
     * @param emailSendRequest the email send request
     */
    public boolean sendMail(EmailSendRequest emailSendRequest);

}
