package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.result.DeviceUsedReportVO;
import com.ceiec.twmp.tmp.vo.chart.result.PersonAlarmTop10MaxNumResultVO;

import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 9:34
 * Description:
 **/
public interface IChartService {
    /**
     * @description: 告警日期分布查询
     * @Param: token
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/26 9:44
     */
    List<TwmpSmDeviceAlarmReport> queryAlarm(String token, ChartQueryVO chartQueryVO);

    /**
     * @description:按告警类型统计数量（饼状图）
     * @Param: token
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/27 16:21
     */
    List<TwmpSmDeviceAlarmReport> queryalarmType(String token, ChartQueryVO chartQueryVO);

    /**
     * @description:人员告警前十统计
     * @Param: token
     * @Param: chartQueryVO
     * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
     * @author: zhaozhengchang
     * @date: 2019/3/28 15:36
     */
    PersonAlarmTop10MaxNumResultVO personAlarmCountTOP10(String token, ChartQueryVO chartQueryVO);

    /**
     * @description:设备使用率查询
     * @Param: token
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceUsedReport>
     * @author: zhaozhengchang
     * @date: 2019/4/23 15:55
     */
    List<DeviceUsedReportVO> deviceUsageRate(String token, ChartQueryVO chartQueryVO);
}
