package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmPersonReport;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.query.QueryDaysVO;
import com.ceiec.twmp.tmp.vo.chart.result.PersonAlarmTop10ResultVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TwmpSmDeviceAlarmPersonReportMapper extends Mapper<TwmpSmDeviceAlarmPersonReport> {
    /**
     * @description:
     * @Param: queryDaysVO
     * @return: java.util.List<java.lang.String>
     * @author: zhaozhengchang
     * @date: 2019/3/26 16:02
     */
    List<String> selectDays(QueryDaysVO queryDaysVO);

    /**
     * @description:人员告警前十统计
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmPersonReport>
     * @author: zhaozhengchang
     * @date: 2019/3/28 15:42
     */
    List<PersonAlarmTop10ResultVO> personAlarmCountTOP10(ChartQueryVO chartQueryVO);
}