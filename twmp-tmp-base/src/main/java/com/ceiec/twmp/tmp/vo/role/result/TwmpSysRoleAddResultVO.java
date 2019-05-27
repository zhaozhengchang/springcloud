package com.ceiec.twmp.tmp.vo.role.result;

import com.ceiec.twmp.tmp.ResponseType;

import java.io.Serializable;

/**
 * @Title: TwmpSysRoleAddResultVO
 * @Package: com.ceiec.twmp.tmp.vo.role.result
 * @Description: TwmpSysRole Add ResultVO
 * @Author: tangquanbin
 * @Data: 2019/1/22 11:29
 * @Version: V1.0
 */
public class TwmpSysRoleAddResultVO implements Serializable {
    private static final long serialVersionUID = 1168581734924814807L;
    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;
    /**
     * 角色id
     */
    private Long  roleId;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
