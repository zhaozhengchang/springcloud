package com.ceiec.twmp.tmp.utils;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;

import java.util.List;

/**
 * @title: DepartmentPermissionVO </br>
 * @createDate: 2019/3/21 15:27 </br>
 * @author: shihsh  </br>
 * @description: 组织机构权限控制VO </br>
 * @version: V1.0
 **/


public class DepartmentStrToList {

    /**
     * 组织机构权限控制
     *
     * @param token
     */
    public static List<Long> getDepartmentIdList(String token) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String departmentIdStr = redisUserInfoVO.getOwnDepartmentId();
        return ConvertUtil.stringToLongList(departmentIdStr, ",");
    }
}
