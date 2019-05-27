package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.LoginStatus;
import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.entity.TwmpSysUser;
import com.ceiec.twmp.tmp.mapper.TwmpLogOperateMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysUserMapper;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IUserService;
import com.ceiec.twmp.tmp.utils.ConvertUtil;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO;
import com.ceiec.twmp.tmp.vo.user.update.UserEditVO;
import com.ceiec.twmp.tmp.vo.user.update.UserPassEditVO;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.DESUtils;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.vo.user.add.UserAddVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: implementation for User service
 **/

@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class UserServiceImpl implements IUserService {

   /**
     * logger
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


/**
     * system user mapper
     */

    @Autowired
    private TwmpSysUserMapper twmpSysUserMapper;
    @Autowired
    private TwmpLogOperateMapper twmpLogOperateMapper;
    @Autowired
    private ILogService logService;

/**
     * User info query list paged items vo.
     *
     * @param token           the token
     * @param userListQueryVO the user list query vo
     * @return the paged items vo
     */

    @Override
    public PagedItemsVO<UserListResultVO> userInfoQueryList(String token, UserListQueryVO userListQueryVO){
        PageHelper.startPage(userListQueryVO.getPageNo(), userListQueryVO.getPageSize()," create_time desc");
        List<UserListResultVO> userListResultVOList = twmpSysUserMapper.getUserList(userListQueryVO);
        return new PagedItemsVO<>(userListResultVOList);
    }

    @Override
    public UserAddResultVO  addOrEditUser(String token, UserAddVO userAddVO) throws BusinessException{
        if(null == userAddVO.getUserId()){
            //校验用户名是否已存在
             if(existUserName(userAddVO.getUserName())){
                 throw new BusinessException("user name existed !");
             }
            //新增用户
            addUser(token,userAddVO);
             //记录操作日志
            logService.saveOperateLog(token , OperateType.insert.value,OperateObjectType.user.value,
                    userAddVO.getUserName(),null);
        }else{
            editUser(token , userAddVO);
            //记录操作日志
            logService.saveOperateLog(token , OperateType.update.value,OperateObjectType.user.value,
                    userAddVO.getUserName(),null);
        }

        return new UserAddResultVO();
    }


    /**
     * 校验用户名
     * true : 此用户已存在
     * false：此用户不存在
     * @param userName
     * @return
     */
    private boolean existUserName(String userName) {
        int count = twmpSysUserMapper.getUserCountByUserName(userName);
        return count >= 1 ? true:false;
    }


    /**
     * 新增用户
     * @param token
     * @param userAddVO
     * @return
     */
   public UserAddResultVO addUser(String token, UserAddVO userAddVO)  {
       UserAddResultVO userAddResultVO = new UserAddResultVO();
       BigInteger userID = SnowflakeIdWorkerUtil.generateId();
       TwmpSysUser twmpSysUser = new TwmpSysUser();
       ObjectUtils.copy(userAddVO, twmpSysUser);
       twmpSysUser.setUserId(userID.longValue());
       twmpSysUser.setPassword(TmpBaseConstants.USER_DEFAULT_PASSWORD);
       twmpSysUser.setCreator(TokenUtils.getUserName(token));
       twmpSysUser.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
       twmpSysUser.setCreateTime(new Date());
       twmpSysUser.setUpdater(TokenUtils.getUserName(token));
       twmpSysUser.setUpdateTime(new Date());
       twmpSysUser.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
       twmpSysUser.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
       twmpSysUserMapper.insertSelective(twmpSysUser);
       userAddResultVO.setUserId(userID.longValue());
       return userAddResultVO;
   }


    /**
     * 编辑用户信息
     * @param userAddVO the user update vo
     * @throws ParameterException
     */
    public void editUser(String token , UserAddVO userAddVO) {
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        ObjectUtils.copy(userAddVO, twmpSysUser);
        twmpSysUser.setUpdater(TokenUtils.getUserName(token));
        twmpSysUser.setUpdateTime(new Date());
        twmpSysUser.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        if(null != userAddVO.getNewPassword()) {
            twmpSysUser.setPassword(DESUtils.encrypt(userAddVO.getNewPassword()));
        }
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);
    }



    @Override
    public void editUserSystem(String token,UserEditVO userEditVO) {
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        ObjectUtils.copy(userEditVO, twmpSysUser);
        twmpSysUser.setUpdater(TokenUtils.getUserName(token));
        twmpSysUser.setUpdateTime(new Date());
        twmpSysUser.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);
        //重新缓存redis信息
        RedisUserInfoVO redisUserInfoVo = UserInfoRedis.getUser(token);
        if(! StringUtils.isNullOrEmpty(userEditVO.getEmail())){
            redisUserInfoVo.setEmail(userEditVO.getEmail());
        }
        if(! StringUtils.isNullOrEmpty(userEditVO.getPhone())){
            redisUserInfoVo.setPhone(userEditVO.getPhone());
        }
        if(! StringUtils.isNullOrEmpty(userEditVO.getFax())){
            redisUserInfoVo.setFax(userEditVO.getFax());
        }
        if(userEditVO.getPhotoId() != null){
            redisUserInfoVo.setPhotoId(userEditVO.getPhotoId());
        }
        UserInfoRedis.saveUser(redisUserInfoVo);
    }


    @Override
    public void editUserPassword(String token,UserPassEditVO userPassEditVO)throws BusinessException{
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        String oldPassword = userPassEditVO.getPassword();
        String oldPassWordEncrypt = DESUtils.decrypt(TokenUtils.getPassWord(token));
        if(oldPassWordEncrypt.equals(oldPassword)){
            twmpSysUser.setUserId(userPassEditVO.getUserId());
            twmpSysUser.setPassword(DESUtils.encrypt(userPassEditVO.getNewPassword()));
            twmpSysUser.setUpdater(TokenUtils.getUserName(token));
            twmpSysUser.setUpdateTime(new Date());
            twmpSysUser.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
            twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);
        }else{
            throw new BusinessException("password is wrong");
        }
    }

