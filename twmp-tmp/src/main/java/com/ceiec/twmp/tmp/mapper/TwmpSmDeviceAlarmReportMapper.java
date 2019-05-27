package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.query.QueryDaysVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TwmpSmDeviceAlarmReportMapper extends Mapper<TwmpSmDeviceAlarmReport> {
    /**
     * @description: 告警日期分布查询
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/26 9:47
     */
    List<TwmpSmDeviceAlarmReport> queryAlarm(ChartQueryVO chartQueryVO);

    /**
     * @description:
     * @Param: queryDaysVO
     * @return: java.util.List<java.lang.String>
     * @author: zhaozhengchang
     * @date: 2019/3/26 16:02
     */
    List<String> selectDays(QueryDaysVO queryDaysVO);

    /**
     * @description:按告警类型统计数量（饼状图）
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/27 16:25
     */
    List<TwmpSmDeviceAlarmReport> queryalarmType(ChartQueryVO chartQueryVO);
}