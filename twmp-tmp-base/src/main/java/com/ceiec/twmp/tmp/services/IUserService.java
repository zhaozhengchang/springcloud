package com.ceiec.twmp.tmp.services;


import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.entity.TwmpSysUser;
import com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO;
import com.ceiec.twmp.tmp.vo.user.update.UserPassEditVO;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.user.add.UserAddVO;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import com.ceiec.twmp.tmp.vo.user.update.UserEditVO;
import com.ceiec.twmp.tmp.vo.user.update.UserUpdateVO;

import java.io.IOException;
import java.math.BigInteger;

/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: interface for authorize service
 */
public interface IUserService {

    /**
     * User info query list paged items vo.
     *
     * @param token           the token
     * @param userListQueryVO the user list query vo
     * @return the paged items vo
     */
    PagedItemsVO<UserListResultVO> userInfoQueryList(String token, UserListQueryVO userListQueryVO);


    /**
     * @description:
     * userAddVO.userId为空：新加用户
     * userAddVO.userId不为空：编辑用户
     * @Param: token
     * @Param: userAddVO
     * @return: com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:15
     */
    UserAddResultVO addOrEditUser(String token, UserAddVO userAddVO) throws BusinessException;

    /**
     * @description:修改个人信息
     * @Param: token
     * @Param: userEditVO
     * @return: void
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:37
     */
    void editUserSystem(String token,UserEditVO userEditVO);

    /**
     * @description:修改密码
     * @Param: token
     * @Param: userPassEditVO
     * @return: void
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:37
     */
    void editUserPassword(String token,UserPassEditVO userPassEditVO)throws BusinessException;

    /**
     * Judge userName Exist Flag
     *
     * @param token   the token
     * @param userName user Name
     */
    boolean nameExistFlag(String token,String userName);

   /**
    * @description:删除用户（更新状态为已删除）
    * @Param: token
    * @Param: userId
    * @return: void
    * @author: zhaozhengchang
    * @date: 2019/3/22 17:16
    */
    void deleteUser(String token,BigInteger userId);

    /**
     * @description:用户查询
     * @Param: token
     * @Param: userListQueryVO
     * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.user.result.UserListResultVO>
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:15
     */
    PagedItemsVO<UserListResultVO> listUserByPage(String token, UserListQueryVO userListQueryVO);

    /*************************************************************************************************************************************
     ** @Description update user login status to logout
     ** @param: twmpSysUser
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 16:29
     **
     ************************************************************************************************************************************/
    void updateUserLogout(Long userId);
}
