package com.ceiec.twmp.tmp.vo.parameter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * CreateDate：2019/1/21 <br/>
 * Author：wenliang <br/>
 * Description: The type Sys parameter set vo.
 **/
public class SysParameterSetVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = -2531338389614571635L;

    /** traceLengthTime 轨迹跟踪时间长度*/
    @NotNull(message = "message.common.message.notnull")
    private Byte traceLengthTime;

    /** oneKeyAlarmlimit 一键报警自动分配上限*/
    @NotNull(message = "message.common.message.notnull")
    private Byte oneKeyAlarmlimit;

    /** subscribeLimit 车辆订阅上限*/
    @NotNull(message = "message.common.message.notnull")
    private Byte subscribeLimit;

    /** oneKeyAlarmRequestLimit 已经报警任务申领上限*/
    @NotNull(message = "message.common.message.notnull")
    private Byte oneKeyAlarmRequestLimit;

    /**
     * Gets trace length time.
     *
     * @return the trace length time
     */
    public Byte getTraceLengthTime() {
        return traceLengthTime;
    }

    /**
     * Sets trace length time.
     *
     * @param traceLengthTime the trace length time
     */
    public void setTraceLengthTime(Byte traceLengthTime) {
        this.traceLengthTime = traceLengthTime;
    }

    /**
     * Gets one key alarmlimit.
     *
     * @return the one key alarmlimit
     */
    public Byte getOneKeyAlarmlimit() {
        return oneKeyAlarmlimit;
    }

    /**
     * Sets one key alarmlimit.
     *
     * @param oneKeyAlarmlimit the one key alarmlimit
     */
    public void setOneKeyAlarmlimit(Byte oneKeyAlarmlimit) {
        this.oneKeyAlarmlimit = oneKeyAlarmlimit;
    }

    /**
     * Gets subscribe limit.
     *
     * @return the subscribe limit
     */
    public Byte getSubscribeLimit() {
        return subscribeLimit;
    }

    /**
     * Sets subscribe limit.
     *
     * @param subscribeLimit the subscribe limit
     */
    public void setSubscribeLimit(Byte subscribeLimit) {
        this.subscribeLimit = subscribeLimit;
    }

    /**
     * Gets one key alarm request limit.
     *
     * @return the one key alarm request limit
     */
    public Byte getOneKeyAlarmRequestLimit() {
        return oneKeyAlarmRequestLimit;
    }

    /**
     * Sets one key alarm request limit.
     *
     * @param oneKeyAlarmRequestLimit the one key alarm request limit
     */
    public void setOneKeyAlarmRequestLimit(Byte oneKeyAlarmRequestLimit) {
        this.oneKeyAlarmRequestLimit = oneKeyAlarmRequestLimit;
    }
}