/**
     * Judge userName Exist Flag,exit return true.
     *
     * @param token   the token
     * @param userName user Name
     */

    @Override
    public boolean nameExistFlag(String token,String userName){
        boolean existFlag = false;
        Example example = new Example(TwmpSysUser.class);
        example.createCriteria()
                .andEqualTo("userName",userName);
        int counts = twmpSysUserMapper.selectCountByExample(example);
        if (counts>0){
            existFlag = true;
        }
        return existFlag;
    }


/**
     * Delete user.
     *
     * @param token  the token
     * @param userId the user id
     */

    @Override
    public void deleteUser(String token,BigInteger  userId){
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        twmpSysUser.setUserId(userId.longValue());
        twmpSysUser.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);
        //记录操作日志
        TwmpSysUser twmpSysUser1 = twmpSysUserMapper.selectByPrimaryKey(userId);
        logService.saveOperateLog(token , OperateType.delete.value,OperateObjectType.user.value,
                twmpSysUser1.getUserName(),null);
    }

    @Override
    public PagedItemsVO<UserListResultVO> listUserByPage(String token, UserListQueryVO userListQueryVO){
        // 组织机构权限控制
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String departmentIds = redisUserInfoVO.getOwnDepartmentId();
        List<Long> ownDepartmentIds = ConvertUtil.stringToLongList(departmentIds, ",");
        userListQueryVO.setDepartmentIdList(ownDepartmentIds);

        PageHelper.startPage(userListQueryVO.getPageNo(), userListQueryVO.getPageSize()," create_time desc");
        List<UserListResultVO> userListResultVOList = twmpSysUserMapper.getUserList(userListQueryVO);
        int total = userListResultVOList.size();
        return new PagedItemsVO<>(total , userListResultVOList);
    }

    @Override
    public void updateUserLogout(Long userId) {
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        twmpSysUser.setUserId(userId);
        twmpSysUser.setLoginStatus(LoginStatus.logout.value);
        twmpSysUserMapper.updateByPrimaryKeySelective(twmpSysUser);
    }
}

