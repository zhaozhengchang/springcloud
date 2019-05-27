package com.ceiec.twmp.tmp.task;

import com.ceiec.twmp.tmp.EConfig;
import com.ceiec.twmp.tmp.services.IMonitorService;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description:for save gps into xml
 * @create 2019-03-29 16:40
 **/
@Component
public class GpsSaveFileTask {
    private static final String GPS_SAVE_FILE = "gpsSaveFile";

    @Autowired
    private Environment env;

    @Autowired
    private IMonitorService monitorService;


    /*************************************************************************************************************************************
     ** @Description  save gps file  including   compensate task
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/29 17:28
     **
     ************************************************************************************************************************************/
    //will start at 00:02:00 every day
    @Scheduled(cron = "0 2 0 * * ?")
    public void gpsSaveFileJob() throws UnsupportedEncodingException {

        if(!RedisUtils.checkRedisKey(GPS_SAVE_FILE)){
            int interval = Integer.parseInt(env.getProperty(EConfig.GPS_SAVE_INTERVAL));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-interval+1);
            Date startTime = calendar.getTime();

            for(int i=0; i<interval; i++){
                monitorService.saveGpsFile(new Date(startTime.getTime()+i*24*60*60*1000L));
            }

            RedisUtils.delRedisKey(GPS_SAVE_FILE);
        }

    }
}
