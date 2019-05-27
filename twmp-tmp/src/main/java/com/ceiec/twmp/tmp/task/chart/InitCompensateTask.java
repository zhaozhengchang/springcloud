package com.ceiec.twmp.tmp.task.chart;

import com.ceiec.twmp.tmp.task.GpsSaveFileTask;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @description:系统启动时补偿设备报警统计表
 * @author: zhaozhengchang
 * @date: 2019/3/27 9:53
 */
@Component
@Order(value=10)
public class InitCompensateTask implements CommandLineRunner {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(InitCompensateTask.class);


    @Autowired
    private GpsSaveFileTask gpsSaveFileTask;


    @Autowired
    DeviceChartTask deviceChartTask;
    @Autowired
    PersonChartTask personChartTask;
    private static String LOCK_ALARM_REPORT = "LOCK_ALARM_REPORT";

    @Override
    public void run(String... args) {
        logger.info("report compensateTask start ...");
        //加锁，重启系统时防止重复统计
        if (! RedisUtils.checkRedisKey(LOCK_ALARM_REPORT)) {
            //执行设备告警统计补偿任务
            deviceChartTask.compensateTask();
            //执行人员告警统计补偿任务
            personChartTask.compensateTask();
        }
        logger.info("report compensateTask end ...");


        try{
            gpsSaveFileTask.gpsSaveFileJob();
        }catch (Exception e){
            logger.error("gps save file task is failed ",e );
        }
        logger.info("gps save file task end ...");

    }


}
