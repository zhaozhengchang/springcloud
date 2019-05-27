package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.entity.TwmpSysAuthority;
import com.ceiec.twmp.tmp.vo.authority.result.AuthorityTreeVO;

import java.util.List;

/**
 * CreateDate：2018/5/22 <br/>
 * Author：wenliang <br/>
 * Description: interface for authorize service
 **/
public interface IAuthorizeService {

    /**
     * get insight-guide datasource information
     *
     * @return datasource information
     */
    DatasourceInfoVO getDatasourceInfo();

    /**
     * get insight-guide user table information
     *
     * @return user table information
     */
    UserTableInfoVO getUserTableInfo();

    /**
     * process login success
     *
     * @param loginResult login result information
     * @return process result code
     */
    String processLoginSuccess(LoginResultVO loginResult);

    /**
     * process logout
     *
     * @param token login token
     * @return process result code
     */
    String processLogout(String token);

    /*************************************************************************************************************************************
     ** @Description get authority list by role
     ** @param: token
     * @param: roleId
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpSysAuthority>
     ** @Author Ding
     ** @Date 2019/3/5 17:27
     **
     ************************************************************************************************************************************/
    List<TwmpSysAuthority> getAuthorityByRoleId(Long roleId);

    /*************************************************************************************************************************************
     ** @Description get authority tree by role
     ** @param: token
     * @param: roleId
     ** @Return com.ceiec.twmp.tmp.vo.authority.result.AuthorityTreeVO
     ** @Author Ding
     ** @Date 2019/3/5 17:28
     **
     ************************************************************************************************************************************/
    List<AuthorityTreeVO> getAuthorityTreeByRoleId( Long roleId);
}
