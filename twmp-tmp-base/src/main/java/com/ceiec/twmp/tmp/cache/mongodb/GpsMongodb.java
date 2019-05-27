package com.ceiec.twmp.tmp.cache.mongodb;

import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.mongodb.MongodbUtils;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import com.mongodb.BasicDBObject;
import org.bson.Document;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: base mongodb
 * @create 2019-03-28 9:42
 **/
public class GpsMongodb extends MongodbUtils {

    /*************************************************************************************************************************************
     ** @Description get gps info from mongodb
     ** @param: deviceNumber
     ** @param: startTime
     ** @param: endTime
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.gps.GpsInfo>
     ** @Author Ding
     ** @Date 2019/3/28 10:05
     **
     ************************************************************************************************************************************/
    public static List<GpsInfo> queryGps(String deviceNumber, String taskCode, Date startTime, Date endTime){
        BasicDBObject queryCondition = new BasicDBObject();
        List<GpsInfo> gpsInfoList = new ArrayList<>();

        queryCondition.append(GpsInfo.MONGO_DEVICE_NUMBER,new BasicDBObject("$eq", deviceNumber));
        queryCondition.append(GpsInfo.MONGO_TASK_CODE,new BasicDBObject("$eq", taskCode));
        queryCondition.append(GpsInfo.MONGO_GPS_TIME, new BasicDBObject("$gte", startTime.getTime()).append("$lte", endTime.getTime()));
        BasicDBObject sortObj = new BasicDBObject(GpsInfo.MONGO_GPS_TIME , 1);

        List<Document> documents = queryRecordList(queryCondition,sortObj);
        if(documents!=null && documents.size()>0){
            for(Document document: documents){
                GpsInfo gpsInfo = new GpsInfo();
                gpsInfo.setDeviceNumber(document.getString(GpsInfo.MONGO_DEVICE_NUMBER));
                gpsInfo.setDirection(document.getString(GpsInfo.MONGO_DIRECTION));
                gpsInfo.setLatitude(document.getString(GpsInfo.MONGO_LATITUDE));
                gpsInfo.setLongitude(document.getString(GpsInfo.MONGO_LONGITUDE));
                gpsInfo.setSpeed(document.getString(GpsInfo.MONGO_SPEED));
                gpsInfo.setTaskCode(document.getString(GpsInfo.MONGO_TASK_CODE));
                gpsInfo.setGpsTime(DateFormatUtils.dateTimeToString(new Date(document.getLong(GpsInfo.MONGO_GPS_TIME))));
                gpsInfoList.add(gpsInfo);
            }
        }

        return gpsInfoList;
    }
}
