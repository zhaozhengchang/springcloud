package com.ceiec.twmp.tmp.vo.user.add;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: The type User save vo.
 **/
public class UserAddVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = -2535338389614671637L;
    /**
     * 用户表主键
     */
    private Long userId;
    /** userName */
    @NotNull(message = "userName not null")
    private String userName;

    /** email */
    private String email;

    /** phone */
    private String phone;

    /** fax */
    private String fax;

    /** role ID */
    @NotNull(message = "roleId not null")
    private Long roleId;

    /** department ID */
    @NotNull(message = "departmentId not null")
    private Long departmentId;

    /** mapCenter */
    private String mapCenter;
    /**
     * 新密码
     */
    private String newPassword;

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
     * Sets contact number.
     *
     * @param phone the contact number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets fax .
     *
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets fax .
     *
     * @param fax the fax
     */
    public void setFaxNumber(String fax) {
        this.fax = fax;
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
     * Gets default map.
     *
     * @return the default map
     */
    public String getMapCenter() {
        return mapCenter;
    }

    /**
     * Sets default map.
     *
     * @param mapCenter the default map
     */
    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }


    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
