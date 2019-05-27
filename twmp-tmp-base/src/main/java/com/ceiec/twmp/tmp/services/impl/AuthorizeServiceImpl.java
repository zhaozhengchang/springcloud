package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.common.dict.LoginStatus;
import com.ceiec.twmp.tmp.entity.*;
import com.ceiec.twmp.tmp.mapper.TwmpSysAuthorityMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysUserMapper;
import com.ceiec.twmp.tmp.cache.redis.PAARedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.*;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;
import com.ceiec.twmp.tmp.vo.alarm.result.RedisPAAVO;
import com.ceiec.twmp.tmp.vo.authority.result.AuthorityTreeVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * CreateDate：2018/5/22 <br/>
 * Author：wenliang <br/>
 * Description: implementation for authorize service
 **/
@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class AuthorizeServiceImpl implements IAuthorizeService {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * properties environment
     */
    @Autowired
    private Environment env;

    /**
     * system user mapper
     */
    @Autowired
    private TwmpSysUserMapper twmpSysUserMapper;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private TwmpSysAuthorityMapper twmpSysAuthorityMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ILogService logService;

    @Autowired
    private IAlarmService alarmService;

    private Integer sessionTime = 30*60;


    /**
     * get insight-guide datasource information
     *
     * @return datasource information
     */
    @Override
    public DatasourceInfoVO getDatasourceInfo() {
        DatasourceInfoVO datasourceInfo = new DatasourceInfoVO();
        datasourceInfo.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        datasourceInfo.setDsUrl(env.getProperty("spring.datasource.url"));
        datasourceInfo.setDsUserName(env.getProperty("spring.datasource.username"));
        datasourceInfo.setDsPassword(env.getProperty("spring.datasource.password"));
        datasourceInfo.setAllowFailTimes(Integer.valueOf(env.getProperty("allow.fail.times")));
        datasourceInfo.setAllowRetryInterval(Integer.valueOf(env.getProperty("allow.retry.interval")));
        return datasourceInfo;
    }

    /**
     * get insight-guide user table information
     *
     * @return user table information
     */
    @Override
    public UserTableInfoVO getUserTableInfo() {
        UserTableInfoVO tableInfo = new UserTableInfoVO();
        tableInfo.setUserTableName("twmp_sys_user");
        tableInfo.setUserID("user_id");
        tableInfo.setUserName("user_name");
        tableInfo.setPassword("password");
        tableInfo.setStatus("is_usable");
        tableInfo.setDeleted("deleted");
        tableInfo.setLoginFailCount("login_fail_count");
        tableInfo.setLastLoginFailTime("last_login_fail");
        return tableInfo;
    }



    /**
     * process login success
     *
     * @param loginResult login result information
     * @return process result code
     */
    @Override
    public String processLoginSuccess(LoginResultVO loginResult) {
        //get login user's useful information
        SystemUserEntity systemUserEntity =   twmpSysUserMapper.getRedisUserLoginInfo(loginResult.getUserId());

        RedisUserInfoVO redisUserInfo = new RedisUserInfoVO();
        redisUserInfo.setUserId(systemUserEntity.getUserId());
        redisUserInfo.setToken(loginResult.getToken());
        redisUserInfo.setUserName(systemUserEntity.getUserName());
        redisUserInfo.setRoleId(systemUserEntity.getRoleId());
        redisUserInfo.setRoleName(systemUserEntity.getRoleName());
        redisUserInfo.setDepartmentId(systemUserEntity.getDepartmentId());
        redisUserInfo.setDepartmentName(systemUserEntity.getDepartmentName());
        redisUserInfo.setPhone(systemUserEntity.getPhone());
        redisUserInfo.setLanguage(systemUserEntity.getLanguage());
        redisUserInfo.setTopic(systemUserEntity.getTopic());
        redisUserInfo.setMapCenter(systemUserEntity.getMapCenter());
        redisUserInfo.setTopic(systemUserEntity.getTopic());
        redisUserInfo.setPhotoId(systemUserEntity.getPhotoId());
        redisUserInfo.setFax(systemUserEntity.getFax());
        redisUserInfo.setOwnDepartmentId(departmentService.getOwnDepartmentIds(redisUserInfo.getDepartmentId()));
        redisUserInfo.setDepartmentTree(departmentService.getDepartmentTree(redisUserInfo.getDepartmentId()));
        redisUserInfo.setAuthorityTree(getAuthorityTreeByRoleId(redisUserInfo.getRoleId()));
        redisUserInfo.setLoginTime(loginResult.getLoginTime());
        redisUserInfo.setToken(loginResult.getToken());
        redisUserInfo.setMessageSubTypes(roleService.getMessageSubTypeByRoleId(redisUserInfo.getRoleId()));


        TwmpSysUser twmpSysUser = new TwmpSysUser();
        twmpSysUser.setUserId(systemUserEntity.getUserId());
        twmpSysUser.setLoginStatus(LoginStatus.login.value);
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);

        //save log in database
        Long loginLogId = logService.saveLoginLog(redisUserInfo);
        redisUserInfo.setLoginId(loginLogId);

        if(!StringUtils.isNullOrEmpty(env.getProperty("web.session.timeout"))){
           sessionTime =  Integer.parseInt(env.getProperty("web.session.timeout"));
        }
        redisUserInfo.setSessionTime(sessionTime);

        //store login information to redis
        UserInfoRedis.saveUser(redisUserInfo);
        UserInfoRedis.saveUserOnline(redisUserInfo);

        //process successfully, return
        String logContent = "user " + loginResult.getUserName() + " login successfully  "  /**TokenUtils.getLoginIP(loginResult.getToken())**/ + " at " + DateUtils.format(loginResult.getLoginTime());
        logger.info(logContent);

        return ResponseType.SUCCESS.getCode();
    }
    /**
     * process logout
     *
     * @param token login token
     * @return process result code
     */
    @Override
    public String processLogout(String token) {

        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        //update logout time
        logService.updateLoginLogByLogoutTime(redisUserInfoVO.getLoginId(), new Date());

        //update user login_status
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        twmpSysUser.setUserId(redisUserInfoVO.getUserId());
        twmpSysUser.setLoginStatus(LoginStatus.logout.value);
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);


        //update undistributed
        List<TwmpSmAlarmEf> twmpSmAlarmEfList = alarmService.getToDisposeAlarmByHandleUserId(redisUserInfoVO.getUserId());
        if(twmpSmAlarmEfList!=null && twmpSmAlarmEfList.size()>0){
            for(TwmpSmAlarmEf twmpSmAlarmEf: twmpSmAlarmEfList){
                RedisPAAVO redisPAAVO = new RedisPAAVO();
                ObjectUtils.copy(twmpSmAlarmEf, redisPAAVO);
                PAARedis.addPAA(redisPAAVO);
            }
        }



        UserInfoRedis.delUser(token);
        return ResponseType.SUCCESS.getCode();
    }

    @Override
    public List<TwmpSysAuthority> getAuthorityByRoleId(Long roleId) {
        return twmpSysAuthorityMapper.getAuthorityByRoleId(roleId);
    }

    @Override
    public List<AuthorityTreeVO> getAuthorityTreeByRoleId(Long roleId) {
        //top authority
        List<AuthorityTreeVO> topAuthorityList = new ArrayList<>();
        List<Long> topAuthority = new ArrayList<>();

        List<TwmpSysAuthority> authorityList = twmpSysAuthorityMapper.getAuthorityByRoleId(roleId);
        if(authorityList!=null && authorityList.size()>0){


            List<AuthorityTreeVO> authorityTreeVOS = new ArrayList<>();
            Map<Long, AuthorityTreeVO> authorityTreeVOMap = new HashMap<>();

            for(TwmpSysAuthority twmpSysAuthority : authorityList){
                if(twmpSysAuthority.getParentId() == null || twmpSysAuthority.getParentId() == 0){
                    topAuthority.add(twmpSysAuthority.getAuthorityId());
                }
                AuthorityTreeVO authorityTreeVO = new AuthorityTreeVO();
                ObjectUtils.copy(twmpSysAuthority, authorityTreeVO);
                authorityTreeVOS.add(authorityTreeVO);
                authorityTreeVOMap.put(authorityTreeVO.getAuthorityId(), authorityTreeVO);
            }


            for(AuthorityTreeVO authorityTreeVO: authorityTreeVOS){
                if(authorityTreeVO.getParentId()!=null && authorityTreeVOMap.get(authorityTreeVO.getParentId())!=null ){
                    AuthorityTreeVO parentTreeVo = authorityTreeVOMap.get(authorityTreeVO.getParentId());

                    List<AuthorityTreeVO> list = new ArrayList<>();
                    if(parentTreeVo.getChildrenList()!=null){
                        list = parentTreeVo.getChildrenList();
                    }
                    list.add(authorityTreeVO);
                    parentTreeVo.setChildrenList(list);

                }
            }

            for(Long id: topAuthority){
                topAuthorityList.add(authorityTreeVOMap.get(id));
            }
            return topAuthorityList;

        }

        return null;
    }
}
