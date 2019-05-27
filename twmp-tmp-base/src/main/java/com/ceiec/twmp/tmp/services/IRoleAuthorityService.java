package com.ceiec.twmp.tmp.services;

import java.util.Map;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-05 17:12
 * Description:
 **/
public interface IRoleAuthorityService {
   /**
    * @description:获取角色权限
    * @Param: token
    * @Param: roleId
    * @return: java.util.Map
    * @author: zhaozhengchang
    * @date: 2019/3/22 17:19
    */
    Map queryRoleAuthority(String token, Long roleId);
}
