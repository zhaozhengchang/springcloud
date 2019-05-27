package com.ceiec.twmp.tmp.timers.tasks;

/**
 * CreateDate：2018/12/10
 * Author：wenliang
 * Description: base interface for timer tasks
 **/
public interface IBaseTask {

    /**
     * get timer task name
     *
     * @return task name
     */
    String getTaskName();

    /**
     * get timer delay in milliseconds before task is to be executed
     *
     * @return delay milliseconds
     */
    long delay();

    /**
     * get timer task interval in milliseconds
     *
     * @return interval milliseconds
     */
    long getTaskInterval();
}
