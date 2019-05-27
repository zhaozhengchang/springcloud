package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import java.util.Date;
import java.util.List;

/**
 * CreateDate：2019/3/8 16:19 </br>
 * Author：shihsh  </br>
 * Description: 记录操作日志  && 登陆日志 </br>
 **/


public interface ILogService {

    /**
     * 记录日志表
     * @param token 用户token
     * @param operateType 操作类型
     * @param operateObjectType 操作对象类型
     * @param operateName 操作对象
     * @param comment 备注
     */
    void saveOperateLog(String token , byte operateType, byte operateObjectType, String operateName, String comment);

    /**
    *
    * @description:  批量存入操作日志
    * @param: token
    * @param: operateType
    * @param: operateObjectType
    * @param: operateNameList
    * @return: void
    * @author: shihsh
    * @date: 2019/4/12
    */

    void saveBatchOperateLog(String token, byte operateType, byte operateObjectType, List<String> operateNameList);

    /*************************************************************************************************************************************
     ** @Description save login log
     ** @param: redisUserInfoVo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/11 17:23
     **
     ************************************************************************************************************************************/
    Long saveLoginLog(RedisUserInfoVO redisUserInfoVo);

    /*************************************************************************************************************************************
     ** @Description update log logoutTime when user logout
     ** @param: loginLogId
     * @param: logoutTime
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/11 17:26
     **
     ************************************************************************************************************************************/
    void updateLoginLogByLogoutTime(Long loginLogId, Date logoutTime);

}
