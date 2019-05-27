package com.ceiec.twmp.tmp.task.chart;

import com.ceiec.twmp.tmp.mapper.TwmpBsPersonCriminalEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmAlarmEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmDeviceAlarmPersonReportMapper;
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
import java.util.concurrent.*;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-28 9:59
 * Description:用于人员告警定时统计
 **/
@Component
public class PersonChartTask {


    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String LOCK_ALARM_REPORT = "LOCK_ALARM_REPORT";


    @Autowired
    TwmpSmDeviceAlarmPersonReportMapper twmpSmDeviceAlarmPersonReportMapper;
    @Autowired
    TwmpSmAlarmEfMapper twmpSmAlarmEfMapper;
    @Autowired
    PersonRunTask personRunTask;

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
            String yesterday = DateFormatUtils.dateToString(DateUtils.getBeforeDay(1), null);

            //执行昨天的统计任务,加锁，避免多实例重复统计
            ExecutorService pool = new ThreadPoolExecutor(1, 1,
                    60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                    new SelfDefinedThreadFactory("personChartTask"));
            try {
                pool.submit(new PersonTask(twmpSmDeviceAlarmPersonReportMapper, twmpSmAlarmEfMapper,personRunTask, yesterday));
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
        String yesterday = DateFormatUtils.dateToString(DateUtils.getBeforeDay(1), null);
        ExecutorService pool = new ThreadPoolExecutor(5, 5,
                60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new SelfDefinedThreadFactory("personChartCompensateTask"));
        try {
            //获取需要补偿的日期列表
            List<String> compensateList = getCompensateList(yesterday, days);
            //有补偿日期时进行补偿
            if (compensateList != null && compensateList.size() > 0) {
                for (String day : compensateList) {
                    //执行补偿任务
                    pool.submit(new PersonTask(twmpSmDeviceAlarmPersonReportMapper, twmpSmAlarmEfMapper,personRunTask, day));
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
        List<String> dayList = twmpSmDeviceAlarmPersonReportMapper.selectDays(new QueryDaysVO(days, today));
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
class PersonTask implements Runnable {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TwmpSmDeviceAlarmPersonReportMapper twmpSmDeviceAlarmPersonReportMapper;
    private TwmpSmAlarmEfMapper twmpSmAlarmEfMapper;
    private String day;
    private PersonRunTask personRunTask;

    @Override
    public void run() {

        personRunTask.task(twmpSmAlarmEfMapper,twmpSmDeviceAlarmPersonReportMapper,day);

    }

    public PersonTask(TwmpSmDeviceAlarmPersonReportMapper twmpSmDeviceAlarmPersonReportMapper, TwmpSmAlarmEfMapper twmpSmAlarmEfMapper,
                      PersonRunTask personRunTask , String day) {
        this.twmpSmDeviceAlarmPersonReportMapper = twmpSmDeviceAlarmPersonReportMapper;
        this.twmpSmAlarmEfMapper = twmpSmAlarmEfMapper;
        this.personRunTask = personRunTask;
        this.day = day;
    }
}























