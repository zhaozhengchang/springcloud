package com.ceiec.twmp.tmp.vo.role.addoredit;

import javax.validation.constraints.NotBlank;

/**
 * @Title: RoleAddOrEditVO
 * @Package: com.ceiec.twmp.tmp.vo.role.save
 * @Description: RoleAddOrEditVO 用于添加、修改
 * @Author: zhengzhengchang
 * @Data: 2019/1/24 14:42
 */
public class RoleAddOrEditVO {
    /**
     * 角色名称
     */
    @NotBlank(message = "role name can not be null or empty string. ")
    private String roleName;
    /**
     * 角色权限,角色权限前端按树图形式呈现
     */
    @NotBlank(message = "rolePermission can not be null or empty string. ")
    private String rolePermission;
    /**
     * 角色主键，用户编辑角色
     */
    private Long roleId;

    private String acceptMessageSubType;

    public String getAcceptMessageSubType() {
        return acceptMessageSubType;
    }

    public void setAcceptMessageSubType(String acceptMessageSubType) {
        this.acceptMessageSubType = acceptMessageSubType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(String rolePermission) {
        this.rolePermission = rolePermission;
    }
}
