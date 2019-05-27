package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSysUserStatus;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.useronline.query.UserOnlineListQueryVO;

/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: interface for User Status service
 */
public interface IUserStatusService {


    /**
     * User Online info query list paged items vo.
     *
     * @param token           the token
     * @param userOnlineListQueryVO the user online list query vo
     * @return the paged items vo
     */
    PagedItemsVO<TwmpSysUserStatus> userOnlineInfoQueryList(String token, UserOnlineListQueryVO userOnlineListQueryVO);

    /**
     * Add user status save result vo.
     *
     * @param token     the token
     * @param twmpSysUserStatus the user status save vo
     * @return the user save result vo
     */
    void addUserStatus(String token, TwmpSysUserStatus twmpSysUserStatus);

    /**
     * update user status.
     *
     * @param token        the token
     * @param twmpSysUserStatus the user status update vo
     */
    void updateUserStatus(String token,  TwmpSysUserStatus twmpSysUserStatus);

    /**
     * Login in edit user status.
     *
     * @param twmpSysUserStatus the user status update vo
     */
    void loginInUserStatus(TwmpSysUserStatus twmpSysUserStatus);

    /**
     * Login out edit user status.
     *
     * @param twmpSysUserStatus the user status update vo
     */
    void loginOutUserStatus(TwmpSysUserStatus twmpSysUserStatus);


    /**
     * Judge userName Exist Flag
     *
     * @param userName user Name
     */
    boolean nameExistFlag( String userName);


}
