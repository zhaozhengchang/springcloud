package com.ceiec.twmp.tmp.authorize_interface.controller;

import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: base controller interface, any project(s) need to use twmp-authorize project need to implement this interface
 **/
public interface IAuthorizeBaseController {

    /**
     * get datasource information for login
     *
     * @return datasource information
     */
    @GetMapping("getDatasourceInfo")
    DatasourceInfoVO getDatasourceInfo();

    /**
     * get user table fields information
     *
     * @return table fields information
     */
    @GetMapping("getUserTableInfo")
    UserTableInfoVO getUserTableInfo();

    /**
     * process return information if user is login successfully
     *
     * @param loginResult login success information
     * @return process result code
     */
    @PostMapping("processLoginSuccess")
    String processLoginSuccess(@RequestBody LoginResultVO loginResult);


    /**
     * process logout request
     *
     * @param token login token
     * @return process result code
     */
    @PostMapping("processLogout")
    String processLogout(@RequestHeader String token);
}
