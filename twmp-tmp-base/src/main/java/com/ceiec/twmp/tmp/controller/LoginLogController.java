package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.ILoginLogService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.systemlog.login.query.LoginLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.login.result.LoginLogResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version V1.0
 * @title: LoginLogController </br>
 * @createDate: 2019/3/18 21:42 </br>
 * @author: shihsh </br>
 * @description: 登陆日志管理 </br>
 **/

@RestController
@RequestMapping("/log")
public class LoginLogController {

    @Autowired
    private ILoginLogService loginLogService;

    /**
    *
    * @description: 分页查询，用户登陆日志
    * @param: token
    * @param: loginLogQueryVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/19
    */
    @PostMapping("/userLoginQuery")
    public ResponseContent queryUserLogin(@RequestHeader String token, @RequestBody LoginLogQueryVO loginLogQueryVO) {
        PagedItemsVO<LoginLogResultVO> loginLogPages = loginLogService.getLoginLogByPage(token, loginLogQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, loginLogPages);
    }

    /**
    *
    * @description:  导出登陆日志
    * @param: token
    * @param: loginLogQueryVO
    * @param: response
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/4/15
    */

    @PostMapping("/userLoginExport")
    public ResponseContent exportLoginLog(@RequestHeader String token, @RequestBody LoginLogQueryVO loginLogQueryVO, HttpServletResponse response) throws IOException {
        loginLogService.exportLoginLog(token, loginLogQueryVO, response);
        return new ResponseContent(ResponseType.SUCCESS);
    }

}
