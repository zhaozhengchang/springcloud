package com.ceiec.twmp.tmp.authorize.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: store login success information
 **/
public class LoginSuccessInfoVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -5882044836481350094L;

    /** login IP */
    private String loginIP;

    /** login user ID */
    private String loginUserID;

    /** login user name */
    private String loginUserName;

    /** login time */
    private Date loginTime;

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginUserID() {
        return loginUserID;
    }

    public void setLoginUserID(String loginUserID) {
        this.loginUserID = loginUserID;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
