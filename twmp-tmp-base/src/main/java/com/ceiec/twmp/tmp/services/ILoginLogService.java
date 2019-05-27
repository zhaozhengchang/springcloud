package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.systemlog.login.query.LoginLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.login.result.LoginLogResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version V1.0
 * @title: ILoginLogService </br>
 * @createDate: 2019/3/18 21:45 </br>
 * @author: shihsh </br>
 * @description: 登录日志 </br>
 **/


public interface ILoginLogService {

    PagedItemsVO<LoginLogResultVO> getLoginLogByPage(String token, LoginLogQueryVO loginLogVO);

    void exportLoginLog(String token, LoginLogQueryVO loginLogQueryVO, HttpServletResponse response) throws IOException;
}
