package com.ceiec.twmp.tmp.vo.user.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: The type User list query vo.
 **/
public class UserListQueryVO extends PageParentVO{
    /** serializable ID */
    private static final long serialVersionUID = 3235325381484621638L;

    /** role ID */
    private Long roleId;

    /** userName */
    private String userName;
    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 自己所属组织机构及下属组织机构列表
     */
    private List<Long> departmentIdList;

    private Byte loginStatus;

    public List<Long> getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List<Long> departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    public Byte getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
