package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.vo.gps.RedisGpsFileInfo;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps file redis
 * @create 2019-03-29 11:24
 **/
public class GpsFileRedis extends BaseRedis {

    public static void saveGpsFile(RedisGpsFileInfo redisGpsFileInfo){
        save(GPS_FILE, redisGpsFileInfo.getFileName(), redisGpsFileInfo);
    }

    public static RedisGpsFileInfo getGpsFile(String fileName){
        return (RedisGpsFileInfo)get(GPS_FILE, fileName, RedisGpsFileInfo.class);
    }

    public static void saveGpsFileJob(String date){
        save(GPS_FILE_JOB, date, 1);
    }

    public static boolean getGpsFileJob(String date){
        String key = GPS_FILE_JOB + SPLIT + date;

        if(!StringUtils.isNullOrEmpty(get(key)) && 1 == Integer.parseInt(get(key))){
            return true;
        }
        return false;
    }
}
