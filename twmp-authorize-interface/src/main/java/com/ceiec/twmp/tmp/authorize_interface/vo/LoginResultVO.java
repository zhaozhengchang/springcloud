package com.ceiec.twmp.tmp.authorize_interface.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: store login result information
 **/
public class LoginResultVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = 4164989306306492391L;

    /** is login success */
    private boolean loginSuccess;

    /** further information */
    private String furtherInfo;

    /** logged in user ID(only when success) */
    private Long userId;

    /** logged in user name(only when success) */
    private String userName;

    /** logged in token(only when success) */
    private String token;

    /** logged in time(only when success) */
    private Date loginTime;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public String getFurtherInfo() {
        return furtherInfo;
    }

    public void setFurtherInfo(String furtherInfo) {
        this.furtherInfo = furtherInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
