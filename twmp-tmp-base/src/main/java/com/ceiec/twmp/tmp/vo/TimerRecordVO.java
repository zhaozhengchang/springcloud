package com.ceiec.twmp.tmp.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/12/10
 * Author：wenliang
 * Description: store timer concurrent record information
 **/
public class TimerRecordVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = 6230778187268440780L;

    /** timer name */
    private String timerName;

    /** execute interval */
    private int executeInterval;

    /** timer interval unit */
    private String intervalUnit;

    /** last execute time */
    private Date lastExecuteTime;

    /** last execute ip */
    private String lastExecuteIP;

    public String getTimerName() {
        return timerName;
    }

    public void setTimerName(String timerName) {
        this.timerName = timerName;
    }

    public int getExecuteInterval() {
        return executeInterval;
    }

    public void setExecuteInterval(int executeInterval) {
        this.executeInterval = executeInterval;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public String getLastExecuteIP() {
        return lastExecuteIP;
    }

    public void setLastExecuteIP(String lastExecuteIP) {
        this.lastExecuteIP = lastExecuteIP;
    }
}
