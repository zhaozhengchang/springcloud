//package com.ceiec.twmp.tmp.authorize.controller;
//
//import com.ceiec.twmp.tmp.authorize.api.IMyFeignClient;
//import com.ceiec.twmp.tmp.authorize.services.ILogonService;
//import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;
//import com.ceiec.twmp.tmp.common.enums.EOperateSystem;
//import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
//import com.ceiec.twmp.tmp.utils.annotations.IgnoreToken;
//import com.ceiec.twmp.tmp.utils.response.ResponseContent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * CreateDate：2018/5/14 <br/>
// * Author：wenliang <br/>
// * Description: process login & logout request
// **/
//@RestController
//public class LogonTwoController {
//
//    /** logon service interface */
//    @Autowired
//    private ILogonService logonService;
//
//    /** logger */
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//
//    @Autowired private IMyFeignClient myFeignClient;
//
//    /**
//     * login user
//     *
//     * @param loginInfo login information
//     * @return response message
//     */
//    @PostMapping("loginTwo")
//    @IgnoreToken
//    public ResponseContent login(@RequestBody LoginInfoVO loginInfo) {
//        String applicationName = EOperateSystem.getSystemEnum(loginInfo.getSystemCode()).getAppName(); //request system's micro service name
//        EmailSendRequest emailSendRequest = new EmailSendRequest();
//        emailSendRequest.setText("YY");
//        emailSendRequest.setSubject("Test email");
//        emailSendRequest.setHtmlFlag(true);
//        String[] recipients = {"luckykapok918@126.com"};
//        emailSendRequest.setRecipients(recipients);
//        ResponseContent responseContent = myFeignClient.sendmail(emailSendRequest);
//        return responseContent;
//    }
//
//
//}
