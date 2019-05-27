package com.ceiec.twmp.tmp.vo.gps;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps file info
 * @create 2019-03-29 11:19
 **/
public class RedisGpsFileInfo {

    private String deviceNumber;

    private String taskCode;

    private String date;

    private String fileName;

    private String path;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
