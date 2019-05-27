package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.mapper.TwmpLogLoginMapper;
import com.ceiec.twmp.tmp.services.ILoginLogService;
import com.ceiec.twmp.tmp.utils.ConvertUtil;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.ExcelUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.systemlog.login.query.LoginLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.login.result.LoginLogResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @title: LoginLogServiceImpl </br>
 * @createDate: 2019/3/18 21:53 </br>
 * @author: shihsh </br>
 * @description: 登录日志查询service </br>
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginLogServiceImpl implements ILoginLogService {
    private static final String LOGIN_LOG = "login_log";

    @Autowired
    private TwmpLogLoginMapper twmpLogLoginMapper;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    /**
    *
    * @description: 根据查询条件，分页查询登陆日志
    * @param: token
    * @param: loginLogQueryVO
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.systemlog.login.result.LoginLogResultVO>
    * @author: shihsh
    * @date: 2019/3/19
    */
    @Override
    public PagedItemsVO<LoginLogResultVO> getLoginLogByPage(String token, LoginLogQueryVO loginLogQueryVO) {

        // 组织机构权限
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String departmentIds = redisUserInfoVO.getOwnDepartmentId();
        List<Long> ownDepartmentIds = ConvertUtil.stringToLongList(departmentIds, ",");
        loginLogQueryVO.setDepartmentIds(ownDepartmentIds);
        loginLogQueryVO.setStartTimeDate(DateFormatUtils.stringToDateTime(loginLogQueryVO.getStartTime()));
        loginLogQueryVO.setEndTimeDate(DateFormatUtils.stringToDateTime(loginLogQueryVO.getEndTime()));

        Long total = twmpLogLoginMapper.countLoginLog(loginLogQueryVO);
        PageHelper.startPage(loginLogQueryVO.getPageNo(), loginLogQueryVO.getPageSize(), "login_time desc");
        List<LoginLogResultVO> list = twmpLogLoginMapper.queryLoginLogByPage(loginLogQueryVO);
        if (list == null || list.size() == 0) {
            return new PagedItemsVO<>(0, list);
        }
        for (LoginLogResultVO loginLogResultVO : list) {
            loginLogResultVO.setLoginTime(DateFormatUtils.dateTimeToString(loginLogResultVO.getLoginTimeDate()));
            loginLogResultVO.setLoginTimeDate(null);
            String logoutTime = DateFormatUtils.dateTimeToString(loginLogResultVO.getLogoutTimeDate());
            if (logoutTime == null) {
                logoutTime = "";
            }
            loginLogResultVO.setLogoutTime(logoutTime);
            loginLogResultVO.setLogoutTimeDate(null);
        }
        return new PagedItemsVO<>(total, list);
    }

    @Override
    public void exportLoginLog(String token, LoginLogQueryVO loginLogQueryVO, HttpServletResponse response) throws IOException {
        // 组织机构权限
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        String departmentIds = userInfo.getOwnDepartmentId();
        List<Long> ownDepartmentIds = ConvertUtil.stringToLongList(departmentIds, ",");
        loginLogQueryVO.setDepartmentIds(ownDepartmentIds);
        loginLogQueryVO.setStartTimeDate(DateFormatUtils.stringToDateTime(loginLogQueryVO.getStartTime()));
        loginLogQueryVO.setEndTimeDate(DateFormatUtils.stringToDateTime(loginLogQueryVO.getEndTime()));
        List<LoginLogResultVO> list = twmpLogLoginMapper.getLoginLogList(loginLogQueryVO);

        List<String> titleFields = createTitle(userInfo.getLanguage());
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(LOGIN_LOG);
        ExcelUtils.buildTitle(worksheet, titleFields);
        String[][] dataString = new String[list.size()+1][titleFields.size()];
        for (int i = 0; i < list.size(); i++) {
            LoginLogResultVO loginLogResultVO = list.get(i);
            dataString[i][0] = loginLogResultVO.getUserName();
            dataString[i][1] = DateFormatUtils.dateTimeToString(loginLogResultVO.getLoginTimeDate());
            dataString[i][2] = DateFormatUtils.dateTimeToString(loginLogResultVO.getLogoutTimeDate());
            dataString[i][3] = loginLogResultVO.getRoleName();
            dataString[i][4] = loginLogResultVO.getDepartmentName();
        }
        ExcelUtils.buildContent(worksheet, dataString);
        ExcelUtils.writeExcel(worksheet, LOGIN_LOG, response);
    }

    private List<String> createTitle(String language) {
        List<String> titleList = new ArrayList<>();
        titleList.add(localeMessageSourceService.getMessageLocal("export.loginLog.user", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.loginLog.loginTime", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.loginLog.logoutTime", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.loginLog.roleName", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.loginLog.departmentName", language));
        return titleList;
    }
}
