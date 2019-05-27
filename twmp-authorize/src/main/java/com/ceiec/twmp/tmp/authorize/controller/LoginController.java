package com.ceiec.twmp.tmp.authorize.controller;

import com.ceiec.twmp.tmp.authorize.ResponseType;
import com.ceiec.twmp.tmp.authorize.bo.LoginResultBO;
import com.ceiec.twmp.tmp.authorize.services.ILogonService;
import com.ceiec.twmp.tmp.utils.annotations.IgnoreToken;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;

import javax.servlet.http.HttpServletResponse;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: process login & logout request
 **/
@RestController
public class LoginController {

    /**
     * logon service interface
     */
    @Autowired
    private ILogonService logonService;

    /**
     * login user
     *
     * @param loginInfo login information
     * @return response message
     */
    @PostMapping("login")
    @IgnoreToken
    public ResponseContent login(@RequestBody LoginInfoVO loginInfo) {
        LoginResultBO loginResult = logonService.login(loginInfo);
        return new ResponseContent(loginResult.getResponseType(), loginResult.getResultInfo());
    }


    /**
     * logout user
     *
     * @param token login token
     * @return response message
     */
    @PostMapping("logout")
    public ResponseContent logout(@RequestHeader String token) {
        logonService.logout(TokenUtils.getUserName(token), TokenUtils.getSystemCode(token));
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
