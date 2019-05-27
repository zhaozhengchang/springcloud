package com.ceiec.twmp.tmp.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * CreateDate：2019/1/3 <br/>
 * Author：wenliang <br/>
 * Description: store login log info
 */
public class LoginLogVO implements Serializable {

    /** serialize number */
    private static final long serialVersionUID = -6842399476428281999L;

    /** token */
    private String token;

    /** name */
    private String name;

    /** user_id */
    private Long userId;

    /** role_id */
    private Long roleId;

    /** role_name */
    private String roleName;

    /** login_time */
    private Date loginTime;

    /** logout_time */
    private Date logoutTime;

    /** department_id */
    private Long departmentId;

    /** department_name */
    private String departmentName;

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * Gets role id.
     *
     * @return the role id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
     * Gets login time.
     *
     * @return the login time
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * Sets login time.
     *
     * @param loginTime the login time
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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

    /**
     * Gets department id.
     *
     * @return the department id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets department id.
     *
     * @param departmentId the department id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Gets department name.
     *
     * @return the department name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets department name.
     *
     * @param departmentName the department name
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
