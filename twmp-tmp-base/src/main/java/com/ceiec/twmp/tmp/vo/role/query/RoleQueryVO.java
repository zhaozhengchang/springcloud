package com.ceiec.twmp.tmp.vo.role.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

/**
 * @Title: RoleQueryVO
 * @Package: com.ceiec.twmp.tmp.vo.role.query
 * @Description: TODO（添加描述）
 * @Author: tangquanbin
 * @Data: 2019/1/25 14:26
 * @Version: V1.0
 */
public class RoleQueryVO extends PageParentVO {
    /**
     * 角色名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
