package com.ceiec.twmp.tmp.vo.alarm.add;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-23 9:51
 **/
public class AlarmDisposeVO {

    private Long alarmId;

    private String comment;

    private Byte disposeType;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte getDisposeType() {
        return disposeType;
    }

    public void setDisposeType(Byte disposeType) {
        this.disposeType = disposeType;
    }
}
