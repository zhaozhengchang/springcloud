package com.ceiec.twmp.tmp.authorize_interface.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/5/21 <br/>
 * Author：wenliang <br/>
 * Description: store kick login result information
 **/
public class KickLoginResultVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = 1972178042885767407L;

    /** current login user ID */
    private String userID;

    /** current login user name */
    private String userName;

    /** current login user token */
    private String token;

    /** current login user IP */
    private String loginIP;

    /** kicked login IP */
    private String kickedIP;

    /** current login time */
    private Date loginTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getKickedIP() {
        return kickedIP;
    }

    public void setKickedIP(String kickedIP) {
        this.kickedIP = kickedIP;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
