package com.ceiec.twmp.tmp.task.chart;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.mapper.TwmpSmAlarmEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmDeviceAlarmReportMapper;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-04-15 16:39
 * Description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceRunTask {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void task(TwmpSmAlarmEfMapper twmpSmAlarmEfMapper,TwmpSmDeviceAlarmReportMapper twmpSmDeviceAlarmReportMapper , String day) {
        logger.info("{}  twmp_sm_alarm_ef  count  start...", day);
        //统计一天的告警数据
        List<TwmpSmDeviceAlarmReport> alarmReportList = twmpSmAlarmEfMapper.groupByAlarmTypeAndDepartment(day);
        //当天没有告警信息时，插入一条数量为0的记录，防止下次进行任务补偿(查询时不查询num为0的记录)
        if (alarmReportList == null || alarmReportList.size() == 0) {
            TwmpSmDeviceAlarmReport o = new TwmpSmDeviceAlarmReport();
            o.setReportId(SnowflakeIdWorkerUtil.generateId().longValue());
            o.setNum((long) 0);
            o.setAlarmType((byte) 1);
            o.setDepartmentId((long) 1);
            o.setAlarmTime(DateFormatUtils.stringToDate(day));
            twmpSmDeviceAlarmReportMapper.insert(o);
        }
        //插入统计结果表
        for (TwmpSmDeviceAlarmReport o : alarmReportList) {
            //设置id
            o.setReportId(SnowflakeIdWorkerUtil.generateId().longValue());
            //设置时间，此时间只是用于看是哪一天，不用精确到某天内的某个点
            o.setAlarmTime(DateFormatUtils.stringToDate(day));
            twmpSmDeviceAlarmReportMapper.insert(o);
        }
        logger.info("{}  twmp_sm_alarm_ef  count  end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", day);
    }
}
