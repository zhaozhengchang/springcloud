package com.ceiec.twmp.tmp.vo.ope;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-12 9:42
 **/
public class OpeUpdateVO {

    private Long deviceId;

    private Long opeId;

    private String fileUrl;

    private Byte opeStatus;

    public Byte getOpeStatus() {
        return opeStatus;
    }

    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getOpeId() {
        return opeId;
    }

    public void setOpeId(Long opeId) {
        this.opeId = opeId;
    }
}
