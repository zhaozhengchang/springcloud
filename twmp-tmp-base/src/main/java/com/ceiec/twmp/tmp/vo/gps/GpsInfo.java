package com.ceiec.twmp.tmp.vo.gps;

import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import org.bson.Document;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps information
 * @create 2019-03-15 10:44
 **/
public class GpsInfo{
    public final static String MONGO_DEVICE_NUMBER = "dn";
    public final static String MONGO_LONGITUDE = "lng";
    public final static String MONGO_LATITUDE= "lat";
    public final static String MONGO_SPEED = "sp";
    public final static String MONGO_DIRECTION = "di";
    public final static String MONGO_GPS_TIME = "gt";
    public final static String MONGO_TASK_CODE = "tc";

    private String deviceNumber;  //dn in mongodb

    private String longitude; //lng in mongodb

    private String latitude; //lat in mongodb

    private String speed; //sp in mongodb

    private String direction;//di in mongodb

    private String gpsTime;//gt in mongodb

    private String taskCode;//link which subject, sub in mongodb  , here is task id

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }


    /*************************************************************************************************************************************
     ** @Description convert gps info into mongodb document
     ** @Return org.bson.Document
     ** @Author Ding
     ** @Date 2019/3/19 17:02
     **
     ************************************************************************************************************************************/
    public Document convertGpsToDocument(){
        Document tempDocument = new Document();
        tempDocument.put(MONGO_DEVICE_NUMBER, this.getDeviceNumber());
        tempDocument.put(MONGO_LONGITUDE, this.getLongitude());
        tempDocument.put(MONGO_LATITUDE, this.getLatitude());
        tempDocument.put(MONGO_SPEED, this.getSpeed());
        tempDocument.put(MONGO_DIRECTION, this.getDirection());
        tempDocument.put(MONGO_GPS_TIME, DateFormatUtils.stringToDate(this.getGpsTime(), DateFormatUtils.DATETIME_FORMAT_SYSTEM).getTime());
        tempDocument.put(MONGO_TASK_CODE, this.getTaskCode());
        return tempDocument;
    }
}
