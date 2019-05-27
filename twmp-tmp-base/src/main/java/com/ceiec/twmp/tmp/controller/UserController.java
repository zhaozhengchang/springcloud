package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.services.IUserService;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.user.add.UserAddVO;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-01 16:11
 * Description:
 **/
@RestController
@RequestMapping("/systemManagement")
public class UserController {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;


    /**
     * @description:添加、编辑系统用户 userAddVO.userId为空：新加用户
     * userAddVO.userId不为空：编辑用户
     * @Param: token
     * @Param: userAddVO
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:06
     */
    @PostMapping("/userData")
    public ResponseContent addOrEditUser(@RequestHeader String token, @Valid @RequestBody UserAddVO userAddVO) {
        try {
            userService.addOrEditUser(token, userAddVO);
        } catch (BusinessException e) {
            return new ResponseContent(ResponseType.USER_NAME_EXISTED);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /**
     * @description:用户查询 - 只可以查询本组织机构及下级组织机构下面的员工
     * @Param: token
     * @Param: userListQueryVO
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:07
     */
    @PostMapping("/userInfoQuery")
    public ResponseContent listUserByPage(@RequestHeader String token, @Valid @RequestBody UserListQueryVO userListQueryVO) {

        PagedItemsVO<UserListResultVO> users = null;
        users = userService.listUserByPage(token, userListQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, users);
    }

    /**
     * @description:删除用户
     * @Param: token
     * @Param: userId
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:07
     */
    @PostMapping("/userDel/{userId}")
    public ResponseContent delUser(@RequestHeader String token, @PathVariable BigInteger userId) {

        userService.deleteUser(token, userId);
        return new ResponseContent(ResponseType.SUCCESS);
    }


}
