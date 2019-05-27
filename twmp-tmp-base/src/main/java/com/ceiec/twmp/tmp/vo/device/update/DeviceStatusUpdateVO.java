package com.ceiec.twmp.tmp.vo.device.update;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-03 11:55
 **/
public class DeviceStatusUpdateVO {

    private Long deviceId;

    private Byte opeStatus;

    private String comment;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Byte getOpeStatus() {
        return opeStatus;
    }

    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
