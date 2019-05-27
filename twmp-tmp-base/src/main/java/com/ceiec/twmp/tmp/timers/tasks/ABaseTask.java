/*
package com.ceiec.twmp.tmp.timers.tasks;

import com.ceiec.twmp.tmp.enums.ETimerIntervalUnit;
import com.ceiec.twmp.tmp.enums.ETimerTasks;
import com.ceiec.twmp.tmp.mapper.ITimerMapper;
import com.ceiec.twmp.tmp.utils.SpringUtil;
import com.ceiec.twmp.tmp.vo.TimerRecordVO;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimerTask;

*/
/**
 * CreateDate：2018/12/10
 * Author：wenliang
 * Description: base abstract class of timer tasks
 **//*

public abstract class ABaseTask extends TimerTask implements IBaseTask {

    */
/** logger *//*

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    */
/** timer mapper interface *//*

    private ITimerMapper timerMapper = SpringUtil.getBean("ITimerMapper", ITimerMapper.class);

    */
/** timer name *//*

    private String timerName;

    */
/** timer interval in seconds *//*

    private int intervalSeconds;

    */
/** timer delay in milliseconds *//*

    private long delayMilliSeconds;

    */
/**
     * construction function
     *
     * @param timerName timer name
     *//*

    public ABaseTask(String timerName) {
        this.timerName = timerName;

        //get timer execute interval
        TimerRecordVO timerRecord = timerMapper.getTimer(timerName);
        int interval = timerRecord.getExecuteInterval();
        String intervalUnit = timerRecord.getIntervalUnit();
        switch (ETimerIntervalUnit.getUnitEnum(intervalUnit)) {
            case UNIT_SECOND:
                intervalSeconds = interval;
                break;
            case UNIT_MINUTE:
                intervalSeconds = interval * 60;
                break;
            case UNIT_HOUR:
                intervalSeconds = interval * 60 * 60;
                break;
            case UNIT_DAY:
                intervalSeconds = interval * 24 * 60 * 60;
                break;
            default:
                throw new RuntimeException("unhandled timer interval unit, please complete your code");
        }

        //get timer delay
        Date lastExecTime = timerRecord.getLastExecuteTime();
        if ((Objects.isNull(lastExecTime)) || (DateUtils.getTwoDateDistance(DateUtils.format(lastExecTime), DateUtils.format(new Date())) >= intervalSeconds)) { //not executed in this period
            delayMilliSeconds = 0; //execute task immediately
        } else { //executed in this period
            //execute task in the start of next period(from moment of last execute)
            Calendar nextExecTime = Calendar.getInstance();
            nextExecTime.setTime(lastExecTime);
            nextExecTime.save(Calendar.SECOND, intervalSeconds);
            delayMilliSeconds = DateUtils.getTwoDateDistance(DateUtils.format(new Date()), DateUtils.format(nextExecTime.getTime())) * 1000;
        }
    }

    @Override
    public final String getTaskName() {
        return timerName;
    }

    @Override
    public long delay() {
        return delayMilliSeconds;
    }

    @Override
    public final long getTaskInterval() {
        return intervalSeconds * 1000;
    }

    @Override
    public void run() {
        try {
            //lock timer record and get last execute information
            TimerRecordVO timerRecord = timerMapper.getTimerAndLock(timerName);

            //execute task only when the task not be executed in this period
            int periodSeconds = timerRecord.getExecuteInterval();
            if ((timerRecord.getLastExecuteTime() == null)
                    || ((DateUtils.getTwoDateDistance(DateUtils.format(timerRecord.getLastExecuteTime()), DateUtils.format(new Date()))) >= periodSeconds)) {
                if (!timerName.equals(ETimerTasks.KAFKA_LISTEN.getTaskName())) {
                    logger.info(timerName + " will execute at IP:" + InetAddress.getLocalHost().getHostAddress() + ". this log need to be deleted later");
                }
                executeTask();
                timerMapper.updateTimer(timerName, new Date(), InetAddress.getLocalHost().getHostAddress());
            }
        } catch (Exception e) {
            logger.error("unable to execute timer task --- " + timerName, e);
        }
    }

    */
/**
     * execute task
     *//*

    protected abstract void executeTask();

    */
/**
     * log task execution
     *
     * @param taskName task name
     * @param logContent log content
     *//*

    protected final void logExecution(String taskName, String logContent) {
        try {
            logger.info("{} executed {} task, {}", InetAddress.getLocalHost().getHostAddress(), taskName, logContent);
        } catch (Exception e) {
            logger.info("log task " + taskName + " error", e);
        }
    }
}
*/
