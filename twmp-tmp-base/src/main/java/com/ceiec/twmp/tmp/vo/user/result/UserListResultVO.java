package com.ceiec.twmp.tmp.vo.user.result;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: The type User list Result vo.
 */
public class UserListResultVO  implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = 1235325379484621338L;

    /** user ID */
    private Long userId;

    /** userName */
    private String userName;

    /** role ID */
    private Long roleId;

    /** roleName */
    private String roleName;

    /** email */
    private String email;

    /** phone */
    private String phone;

    /** createTime */
    private Date createTime;
    /**
     * 传真
     */
    private String fax;
    /**
     * 用户初始地图位置
     */
    private String mapCenter;

    private Long photoId;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the contact number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets creat time.
     *
     * @param createTime the creat time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }
}
