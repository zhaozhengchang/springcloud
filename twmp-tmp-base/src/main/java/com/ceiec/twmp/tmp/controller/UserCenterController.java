package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.services.IUserService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.user.add.UserAddVO;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import com.ceiec.twmp.tmp.vo.user.update.UserEditVO;
import com.ceiec.twmp.tmp.vo.user.update.UserPassEditVO;
import com.ceiec.twmp.tmp.vo.user.update.UserUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;


/**
 * CreateDate：2019/3/1<br/>
 * Author：wenliang <br/>
 * Description: User Center Manage Controller
 **/

@RestController
@RequestMapping("/user")
public class UserCenterController {

    @Autowired
    private IUserService userService;

    /**
     * logger
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @description:修改个人信息
     * @Param: token
     * @Param: userEditVO
     * @Param: bindingResult
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:36
     */
    @PostMapping("/useInfoEdit")
    public ResponseContent editUser(@RequestHeader String token, @Valid @RequestBody UserEditVO userEditVO ) {
        userService.editUserSystem(token, userEditVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /**
     * @description:密码修改
     * @Param: token
     * @Param: userPassEditVO
     * @Param: bindingResult
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:36
     */
    @PostMapping("/usePasswordEdit")
    public ResponseContent editUserPassword(@RequestHeader String token, @Valid @RequestBody UserPassEditVO userPassEditVO) {
        try {
            userService.editUserPassword(token, userPassEditVO);
            return new ResponseContent(ResponseType.SUCCESS);
        } catch (BusinessException e) {
            logger.error("edit user password is error=" + e.getMessage());
        }
        return new ResponseContent(ResponseType.OLD_PASSWORD_ERROR);
    }


}
