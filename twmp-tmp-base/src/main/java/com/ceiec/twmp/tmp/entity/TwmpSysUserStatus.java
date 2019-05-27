package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sys_user_status")
public class TwmpSysUserStatus {
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 组织机构名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 登录状态（1，在线2离线）
     */
    @Column(name = "login_status")
    private Byte loginStatus;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 退出时间
     */
    @Column(name = "logout_time")
    private Date logoutTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名
     *
     * @return role_name - 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名
     *
     * @param roleName 角色名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取组织机构名称
     *
     * @return department_name - 组织机构名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置组织机构名称
     *
     * @param departmentName 组织机构名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 获取登录状态（1，在线2离线）
     *
     * @return login_status - 登录状态（1，在线2离线）
     */
    public Byte getLoginStatus() {
        return loginStatus;
    }

    /**
     * 设置登录状态（1，在线2离线）
     *
     * @param loginStatus 登录状态（1，在线2离线）
     */
    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取退出时间
     *
     * @return logout_time - 退出时间
     */
    public Date getLogoutTime() {
        return logoutTime;
    }

    /**
     * 设置退出时间
     *
     * @param logoutTime 退出时间
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}