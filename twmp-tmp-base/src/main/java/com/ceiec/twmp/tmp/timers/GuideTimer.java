/*
package com.ceiec.twmp.tmp.timers;

import com.ceiec.twmp.tmp.timers.tasks.impl.KafkaListenTask;
import com.ceiec.twmp.tmp.enums.ETimerTasks;
import com.ceiec.twmp.tmp.timers.tasks.IBaseTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.ceiec.twmp.tmp.timers.tasks.impl.AccountCrawlerTask;

*/
/**
 * CreateDate：2018/12/10
 * Author：wenliang
 * Description: timer for guide system
 **//*

public class GuideTimer {

    */
/** tasks to be execute *//*

    private List<IBaseTask> tasks = new ArrayList<>();

    */
/** task executor *//*

    private ExecutorService exec;

    */
/**
     * construction function
     *//*

    public GuideTimer() {
        tasks.save(new KafkaListenTask(ETimerTasks.KAFKA_LISTEN));
//        tasks.save(new AccountCrawlerTask(ETimerTasks.ACCOUNT_CRAWLER));
        exec = Executors.newFixedThreadPool(tasks.size());
    }

    */
/**
     * start all tasks
     *//*

    public void startTimers() {
        for (IBaseTask task : tasks) {
            exec.execute(() -> {
                Timer timer = new Timer(task.getTaskName(), true);
                timer.schedule((TimerTask) task, task.delay(), task.getTaskInterval());
            });
        }
    }
}
*/
