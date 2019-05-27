package com.ceiec.twmp.tmp.task;

import com.ceiec.twmp.tmp.common.dict.TaskStatus;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.mapper.TwmpMonitorTaskEfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @title: MonitorTaskStatusCheck </br>
 * @createDate: 2019/4/10 9:55 </br>
 * @author: shihsh  </br>
 * @description: 检查监控任务的开始、结束时间是否达到，从而变更监控任务为监控中和监控完成状态 </br>
 * @version: V1.0
 **/

@Component
@Order(value = 103)
public class MonitorTaskStatusCheck implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(MonitorTaskStatusCheck.class);

    @Autowired
    private TwmpMonitorTaskEfMapper twmpMonitorTaskEfMapper;

    @Override
    public void run(String... args) {
        TaskStatusCheck taskStatusCheck = new TaskStatusCheck();
        taskStatusCheck.start();
    }

    class TaskStatusCheck extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 获取所有状态为安装完成和监控中的监控任务
                    List<TwmpMonitorTaskEf> taskList = twmpMonitorTaskEfMapper.getInstalledAndMonitoringTasks();
                    taskList.forEach(task -> {
                        Date startTime = task.getStartTime();
                        Date endTime = task.getEndTime();
                        Date now = new Date();
                        if (task.getTaskStatus() == TaskStatus.monitoring.value && now.after(endTime)) {
                            // 超过了结束时间，状态从监控中转变为已完成
                            task.setTaskStatus(TaskStatus.completed.value);
                            twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(task);
                            logger.info("监控结束时间已到，进入监控完成!");
                        } else if (task.getTaskStatus() == TaskStatus.initial_installed.value && now.after(startTime)) {
                            // 超过了开始时间，状态从安装完成转变为监控中
                            task.setTaskStatus(TaskStatus.monitoring.value);
                            twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(task);
                            logger.info("监控开始时间已到，进入监控中!");
                        }
                    });
                    TimeUnit.SECONDS.sleep(60);
                } catch (Exception e) {
                    logger.warn("the task status checking is failed", e);
                }
            }
        }
    }
}
