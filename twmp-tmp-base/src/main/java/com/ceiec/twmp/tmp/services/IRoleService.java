package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.common.dict.MessageSubType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpSysRole;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.role.add.RoleAddVO;
import com.ceiec.twmp.tmp.vo.role.addoredit.RoleAddOrEditVO;
import com.ceiec.twmp.tmp.vo.role.query.RoleQueryVO;
import com.ceiec.twmp.tmp.vo.role.result.TwmpSysRoleAddResultVO;

import java.util.List;

/**
 * @Title: IRoleService
 * @Package: com.ceiec.twmp.tmp.services
 * @Description: role service
 * @Author: tangquanbin
 * @Data: 2019/1/22 11:00
 * @Version: V1.0
 */
public interface IRoleService {


    /**
     * @description:角色分页列表
     * @Param: token
     * @Param: roleQueryVO
     * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.entity.TwmpSysRole>
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:18
     */
    PagedItemsVO<TwmpSysRole> queryRoleByPage(String token, RoleQueryVO roleQueryVO);

    /**
     * @description: 删除角色
     * @Param: token
     * @Param: roleId
     * @return: java.lang.Integer
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:18
     */
    Integer deleteRole(String token, Long roleId);

  /**
   * @description:
   *  roleAddOrEditVO.roleId为空：新加
   *  roleAddOrEditVO.roleId不为空：编辑
   * @Param: token
   * @Param: roleAddOrEditVO
   * @return: com.ceiec.twmp.tmp.vo.role.result.TwmpSysRoleAddResultVO
   * @author: zhaozhengchang
   * @date: 2019/3/22 17:17
   */
    TwmpSysRoleAddResultVO addOrEditRole(String token, RoleAddOrEditVO roleAddOrEditVO) throws BusinessException;


    /*************************************************************************************************************************************
     ** @Description get message sub type with role id
     ** @param: roleId
     ** @Return java.util.List<com.ceiec.twmp.tmp.common.dict.MessageSubType>
     ** @Author Ding
     ** @Date 2019/3/21 16:58
     **
     ************************************************************************************************************************************/
    List<MessageSubType> getMessageSubTypeByRoleId(Long roleId);


}
