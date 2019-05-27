package com.ceiec.twmp.tmp.common.enums;

/**
 * @author Ding
 * @version V1.0
 * @Description: system parameter
 * @create 2019-03-25 15:11
 **/
public enum  ESystemParameter {

    /**轨迹查询时间长度**/
    TRACK_TIME_SPAN("system.parameter.name.trackTimeSpan"),
    /**任务自动分配个数**/
    AAA_NUM("system.parameter.name.alarmAutomaticAllocationNum"),
    /**任务手动申领个数**/
    AMA_NUM("system.parameter.name.alarmManualApplicationNum"),
    /**设备离线时间**/
    DEVICE_OFFLINE_TIME("system.parameter.name.deviceOfflineTime"),
    /**低电量告警**/
    LOW_POWER_ALARM("system.parameter.name.lowPowerAlarm"),
    /**监控任务到期提前通知时间**/
    MTEADN_TIME("system.parameter.name.monitoringTaskExpirationAdvanceNoticeTime");


    private String name;


    ESystemParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
