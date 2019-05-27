package com.ceiec.twmp.tmp.authorize.services;

import com.ceiec.twmp.tmp.authorize.bo.LoginResultBO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: interface for logon service
 **/
public interface ILogonService {

    /**
     * login user
     *
     * @param loginInfo login information
     * @return login result
     */
    LoginResultBO login(LoginInfoVO loginInfo);

    /**
     * logout user
     *
     * @param userName logout user name
     * @param systemCode logout system code
     */
    void logout(String userName, int systemCode);
}
