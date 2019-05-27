package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.services.IChartService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.result.DeviceUsedReportVO;
import com.ceiec.twmp.tmp.vo.chart.result.PersonAlarmTop10MaxNumResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 9:22
 * Description:
 **/
@RestController
@RequestMapping("/chart")
public class ChartController {


    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IChartService chartService;

    @PostMapping("/queryAlarm")
    public ResponseContent queryAlarm(@RequestHeader String token, @Valid @RequestBody ChartQueryVO chartQueryVO) {

        List<TwmpSmDeviceAlarmReport> alarmReportList = chartService.queryAlarm(token , chartQueryVO);
        return new ResponseContent(ResponseType.SUCCESS , alarmReportList);

    }
    @PostMapping("/queryAlarmType")
    public ResponseContent queryalarmType(@RequestHeader String token, @Valid @RequestBody ChartQueryVO chartQueryVO) {

        List<TwmpSmDeviceAlarmReport> alarmReportList = chartService.queryalarmType(token , chartQueryVO);
        return new ResponseContent(ResponseType.SUCCESS , alarmReportList);

    }
    @PostMapping("/deviceUsageRate")
    public ResponseContent deviceUsageRate(@RequestHeader String token, @Valid @RequestBody ChartQueryVO chartQueryVO) {

        List<DeviceUsedReportVO> deviceUsedReportList = chartService.deviceUsageRate(token , chartQueryVO);
        return new ResponseContent(ResponseType.SUCCESS , deviceUsedReportList);

    }
    /**
     * @description:人员告警前十统计
     * @Param: token
     * @Param: chartQueryVO
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/28 15:36
     */
    @PostMapping("/personAlarmCount")
    public ResponseContent personAlarmCountTOP10(@RequestHeader String token, @Valid @RequestBody ChartQueryVO chartQueryVO) {

        PersonAlarmTop10MaxNumResultVO personAlarmTop10MaxNumResultVO = chartService.personAlarmCountTOP10(token , chartQueryVO);
        return new ResponseContent(ResponseType.SUCCESS , personAlarmTop10MaxNumResultVO);

    }



}
