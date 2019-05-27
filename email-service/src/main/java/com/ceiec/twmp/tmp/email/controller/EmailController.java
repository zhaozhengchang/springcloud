package com.ceiec.twmp.tmp.email.controller;

import com.ceiec.twmp.tmp.email.ResponseType;
import com.ceiec.twmp.tmp.email.interfaces.IEmailInterfaces;
import com.ceiec.twmp.tmp.email.services.impl.EmailServiceImpl;
import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * CreateDate：2018/11/04
 * Author：wenliang
 * Description: 该类主要用于提供邮件发送服务controller
 **/
@RefreshScope
@RestController
public class EmailController implements IEmailInterfaces {

    /** logger */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private Environment env;
    @Override
    public ResponseContent sendmail(@RequestBody EmailSendRequest emailSendRequest) {
        boolean sendok = false;
        ResponseType responseType = ResponseType.FAILS;
        if (null != emailSendRequest){
            sendok = emailService.sendMail(emailSendRequest);
            if (sendok){
                responseType = ResponseType.SUCCESS;
            }
        }
        return  new ResponseContent(responseType);
    }

}