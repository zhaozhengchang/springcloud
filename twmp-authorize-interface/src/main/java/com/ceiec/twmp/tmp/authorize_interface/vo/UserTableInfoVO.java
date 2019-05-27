package com.ceiec.twmp.tmp.authorize_interface.vo;

import java.io.Serializable;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: store user table information, set null if not exist
 **/
public class UserTableInfoVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -179777561003004178L;

    /** user table name, necessary */
    private String userTableName;

    /** table field name for user ID(String), necessary */
    private String userID;

    /** table field name for login user name(String), necessary */
    private String userName;

    /** table field name for login password(String), necessary */
    private String password;

    /** table field name for user status(number), 1 for valid , 2 for invalid*/
    private String status;

    /** table field name for user deleted status(number), 1 for not deleted, 2 for del
     * eted */
    private String deleted;

    /** table field name for login fail times(number) */
    private String loginFailCount;

    /** table field name for last login fail time(Date) */
    private String lastLoginFailTime;

    public String getUserTableName() {
        return userTableName;
    }

    public void setUserTableName(String userTableName) {
        this.userTableName = userTableName;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(String loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public String getLastLoginFailTime() {
        return lastLoginFailTime;
    }

    public void setLastLoginFailTime(String lastLoginFailTime) {
        this.lastLoginFailTime = lastLoginFailTime;
    }
}
