package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.authorize_interface.controller.IAuthorizeBaseController;
import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.services.IAuthorizeService;
import com.ceiec.twmp.tmp.utils.annotations.IgnoreToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//import com.ceiec.twmp.tmp.mqtt.MqttGateway;

/**
 * CreateDate：2018/5/14
 * Author：wenliang
 * Description: callback controller for project sentiment-authorize
 **/
@RestController
public class AuthorizeController implements IAuthorizeBaseController {

    /**
     * authorize service interface
     */
    @Autowired
    private IAuthorizeService authorizeService;

    /**
     * get datasource information for login
     *
     * @return datasource information
     */
    @Override
    @IgnoreToken
    public DatasourceInfoVO getDatasourceInfo() {
        return authorizeService.getDatasourceInfo();
    }

    /**
     * get user table fields information
     *
     * @return table fields information
     */
    @Override
    @IgnoreToken
    public UserTableInfoVO getUserTableInfo() {
        return authorizeService.getUserTableInfo();
    }

    /**
     * process return information if user is kicked login successfully
     *
     * @param loginResult kicked login success information
     * @return process result code
     */
    @Override
    @IgnoreToken
    public String processLoginSuccess(@RequestBody LoginResultVO loginResult) {
        return authorizeService.processLoginSuccess(loginResult);
    }

    /**
     * process logout request
     *
     * @param token login token
     * @return process result code
     */
    @Override
    public String processLogout(@RequestHeader String token) {
        return authorizeService.processLogout(token);
    }
}
