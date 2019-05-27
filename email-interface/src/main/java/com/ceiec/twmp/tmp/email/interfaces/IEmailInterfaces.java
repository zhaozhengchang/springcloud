package com.ceiec.twmp.tmp.email.interfaces;

import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import org.springframework.web.bind.annotation.*;

/**
 * CreateDate：2018/4/24
 * Author：wenliang
 * Description: 该类主要用于提供邮件发送服务请求接口类封装
 **/
public interface IEmailInterfaces {

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    ResponseContent sendmail(@RequestBody  EmailSendRequest emailSendRequest);

}
