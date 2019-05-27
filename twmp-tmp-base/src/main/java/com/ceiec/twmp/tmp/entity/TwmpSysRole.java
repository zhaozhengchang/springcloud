package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_sys_role")
public class TwmpSysRole extends BaseEntity {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;
    /**
     * 更新人
     */
    private String updater;


    /**可以接收的消息类型**/
    @Column(name = "accept_message_sub_type")
    private String acceptMessageSubType;


    /**
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getUpdater() {
        return updater;
    }

    @Override
    public void setUpdater(String updater) {
        this.updater = updater;
    }


    public String getAcceptMessageSubType() {
        return acceptMessageSubType;
    }

    public void setAcceptMessageSubType(String acceptMessageSubType) {
        this.acceptMessageSubType = acceptMessageSubType;
    }
}