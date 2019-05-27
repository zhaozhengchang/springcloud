package com.ceiec.twmp.tmp.algorithms;//package com.ceiec.twmp.tmp.algorithms;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ceiec.twmp.tmp.SystemInit;
//import com.ceiec.twmp.tmp.bo.KafkaTaskInfoBO;
//import ETaskStatus;
//import EKafkaTopic;
//import KafkaFactory;
//import com.ceiec.twmp.tmp.mapper.IAccountLibMapper;
//import com.ceiec.twmp.tmp.mapper.IGuideTaskMapper;
//import com.ceiec.twmp.tmp.mapper.ITaskDetailsMapper;
//import SpringUtil;
//import com.ceiec.twmp.tmp.vo.TaskPauseInfoVO;
//import com.ceiec.twmp.tmp.vo.UsableIPVO;
//import DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * CreateDate：2018/9/7 <br/>
// * Author：wenliang <br/>
// * Description: dispatch algorithm of sending guide task
// **/
//public class TaskDispatchAlgorithm {
//
//    /** logger */
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    /** guide task mapper */
//    private static IGuideTaskMapper guideTaskMapper;
//
//    /** task details mapper */
//    private static ITaskDetailsMapper taskDetailsMapper;
//
//    /** account lib mapper */
//    private static IAccountLibMapper accountLibMapper;
//
//    /** ip minimum use interval seconds */
//    private static long ipMinInterval;
//
//    /** waiting task list, sorted by plan execute time desc */
//    private static TreeSet<KafkaTaskInfoBO> taskQueue = new TreeSet<>();
//
//    /** asynchronous executors */
//    private ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//
//    /** random object */
//    private Random random = new Random();
//
//    /** is algorithm running now */
//    private static boolean isRunning = false;
//
//    /**
//     * init task dispatch algorithm
//     *
//     * @param ipInterval ip minimum use interval seconds
//     * @param unExecutedTasks not executed tasks when system restart
//     */
//    public void initAlgorithm(long ipInterval, List<KafkaTaskInfoBO> unExecutedTasks) {
//        ipMinInterval = ipInterval;
//        taskQueue.addAll(unExecutedTasks);
//        startDispatch();
//    }
//
//    /**
//     * save new tasks to execute
//     *
//     * @param newTasks new tasks
//     */
//    public synchronized void acceptNewTasks(List<KafkaTaskInfoBO> newTasks) {
//        taskQueue.addAll(newTasks);
//        startDispatch();
//    }
//
//    /**
//     * charge waiting task's plan execution time
//     *
//     * @param taskID task ID
//     * @param newExecTime new execution time
//     */
//    public synchronized void changeTaskExecutionTime(String taskID, Date newExecTime) {
//        for (KafkaTaskInfoBO taskInfo : taskQueue) {
//            if (taskInfo.getTask_id().equals(taskID)) {
//                taskInfo.setPlan_execute_time(newExecTime);
//            }
//        }
//        startDispatch();
//    }
//
//    /**
//     * execute waiting task immediately
//     *
//     * @param taskID task ID
//     */
//    public synchronized void executeWaitingTask(String taskID) {
//        for (KafkaTaskInfoBO taskInfo : taskQueue) {
//            if (taskInfo.getTask_id().equals(taskID)) {
//                taskInfo.setSub_task_status(ETaskStatus.STATUS_GUIDING.getStatusValue());
//                taskInfo.setPlan_execute_time(new Date());
//            }
//        }
//        startDispatch();
//    }
//
//    /**
//     * pause executing task or continue paused task
//     *
//     * @param pauseInfo task pause query information
//     */
//    public synchronized void pauseTask(TaskPauseInfoVO pauseInfo) {
//        for (KafkaTaskInfoBO taskInfo : taskQueue) {
//            if (taskInfo.getTask_id().equals(pauseInfo.getTaskId())) {
//                taskInfo.setSub_task_status(pauseInfo.isPause() ? ETaskStatus.STATUS_PAUSED.getStatusValue() : ETaskStatus.STATUS_GUIDING.getStatusValue());
//            }
//        }
//        startDispatch();
//    }
//
//    /**
//     * terminate task
//     *
//     * @param taskID task ID
//     */
//    public synchronized void terminateTask(String taskID) {
//        for (KafkaTaskInfoBO taskInfo : taskQueue) {
//            if (taskInfo.getTask_id().equals(taskID)) {
//                taskQueue.remove(taskInfo);
//            }
//        }
//        startDispatch();
//    }
//
//    /**
//     * start dispatching tasks
//     */
//    private synchronized void start() {
//        while (hasToExecuteTasks()) {
//            //get proper task and ip
//            KafkaTaskInfoBO taskInfo = getFirstTask();
//            if (taskInfo.getPlan_execute_time().after(new Date())) { //need more waiting
//                long intervals = DateUtils.getTwoDateDistance(DateUtils.format(new Date()), DateUtils.format(taskInfo.getPlan_execute_time()));
//                try {
//                    wait(intervals);
//                } catch (InterruptedException e) {
//                    //ignore
//                }
//                continue;
//            }
//            UsableIPVO ipInfo = SystemInit.getMostSuitIP(true);
//            if (Objects.isNull(ipInfo)) {
//                //no usable IP, wait 30 seconds and go on
//                try {
//                    wait(30000);
//                } catch (InterruptedException e) {
//                    //ignore
//                }
//                continue;
//            }
//            //make sure the select ip can be use now
//            long intervals = DateUtils.getTwoDateDistance(DateUtils.format(ipInfo.getLastUseTime()), DateUtils.format(new Date()));
//            if (intervals < ipMinInterval) {
//                try {
//                    //random guide time to avoid being recognized
//                    wait((ipMinInterval - intervals + random.nextInt(10)) * 1000);
//                } catch (InterruptedException e) {
//                    //ignore
//                }
//                continue;
//            }
//
//            //update ip usage information and execute task asynchronously
//            taskQueue.remove(taskInfo);
//            ipInfo.setUsable(false);
//            try {
//                executors.execute(() -> {
//                    //execute task
//                    taskInfo.setSend_time(System.currentTimeMillis());
//                    taskInfo.setProxy_ip(ipInfo.getIp());
//                    KafkaFactory.getKafkaProducer().sendMsg(EKafkaTopic.TOPIC_FROM_BACKGROUND, JSONObject.toJSONString(taskInfo));
//                    logger.info("send sub task {} to kafka successfully", taskInfo.getSub_task_id());
//                    logger.info("send message:" + JSONObject.toJSONString(taskInfo));
//
//                    //update ip usage information
//                    ipInfo.setUsable(true);
//                    ipInfo.setLastUseTime(new Date());
//                    SystemInit.resortUsableIPs();
//
//                    //update task status
//                    if (taskInfo.getSub_task_status() == ETaskStatus.STATUS_WAITING.getStatusValue()) {
//                        getTaskDetailsMapper().updateWaitingTaskDetail(taskInfo.getSub_task_id(), ETaskStatus.STATUS_GUIDING.getStatusValue());
//                        getGuideTaskMapper().updateWaitingTaskDetail(taskInfo.getTask_id(), ETaskStatus.STATUS_GUIDING.getStatusValue());
//                    } else if (!taskInfo.getCancel()) {
//                        getTaskDetailsMapper().updateTaskSendTime(taskInfo.getSub_task_id());
//                    }
//
//                    //update account's last use time
//                    getAccountLibMapper().updateAccountLastUseTime(taskInfo.getUser_info().getUser_id());
//                });
//            } catch (Exception e) {
//                logger.error("dispatch sub task " + taskInfo.getSub_task_id() + " error", e);
//                taskQueue.save(taskInfo);
//            }
//        }
//
//        //executing finished, stop algorithm
//        isRunning = false;
//    }
//
//    /**
//     * start task dispatch
//     */
//    private synchronized void startDispatch() {
//        executors.execute(() -> {
//            if (!isRunning) {
//                start();
//            } else {
//                notifyAll();
//            }
//        });
//    }
//
//    /**
//     * charge if there are tasks need to execute
//     *
//     * @return charge result
//     */
//    private boolean hasToExecuteTasks() {
//        for (KafkaTaskInfoBO task : taskQueue) {
//            if (task.getSub_task_status() != ETaskStatus.STATUS_PAUSED.getStatusValue()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * get first task need to execute
//     *
//     * @return task instance
//     */
//    private KafkaTaskInfoBO getFirstTask() {
//        for (KafkaTaskInfoBO task : taskQueue) {
//            if (task.getSub_task_status() != ETaskStatus.STATUS_PAUSED.getStatusValue()) {
//                return task;
//            }
//        }
//        throw new RuntimeException("no task found");
//    }
//
//    /**
//     * get guide task mapper
//     *
//     * @return guide task mapper
//     */
//    private IGuideTaskMapper getGuideTaskMapper() {
//        if (guideTaskMapper == null) {
//            guideTaskMapper = SpringUtil.getBean("IGuideTaskMapper", IGuideTaskMapper.class);
//        }
//        return guideTaskMapper;
//    }
//
//    /**
//     * get task detail mapper
//     *
//     * @return task detail mapper
//     */
//    private ITaskDetailsMapper getTaskDetailsMapper() {
//        if (taskDetailsMapper == null) {
//            taskDetailsMapper = SpringUtil.getBean("ITaskDetailsMapper", ITaskDetailsMapper.class);
//        }
//        return taskDetailsMapper;
//    }
//
//    /**
//     * get account lib mapper
//     *
//     * @return account lib mapper
//     */
//    private IAccountLibMapper getAccountLibMapper() {
//        if (accountLibMapper == null) {
//            accountLibMapper = SpringUtil.getBean("IAccountLibMapper", IAccountLibMapper.class);
//        }
//        return accountLibMapper;
//    }
//}
