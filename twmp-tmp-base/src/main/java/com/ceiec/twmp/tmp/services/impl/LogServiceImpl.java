package com.ceiec.twmp.tmp.services.impl;

/**
 * CreateDate：2019/3/8 16:20 </br>
 * Author：shihsh  </br>
 * Description: 记录操作日志 </br>
 **/


import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.entity.TwmpLogLogin;
import com.ceiec.twmp.tmp.entity.TwmpLogOperate;
import com.ceiec.twmp.tmp.mapper.TwmpLogLoginMapper;
import com.ceiec.twmp.tmp.mapper.TwmpLogOperateMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysUserMapper;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
class LogServiceImpl implements ILogService {

    @Autowired
    private TwmpLogOperateMapper twmpLogOperateMapper;

    @Autowired
    private TwmpLogLoginMapper twmpLogLoginMapper;


    @Override
    public void saveOperateLog(String token , byte operateType, byte operateObjectType, String operateName, String comment) {
        TwmpLogOperate twmpLogOperate = new TwmpLogOperate();

        twmpLogOperate.setOperateObjectType(operateObjectType);
        twmpLogOperate.setOperateType(operateType);
        twmpLogOperate.setOperateName(operateName);
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        twmpLogOperate.setDepartmentId(userInfo.getDepartmentId());
        twmpLogOperate.setDepartmentName(userInfo.getDepartmentName());
        twmpLogOperate.setOperateId(SnowflakeIdWorkerUtil.generateId().longValue());
        twmpLogOperate.setOperateTime(new Date());
        twmpLogOperate.setOperator(userInfo.getUserName());
        twmpLogOperate.setOperatorId(userInfo.getUserId());
        twmpLogOperate.setComment(comment);
        twmpLogOperateMapper.insert(twmpLogOperate);
    }

    @Override
    public void saveBatchOperateLog(String token, byte operateType, byte operateObjectType, List<String> operateNameList) {
        List<TwmpLogOperate> list = new ArrayList<>();
        for (String operateName : operateNameList) {
            TwmpLogOperate twmpLogOperate = new TwmpLogOperate();

            twmpLogOperate.setOperateObjectType(operateObjectType);
            twmpLogOperate.setOperateType(operateType);
            twmpLogOperate.setOperateName(operateName);
            RedisUserInfoVO userInfoVO = UserInfoRedis.getUser(token);
            twmpLogOperate.setDepartmentId(userInfoVO.getDepartmentId());
            twmpLogOperate.setDepartmentName(userInfoVO.getDepartmentName());
            twmpLogOperate.setOperateId(SnowflakeIdWorkerUtil.generateId().longValue());
            twmpLogOperate.setOperateTime(new Date());
            twmpLogOperate.setOperator(userInfoVO.getUserName());
            twmpLogOperate.setOperatorId(userInfoVO.getUserId());
            twmpLogOperate.setComment(null);
            list.add(twmpLogOperate);
        }
        twmpLogLoginMapper.insertLogList(list);
    }

    @Override
    public Long saveLoginLog(RedisUserInfoVO redisUserInfoVo) {
        TwmpLogLogin twmpLogLogin = new TwmpLogLogin();
        twmpLogLogin.setLoginId(SnowflakeIdWorkerUtil.generateId().longValue());
        twmpLogLogin.setToken(redisUserInfoVo.getToken());
        twmpLogLogin.setUserName(redisUserInfoVo.getUserName());
        twmpLogLogin.setUserId(redisUserInfoVo.getUserId());
        twmpLogLogin.setRoleId(redisUserInfoVo.getRoleId());
        twmpLogLogin.setRoleName(redisUserInfoVo.getRoleName());
        twmpLogLogin.setLoginTime(redisUserInfoVo.getLoginTime());
        twmpLogLogin.setDepartmentId(redisUserInfoVo.getDepartmentId());
        twmpLogLogin.setDepartmentName(redisUserInfoVo.getDepartmentName());
        twmpLogLoginMapper.insertSelective(twmpLogLogin);

        return twmpLogLogin.getLoginId();
    }

    @Override
    public void updateLoginLogByLogoutTime(Long loginLogId, Date logoutTime) {
        TwmpLogLogin twmpLogLogin = new TwmpLogLogin();
        twmpLogLogin.setLoginId(loginLogId);
        twmpLogLogin.setLogoutTime(logoutTime);

        twmpLogLoginMapper.updateByPrimaryKeySelective(twmpLogLogin);
    }
}

