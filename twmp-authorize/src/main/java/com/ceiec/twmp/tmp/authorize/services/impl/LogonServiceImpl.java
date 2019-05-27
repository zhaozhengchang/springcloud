package com.ceiec.twmp.tmp.authorize.services.impl;

import com.ceiec.twmp.tmp.authorize.ResponseType;
import com.ceiec.twmp.tmp.authorize.bo.LoginResultBO;
import com.ceiec.twmp.tmp.authorize.dao.DatasourceFactory;
import com.ceiec.twmp.tmp.authorize.dao.LogonDAO;
import com.ceiec.twmp.tmp.authorize.enums.EDeleteStatus;
import com.ceiec.twmp.tmp.authorize.services.ILogonService;
import com.ceiec.twmp.tmp.authorize.vo.UserInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.common.enums.EOperateSystem;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.utils.tools.DESUtils;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: logon service implementation
 **/
@Service
public class LogonServiceImpl implements ILogonService {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * login user
     *
     * @param loginInfo login information
     * @return login result
     */
    @Override
    public synchronized LoginResultBO login(LoginInfoVO loginInfo) {
        //initialize return info
        LoginResultBO result = new LoginResultBO();
        //get datasource
        loginInfo.setSystemCode(1);
        String applicationName = EOperateSystem.getSystemEnum(loginInfo.getSystemCode()).getAppName(); //request system's micro service name
        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<DatasourceInfoVO> datasourceResponse = restTemplate.exchange("http://" + applicationName + "/getDatasourceInfo", HttpMethod.GET, httpEntity, DatasourceInfoVO.class);
        DatasourceInfoVO datasourceInfo = datasourceResponse.getBody();
        DriverManagerDataSource dataSource = DatasourceFactory.getDatasource(datasourceInfo);
        //get table info
        ResponseEntity<UserTableInfoVO> userTableResponse = restTemplate.exchange("http://" + applicationName + "/getUserTableInfo", HttpMethod.GET, httpEntity, UserTableInfoVO.class);
        loginInfo.setUserTableInfo(userTableResponse.getBody());

        //get matched user from database
        LogonDAO logonDAO = new LogonDAO(dataSource);
        UserTableInfoVO tableInfo = loginInfo.getUserTableInfo();
        List<UserInfoVO> matchedUsers = logonDAO.getLoginUserInfo(loginInfo.getUserName(), tableInfo);
        if (CollectionUtils.isEmpty(matchedUsers)) {
            result.setResponseType(ResponseType.NOT_EXIST);
            result.getResultInfo().setLoginSuccess(false);
            return result;
        }
        if (matchedUsers.size() > 1) {
            result.setResponseType(ResponseType.UNKNOWN);
            result.getResultInfo().setFurtherInfo("find more than one user with name: " + loginInfo.getUserName());
            result.getResultInfo().setLoginSuccess(false);
            return result;
        }
        UserInfoVO matchedUser = matchedUsers.get(0); //matched user

        String passWordEnCrypt = DESUtils.encrypt(loginInfo.getPassword());
        //verify request password
        if (!(matchedUser.getPassword().equals(passWordEnCrypt))) { //password is incorrect
            //if user has wrong record, update it
            if (!StringUtils.isNotBlank(tableInfo.getLoginFailCount()) && !StringUtils.isNotBlank(tableInfo.getLastLoginFailTime())) {
                logonDAO.updateWrongRecord(loginInfo.getUserName(), tableInfo);
            }

            //return login result
            result.setResponseType(ResponseType.PASSWORD_UNMATCHED);
            result.getResultInfo().setLoginSuccess(false);
            return result;
        } else { //password is correct
            //if user has wrong record, empty it
            if (!StringUtils.isNotBlank(tableInfo.getLoginFailCount()) && !StringUtils.isNotBlank(tableInfo.getLastLoginFailTime())) {
                logonDAO.emptyWrongRecords(loginInfo.getUserName(), tableInfo);
            }
        }

        //verify user's status
        if (StringUtils.isNotBlank(tableInfo.getDeleted())) { //field exist
            if (matchedUser.getDeleted() == null) {
                throw new BusinessException("user(" + loginInfo.getUserName() + ") delete status is necessary in database");
            }
            if (matchedUser.getDeleted() == EDeleteStatus.DELETED.getStatusValue()) {
                result.setResponseType(ResponseType.DELETED);
                result.getResultInfo().setLoginSuccess(false);
                return result;
            }
        }

        //return login success result
        LoginResultVO resultInfo = new LoginResultVO();
        resultInfo.setLoginSuccess(true);
        resultInfo.setUserId(matchedUser.getUserId());
        resultInfo.setUserName(loginInfo.getUserName());
        resultInfo.setToken(TokenUtils.generateToken(loginInfo.getSystemCode(), matchedUser.getUserId().toString(), loginInfo.getUserName(), passWordEnCrypt));
        resultInfo.setLoginTime(new Date());
        result.setResponseType(ResponseType.SUCCESS);
        result.setResultInfo(resultInfo);



        HttpEntity<LoginResultVO> requestEntity = new HttpEntity<>(resultInfo, new HttpHeaders());
        restTemplate.postForEntity("http://" + applicationName + "/processLoginSuccess", requestEntity, String.class);



        return result;
    }


    /**
     * logout user
     *
     * @param userName logout user name
     * @param systemCode logout system code
     */
    @Override
    public void logout(String userName, int systemCode) {

    }
}
