package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_sys_user")
public class TwmpSysUser extends BaseEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 帐号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像Id
     */
    @Column(name = "photo_id")
    private Long photoId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 备注
     */
    private String comment;

    /**
     * 主题
     */
    private Byte topic;

    /**
     * 语言
     */
    private String language;

    /**
     * 用户地图中心区域
     */
    @Column(name = "map_center")
    private String mapCenter;

    /**
     * 登录状态 1登录 2退出
     */
    @Column(name = "login_status")
    private Byte loginStatus;

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取帐号
     *
     * @return user_name - 帐号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置帐号
     *
     * @param userName 帐号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像路径
     *
     * @return photoId - 头像路径
     */
    public Long getPhotoUrl() {
        return photoId;
    }

    /**
     * 设置头像路径
     *
     * @param photoId 头像路径
     */
    public void setPhotoUrl(Long photoId) {
        this.photoId = photoId;
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
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取主题
     *
     * @return topic - 主题
     */
    public Byte getTopic() {
        return topic;
    }

    /**
     * 设置主题
     *
     * @param topic 主题
     */
    public void setTopic(Byte topic) {
        this.topic = topic;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取用户地图中心区域
     *
     * @return map_center - 用户地图中心区域
     */
    public String getMapCenter() {
        return mapCenter;
    }

    /**
     * 设置用户地图中心区域
     *
     * @param mapCenter 用户地图中心区域
     */
    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }

    /**
     * 获取登录状态 1登录 2退出
     *
     * @return login_status - 登录状态 1登录 2退出
     */
    public Byte getLoginStatus() {
        return loginStatus;
    }

    /**
     * 设置登录状态 1登录 2退出
     *
     * @param loginStatus 登录状态 1登录 2退出
     */
    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
    }


}