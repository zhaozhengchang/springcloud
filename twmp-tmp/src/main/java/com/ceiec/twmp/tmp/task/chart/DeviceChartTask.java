package com.ceiec.twmp.tmp.task.chart;

import com.ceiec.twmp.tmp.mapper.TwmpSmAlarmEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmDeviceAlarmReportMapper;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import com.ceiec.twmp.tmp.vo.chart.query.QueryDaysVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:用于设备告警定时统计
 * @author: zhaozhengchang
 * @date: 2019/3/27 9:53
 */
@Component
public class DeviceChartTask {


    /**
     * redis锁使用
     */
    private static String LOCK_ALARM_REPORT = "lockAlarmReport";

    @Autowired
    TwmpSmDeviceAlarmReportMapper twmpSmDeviceAlarmReportMapper;
    @Autowired
    TwmpSmAlarmEfMapper twmpSmAlarmEfMapper;
    @Autowired
    DeviceRunTask deviceRunTask;


    /**
     * @description: Will be executed at 00:05:00
     * @Param:
     * @return: void
     * @author: zhaozhengchang
     * @date: 2019/3/27 11:38
     */

    @Scheduled(cron = "0 5 0 * * ? ")
    public void countAlarmReport() {
        if (! RedisUtils.checkRedisKey(LOCK_ALARM_REPORT)) {

            //执行补偿任务
            compensateTask();

            //获取昨天时间
            String yesterday = DateFormatUtils.dateToString(DateUtils.getBeforeDay(1),null);
            //执行昨天的统计任务,加锁，避免多实例重复统计
            ExecutorService pool = new ThreadPoolExecutor(1, 1,
                    60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                    new SelfDefinedThreadFactory("deviceChartTask"));
            try {
                pool.submit(new DeviceTask(twmpSmDeviceAlarmReportMapper, twmpSmAlarmEfMapper, deviceRunTask, yesterday));
            } finally {
                pool.shutdown();
            }
        }
    }

    /**
     * @description:进行任务补偿
     * @return: void
     * @author: zhaozhengchang
     * @date: 2019/3/27 12:21
     */
    public void compensateTask() {
            //查看最近days天内有没有需要补偿的任务
            int days = 30;
        //获取昨天时间
        String yesterday = DateFormatUtils.dateToString(DateUtils.getBeforeDay(1),null);
             ExecutorService pool = new ThreadPoolExecutor(5, 5,
                60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new SelfDefinedThreadFactory("deviceChartCompensateTask"));
            try {
                //获取需要补偿的日期列表
                List<String> compensateList = getCompensateList(yesterday, days);
                //有补偿日期时进行补偿
                if (compensateList != null && compensateList.size() > 0) {
                    for (String day : compensateList) {
                        //执行补偿任务
                        pool.submit(new DeviceTask(twmpSmDeviceAlarmReportMapper, twmpSmAlarmEfMapper,deviceRunTask, day));
                    }
                }
            } finally {
                pool.shutdown();
            }
    }

    /**
     * @description:获取需要补偿的日期列表
     * @Param: day
     * @return: java.util.List<java.lang.String>
     * @author: zhaozhengchang
     * @date: 2019/3/26 16:18
     */
    private List<String> getCompensateList(String today, int days) {


        //获取前days天不需要进行补偿的日期列表
        List<String> dayList = twmpSmDeviceAlarmReportMapper.selectDays(new QueryDaysVO(days, today));
        //需要补偿的日期列表
        List<String> compensateList = new ArrayList<>();
        //去除昨天，所以此处是2
        for (int i = days; i >= 2; i--) {
            String dayTemp = DateFormatUtils.dateToString(DateUtils.getBeforeDay(i), null);
            if (!dayList.contains(dayTemp)) {
                compensateList.add(dayTemp);
            }
        }
        return compensateList;
    }

}


/**
 * @description:执行
 * @Param: null
 * @return:
 * @author: zhaozhengchang
 * @date: 2019/3/27 15:01
 */
class DeviceTask implements Runnable {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TwmpSmDeviceAlarmReportMapper twmpSmDeviceAlarmReportMapper;
    private TwmpSmAlarmEfMapper twmpSmAlarmEfMapper;
    private String day;
    private DeviceRunTask deviceRunTask;

    @Override
    public void run() {

        deviceRunTask.task(twmpSmAlarmEfMapper,twmpSmDeviceAlarmReportMapper,day);

    }

    public DeviceTask(TwmpSmDeviceAlarmReportMapper twmpSmDeviceAlarmReportMapper, TwmpSmAlarmEfMapper twmpSmAlarmEfMapper,
            DeviceRunTask deviceRunTask, String day) {
        this.twmpSmDeviceAlarmReportMapper = twmpSmDeviceAlarmReportMapper;
        this.twmpSmAlarmEfMapper = twmpSmAlarmEfMapper;
        this.day = day;
        this.deviceRunTask = deviceRunTask;
    }

}

























