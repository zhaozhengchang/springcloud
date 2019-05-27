package com.ceiec.twmp.tmp.vo.role.add;

import javax.validation.constraints.NotBlank;

/**
 * @Title: RoleAddVO
 * @Package: com.ceiec.twmp.tmp.vo.role.save
 * @Description: RoleAddVO 用于添加、修改、删除接收参数
 * @Author: tangquanbin
 * @Data: 2019/1/24 14:42
 * @Version: V1.0
 */
public class RoleAddVO {
    /**
     * 角色名称
     */
    @NotBlank(message = "role name can not be null or empty string. ")
    private String roleName;
    /**
     * 角色权限,角色权限前端按树图形式呈现
     */
    @NotBlank(message = "role auth can not be null or empty string. ")
    private String roleAuth;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAuth() {
        return roleAuth;
    }

    public void setRoleAuth(String roleAuth) {
        this.roleAuth = roleAuth;
    }
}
