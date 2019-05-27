package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IUserService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.user.add.UserAddVO;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.UserAddResultVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import com.ceiec.twmp.tmp.vo.user.update.UserUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;


/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: System User Manage Controller
 **/

@RestController
@RequestMapping("/userInfoManage")
public class SysUserManageController {

    @Autowired
    private IUserService userService;


/**
     * logger
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


/**
     * User info query list response content.
     *
     * @param token           the token
     * @param userListQueryVO the user list query vo
     * @return the response content
     */

    @PostMapping("/userInfoQuery")
    public ResponseContent userInfoQueryList(@RequestHeader String token, @RequestBody UserListQueryVO userListQueryVO) {
        PagedItemsVO<UserListResultVO> userListResultVOPagedItemsVO = userService.userInfoQueryList(token, userListQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, userListResultVOPagedItemsVO);
    }


/**
     * Add user response content.
     *
     * @param token         the token
     * @param userAddVO     the user save vo
     * @return the response content
     */

    @PostMapping("/addUser")
    public ResponseContent addUser(@RequestHeader String token, @Valid @RequestBody UserAddVO userAddVO) {
//        String errorMess ="";
//        if(bindingResult.hasErrors()){
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                errorMess = fieldError.getDefaultMessage();
//                logger.warn("addUser is error="+errorMess);
//                break;
//            }
//            return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,errorMess);
//        }else{
                UserAddResultVO userAddResultVO = userService.addOrEditUser(token, userAddVO);
                return new ResponseContent(userAddResultVO.getResponseType(), userAddResultVO.getUserId(), "userId");
//            }
    }


/**
     * Edit user response content.
     *
     * @param token         the token
     * @param userUpdateVO  the user update vo
     * @return the response content
     */

    @PostMapping("/editUser")
    public ResponseContent editUser(@RequestHeader String token, @Valid @RequestBody UserUpdateVO userUpdateVO) {
//        if(bindingResult.hasErrors()){
//            String errorMess ="";
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                errorMess = fieldError.getDefaultMessage();
//                logger.warn("addUser is error="+errorMess);
//                break;
//            }
//            return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,errorMess);
//        }else {
            userService.addOrEditUser(token, userUpdateVO);
            return new ResponseContent(ResponseType.SUCCESS);
//        }
    }


/**
     * User name exist response content.
     *
     * @param token    the token
     * @param userName the user name
     * @return the response content
     */

    @PostMapping("/userNameExist/{userName}")
    public ResponseContent userNameExist(@RequestHeader String token, @PathVariable String userName) {
        boolean existFalg = userService.nameExistFlag(token, userName);
        if (existFalg){
            return new ResponseContent(ResponseType.USERNAME_EXIST);
        }
            return new ResponseContent(ResponseType.SUCCESS);
    }

/**
     * Delete user response content.
     *
     * @param token  the token
     * @param userId the user id
     * @return the response content
     */

    @PostMapping("/delUser/{userId}")
    public ResponseContent deleteUser(@RequestHeader String token, @PathVariable BigInteger userId) {
        userService.deleteUser(token, userId);
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
