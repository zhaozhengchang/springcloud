package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpSysUserStatus;
import com.ceiec.twmp.tmp.services.IUserStatusService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.useronline.query.UserOnlineListQueryVO;
import com.ceiec.twmp.tmp.vo.useronline.result.UserOnlineListResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CreateDate：2019/1/16 <br/>
 * Author：wenliang <br/>
 * Description: 用户登录状态 Controller
 **/
@RestController
@RequestMapping("/userOnline")
public class UserOnlineController {

    @Autowired
    private IUserStatusService userStatusService;

    /**
     * User online info query list response content.
     *
     * @param token           the token
     * @param userOnlineListQueryVO the user online list query vo
     * @return the response content
     */
    @PostMapping("/userOnlineQuery")
    public ResponseContent userOnlineQuery(@RequestHeader String token, @RequestBody UserOnlineListQueryVO userOnlineListQueryVO) {
        PagedItemsVO<TwmpSysUserStatus> twmpSysUserStatusPageInfo = userStatusService.userOnlineInfoQueryList(token, userOnlineListQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, twmpSysUserStatusPageInfo);
    }
}
