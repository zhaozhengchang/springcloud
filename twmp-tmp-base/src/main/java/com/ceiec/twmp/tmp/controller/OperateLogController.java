package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IOperateLogService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.query.OperateLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-18 16:42
 * Description:
 **/
@RestController
@RequestMapping("/log")
public class OperateLogController {

    @Autowired
    IOperateLogService operateLogService;



    /**
     * @description:  操作日志查询
     * @param: token
     * @param: operateLogQueryVO
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: shihsh
     * @date: 2019/3/21
     */
    @PostMapping("/queryLog")
    public ResponseContent queryOperateLogByPage(@RequestHeader String token,@Valid @RequestBody OperateLogQueryVO operateLogQueryVO) {
        // 需要模板语进行拼接，等产品经理确定
        PagedItemsVO<OperateLogQueryResultVO> logOperateList = operateLogService.listOperateLog(token , operateLogQueryVO);
        return new ResponseContent(ResponseType.SUCCESS,logOperateList);
    }

    @PostMapping("/operateLogExport")
     public ResponseContent exportOperateLog(@RequestHeader String token, @RequestBody OperateLogQueryVO operateLogQueryVO, HttpServletResponse response) throws IOException {
        operateLogService.exportOperateLog(token, operateLogQueryVO, response);
        return new ResponseContent(ResponseType.SUCCESS);
    }
}