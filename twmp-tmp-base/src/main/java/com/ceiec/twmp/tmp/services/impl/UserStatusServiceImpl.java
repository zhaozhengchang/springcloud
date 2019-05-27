package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.LoginStatus;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.entity.TwmpSysUserStatus;
import com.ceiec.twmp.tmp.mapper.TwmpSysUserStatusMapper;
import com.ceiec.twmp.tmp.services.IUserStatusService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.useronline.query.UserOnlineListQueryVO;
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
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: implementation for User Status service
 **/
@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class UserStatusServiceImpl implements IUserStatusService {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * User Status Service
     */
    @Autowired
    private TwmpSysUserStatusMapper twmpSysUserStatusMapper;

    /**
     * User Online info query list paged items vo.
     *
     * @param token           the token
     * @param userOnlineListQueryVO the user online list query vo
     * @return the paged items vo
     */
    @Override
    public PagedItemsVO<TwmpSysUserStatus> userOnlineInfoQueryList(String token, UserOnlineListQueryVO userOnlineListQueryVO){
        PageHelper.startPage(userOnlineListQueryVO.getPageNo(), userOnlineListQueryVO.getPageSize()," login_time desc");
        Example example = new Example(TwmpSysUserStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userName","%"+userOnlineListQueryVO.getUserName()+"%");
        if(0 != userOnlineListQueryVO.getStatus()){
            criteria.andEqualTo("loginStatus",userOnlineListQueryVO.getStatus());
        }
        List<TwmpSysUserStatus> twmpSysUserStatusList = twmpSysUserStatusMapper.selectByExample(example);
        return new PagedItemsVO<>(twmpSysUserStatusList);
    }

    /**
     * Add user user status save result vo.
     *
     * @param token     the token
     * @param twmpSysUserStatus the user status save vo
     * @return the user save result vo
     */
    @Override
   public void addUserStatus(String token, TwmpSysUserStatus twmpSysUserStatus) throws ParameterException {
        BigInteger userID = SnowflakeIdWorkerUtil.generateId();
        twmpSysUserStatus.setId(userID.longValue());
        twmpSysUserStatusMapper.insertSelective(twmpSysUserStatus);


   }

    /**
     * update user.
     *
     * @param token        the token
     * @param twmpSysUserStatus the user status vo
     */
    @Override
    public void updateUserStatus(String token,TwmpSysUserStatus twmpSysUserStatus)throws ParameterException{
        twmpSysUserStatusMapper.updateByPrimaryKeySelective(twmpSysUserStatus);
    }

    /**
     * Login in edit user status.
     *
     * @param twmpSysUserStatus the user status vo
     */
    @Override
    public void loginInUserStatus(TwmpSysUserStatus twmpSysUserStatus)throws ParameterException{
        boolean exitFlag = this.nameExistFlag(twmpSysUserStatus.getUserName());
        TwmpSysUserStatus twmpSysUserStatusTemp = new TwmpSysUserStatus();
        if (!exitFlag){
            twmpSysUserStatusTemp = twmpSysUserStatus;
        }
        twmpSysUserStatusTemp.setLoginStatus(LoginStatus.login.value);
        twmpSysUserStatusTemp.setLoginTime(new Date(System.currentTimeMillis()));
        if (exitFlag){
            twmpSysUserStatusTemp.setUserId(twmpSysUserStatus.getUserId());
            Example example = new Example(TwmpSysUserStatus.class);
            example.createCriteria()
                    .andEqualTo("userId",twmpSysUserStatusTemp.getUserId());
            twmpSysUserStatusMapper.updateByExampleSelective(twmpSysUserStatusTemp,example);

        }else{
            twmpSysUserStatusMapper.insertSelective(twmpSysUserStatusTemp);
        }
    }

    /**
     * Login out edit user status.
     *
     * @param twmpSysUserStatus the user status vo
     */
    @Override
    public void loginOutUserStatus(TwmpSysUserStatus twmpSysUserStatus)throws ParameterException{
        TwmpSysUserStatus twmpSysUserStatusTemp = new TwmpSysUserStatus();
        twmpSysUserStatusTemp.setUserId(twmpSysUserStatus.getUserId());
        twmpSysUserStatusTemp.setLoginStatus(LoginStatus.logout.value);
        twmpSysUserStatusTemp.setLoginTime(null);
        twmpSysUserStatusTemp.setLogoutTime(new Date(System.currentTimeMillis()));
        Example example = new Example(TwmpSysUserStatus.class);
        example.createCriteria()
                .andEqualTo("userId",twmpSysUserStatusTemp.getUserId());
        twmpSysUserStatusMapper.updateByExampleSelective(twmpSysUserStatusTemp,example);
    }



    /**
     * Judge userName Exist Flag,exit return true.
     *
     * @param userName user Name
     */
    @Override
    public boolean nameExistFlag(String userName){
        boolean existFlag = false;
        Example example = new Example(TwmpSysUserStatus.class);
        example.createCriteria()
                .andEqualTo("userName",userName);
        int counts = twmpSysUserStatusMapper.selectCountByExample(example);
        if (counts>0){
            existFlag = true;
        }
        return existFlag;
    }

}
