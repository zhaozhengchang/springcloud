package com.ceiec.twmp.tmp.vo.useronline.result;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2019/1/16 <br/>
 * Author：wenliang <br/>
 * Description: The type User Online list Result vo.
 */
public class UserOnlineListResultVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = -21532537929468136L;

    /** user ID 用户ID*/
    private Long userId;

    /** userName 用户名*/
    private String userName;

    /** roleName 角色名*/
    private String roleName;

    /** logInTime 登录时间*/
    private Date logInTime;

    /** logoutTime 退出时间*/
    private Date logoutTime;

    /** loginStatus 用户登录状态*/
    private Byte loginStatus;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets log in time.
     *
     * @return the log in time
     */
    public Date getLogInTime() {
        return logInTime;
    }

    /**
     * Sets log in time.
     *
     * @param logInTime the log in time
     */
    public void setLogInTime(Date logInTime) {
        this.logInTime = logInTime;
    }

    /**
     * Gets logout time.
     *
     * @return the logout time
     */
    public Date getLogoutTime() {
        return logoutTime;
    }

    /**
     * Sets logout time.
     *
     * @param logoutTime the logout time
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Byte getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
    }
}
