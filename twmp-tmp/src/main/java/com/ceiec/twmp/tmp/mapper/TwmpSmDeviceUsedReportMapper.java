package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceUsedReport;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.result.DeviceUsedReportVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TwmpSmDeviceUsedReportMapper extends Mapper<TwmpSmDeviceUsedReport> {
    /**
     * @description:设备使用率查询
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceUsedReport>
     * @author: zhaozhengchang
     * @date: 2019/4/23 16:02
     */
    List<DeviceUsedReportVO> deviceUsageRate(ChartQueryVO chartQueryVO);
}