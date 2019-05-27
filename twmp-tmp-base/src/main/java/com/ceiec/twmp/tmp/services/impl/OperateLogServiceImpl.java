package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.mapper.TwmpLogOperateMapper;
import com.ceiec.twmp.tmp.services.IOperateLogService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.ExcelUtils;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.query.OperateLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.hsqldb.OpTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-20 17:35
 * Description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OperateLogServiceImpl implements IOperateLogService {
    // 导出日志的文件名
    private static final String[] EXPORT_FILE_NAME = {"export.operateLog.deviceManageoLog", "export.operateLog.personManageLog",
            "export.operateLog.departmentManageLog", "export.operateLog.taskManageLog","export.operateLog.documentManageLog",
            "export.operateLog.parameterManageLog", "export.operateLog.approvalManageLog" , "export.operateLog.userManageLog"};


    // 导出的表头第一项：操作对象
    private static final String[] EXPROT_OPERATE_NAME = {"export.operateLog.deviceNumber", "export.operateLog.person",
            "export.operateLog.departmentName", "export.operateLog.taskCode", "export.operateLog.documentName", "export.operateLog.parameterName",
            "export.operateLog.taskCode",   "export.operateLog.userName"};

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @Autowired
    TwmpLogOperateMapper twmpLogOperateMapper;


    /**
     * @description: 操作日志列表查询
     * @param: token
     * @param: operateLogQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO>
     * @author: shihsh
     * @date: 2019/3/21
     */
    @Override
    public PagedItemsVO<OperateLogQueryResultVO> listOperateLog(String token, OperateLogQueryVO operateLogQueryVO) {
        String language = UserInfoRedis.getUser(token).getLanguage();
        // 组织机构权限
        operateLogQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        operateLogQueryVO.setStartTimeDate(DateFormatUtils.stringToDateTime(operateLogQueryVO.getStartTime()));
        operateLogQueryVO.setEndTimeDate(DateFormatUtils.stringToDateTime(operateLogQueryVO.getEndTime()));
        int total = twmpLogOperateMapper.countLogTotal(operateLogQueryVO);
        //分页
        PageHelper.startPage(operateLogQueryVO.getPageNo(), operateLogQueryVO.getPageSize(), " operate_time desc");

        List<OperateLogQueryResultVO> operateLogQueryResultVOList = twmpLogOperateMapper.getOperateLogListByPage(operateLogQueryVO);
        for (OperateLogQueryResultVO operateLogQueryResultVO : operateLogQueryResultVOList) {
            OperateType operateType = OperateType.get(operateLogQueryResultVO.getOperateType());
            String operateTypeString = localeMessageSourceService.getMessageLocal(operateType.nameCode, language);
            String timeString =  DateFormatUtils.dateTimeToString(operateLogQueryResultVO.getOperateTimeDate());
            OperateObjectType operateObjectType = OperateObjectType.get(operateLogQueryResultVO.getOperateObjectType());
            String comment = localeMessageSourceService.getMessageLocal(operateObjectType.comment, language);
            operateLogQueryResultVO.setContent(comment.replace("{0}", operateLogQueryResultVO.getOperator()).replace("{1}", timeString)
                    .replace("{2}", operateTypeString).replace("{3}", operateLogQueryResultVO.getOperateName()));
        }
        return new PagedItemsVO<>(total, operateLogQueryResultVOList);
    }

    @Override
    public void exportOperateLog(String token, OperateLogQueryVO operateLogQueryVO, HttpServletResponse response) throws IOException {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        String language = userInfo.getLanguage();
        operateLogQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        operateLogQueryVO.setDepartmentIds(new ArrayList<>());
        if (!StringUtils.isNullOrEmpty(operateLogQueryVO.getStartTime())) {
            operateLogQueryVO.setStartTimeDate(DateFormatUtils.stringToDateTime(operateLogQueryVO.getStartTime()));
        }
        if (!StringUtils.isNullOrEmpty(operateLogQueryVO.getEndTime())) {
            operateLogQueryVO.setEndTimeDate(DateFormatUtils.stringToDateTime(operateLogQueryVO.getEndTime()));
        }
        List<OperateLogQueryResultVO> list = twmpLogOperateMapper.getOperateLogList(operateLogQueryVO);
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 不能使用中文命名
        String fileName = localeMessageSourceService.getMessageLocal(EXPORT_FILE_NAME[operateLogQueryVO.getOperateObjectType()-1], language);
        String operateName = localeMessageSourceService.getMessageLocal(EXPROT_OPERATE_NAME[operateLogQueryVO.getOperateObjectType()-1],language);
        List<String> titleList = new ArrayList<>();
        titleList.add(operateName);
        titleList.add(localeMessageSourceService.getMessageLocal("export.operateLog.operateType", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.operateLog.operator", language));
        titleList.add(localeMessageSourceService.getMessageLocal("export.operateLog.operateTime", language));
        HSSFSheet worksheet = workbook.createSheet(fileName);
        ExcelUtils.buildTitle(worksheet, titleList);
        String[][] dataString = new String[list.size()][titleList.size()];
        for (int i = 0; i < list.size(); i++) {
            OperateLogQueryResultVO operateLogQueryResultVO = list.get(i);
            dataString[i][0] = operateLogQueryResultVO.getOperateName();
            OperateType operateType = OperateType.get(operateLogQueryResultVO.getOperateType());
            String operateTypeString = localeMessageSourceService.getMessageLocal(operateType.nameCode, language);
            dataString[i][1] = operateTypeString;
            dataString[i][2] = operateLogQueryResultVO.getOperator();
            String timeString =  DateFormatUtils.dateTimeToString(operateLogQueryResultVO.getOperateTimeDate());
            dataString[i][3] = timeString;
        }
        ExcelUtils.buildContent(worksheet, dataString);
        ExcelUtils.writeExcel(worksheet, fileName, response);

    }


}
