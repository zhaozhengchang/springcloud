package com.ceiec.twmp.tmp.vo.gps;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps result for front
 * @create 2019-03-28 12:01
 **/
public class GpsResultVO  {

    private String[] noDataTime;

    private String date;

    private List<GpsInfo> gpsInfoList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<GpsInfo> getGpsInfoList() {
        return gpsInfoList;
    }

    public void setGpsInfoList(List<GpsInfo> gpsInfoList) {
        this.gpsInfoList = gpsInfoList;
    }

    public String[] getNoDataTime() {
        return noDataTime;
    }

    public void setNoDataTime(String[] noDataTime) {
        this.noDataTime = noDataTime;
    }
}
