package com.ceiec.twmp.tmp.vo.incident.result;

/**
 * @title: PushAlarmResultVO </br>
 * @createDate: 2019/4/19 17:55 </br>
 * @author: shihsh  </br>
 * @description: 推送警情结果 </br>
 * @version: V1.0
 **/


public class PushAlarmResultVO {
    private boolean success;

    private String message;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
