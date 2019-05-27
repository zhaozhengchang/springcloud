package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpSysRole;
import com.ceiec.twmp.tmp.services.IRoleAuthorityService;
import com.ceiec.twmp.tmp.services.IRoleService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.role.addoredit.RoleAddOrEditVO;
import com.ceiec.twmp.tmp.vo.role.query.RoleQueryVO;
import com.ceiec.twmp.tmp.vo.role.result.TwmpSysRoleAddResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Title: RoleController
 * @Package: com.ceiec.twmp.tmp.controller
 * @Description: 权限Controller
 * @Author: tangquanbin
 * @Data: 2019/1/22 10:58
 * @Version: V1.0
 */
@RestController
@RequestMapping("/systemManagement")
public class RoleController {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * role service
     */
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleAuthorityService roleAuthorityService;

    /**
     * roleAddOrEditVO.roleId为空：新加
     * roleAddOrEditVO.roleId不为空：编辑
     *
     * @param token
     * @param roleAddOrEditVO
     * @return
     */
    @PostMapping("/roleData")
    public ResponseContent addOrEditRole(@RequestHeader String token, @Valid @RequestBody RoleAddOrEditVO roleAddOrEditVO) {
        try {
            roleService.addOrEditRole(token, roleAddOrEditVO);
        } catch (BusinessException e) {
            return new ResponseContent(ResponseType.ROLE_NAME_EXISTED);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }


    /**
     * 角色分页列表
     *
     * @param token
     * @param roleQueryVO
     * @return
     */
    @PostMapping("/roleQuery")
    public ResponseContent queryRoleByPage(@RequestHeader String token, @Valid @RequestBody RoleQueryVO roleQueryVO) {
        PagedItemsVO<TwmpSysRole> roles = null;
        try {
            roles = roleService.queryRoleByPage(token, roleQueryVO);
        } catch (Exception e) {
            logger.error("query role failed.", e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS, roles);
    }

    /**
     * 删除角色
     *
     * @param token
     * @param roleId
     * @return
     */
    @PostMapping("/roleDel/{roleId}")
    public ResponseContent deleteRole(@RequestHeader String token, @PathVariable Long roleId) {
        try {
            roleService.deleteRole(token, roleId);
        } catch (Exception e) {
            logger.error("delete role failed.", e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /**
     * 获取角色权限
     *
     * @param token
     * @param roleId
     * @return
     */
    @PostMapping("/roleAuthority")
    public ResponseContent queryRoleAuthority(@RequestHeader String token, @RequestParam Long roleId) {
        Map authority;
        try {
            authority = roleAuthorityService.queryRoleAuthority(token, roleId);
        } catch (Exception e) {
            logger.error("quert role Authority failed.", e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS, authority);
    }

}
