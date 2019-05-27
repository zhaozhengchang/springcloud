//package com.ceiec.twmp.tmp.timers.tasks.impl;
//
//import com.ceiec.twmp.gps.kafka.KafkaConsumer;
//import com.ceiec.twmp.gps.kafka.KafkaFactory;
//import com.ceiec.twmp.gps.kafka.KafkaResponse;
//import com.ceiec.twmp.tmp.enums.ETimerTasks;
//import com.ceiec.twmp.gps.kafka.EKafkaTopic;
//import com.ceiec.twmp.tmp.timers.tasks.ABaseTask;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//
///**
// * CreateDate：2018/12/10
// * Author：wenliang
// * Description: listen kafka server and process message received
// **/
//public class KafkaListenTask extends ABaseTask {
//
//    /** logger */
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    /** kafka consumer */
//    private KafkaConsumer kafkaConsumer;
//
////    /** guide target mapper */
////    private IGuideTargetMapper guideTargetMapper = SpringUtil.getBean("IGuideTargetMapper", IGuideTargetMapper.class);
////
////    /** task detail mapper */
////    private ITaskDetailsMapper taskDetailsMapper = SpringUtil.getBean("ITaskDetailsMapper", ITaskDetailsMapper.class);
////
////    /** guide task mapper */
////    private IGuideTaskMapper guideTaskMapper = SpringUtil.getBean("IGuideTaskMapper", IGuideTaskMapper.class);
////
////    /** account lib mapper */
////    private IAccountLibMapper accountLibMapper = SpringUtil.getBean("IAccountLibMapper", IAccountLibMapper.class);
////
////    /** account warn mapper */
////    private IAccountWarnMapper accountWarnMapper = SpringUtil.getBean("IAccountWarnMapper", IAccountWarnMapper.class);
//
//    /**
//     * construction function
//     *
//     * @param timerTask timer task enum
//     */
//    public KafkaListenTask(ETimerTasks timerTask) {
//        super(timerTask.getTaskName());
//        kafkaConsumer = KafkaFactory.getKafkaConsumer();
//        kafkaConsumer.setConsumerTopic(EKafkaTopic.TOPIC_WEB_FROM_BACKGROUND);
//    }
//
//    /**
//     * process received kafka message
//     */
//    @Override
//    public void executeTask() {
//        List<KafkaResponse> kafkaResponses = kafkaConsumer.detectResponse();
//        if (!CollectionUtils.isEmpty(kafkaResponses)) {
//            logExecution(getTaskName(), "received kafka message:" + kafkaResponses);
//            for (KafkaResponse response : kafkaResponses) {
////                int msgType = JSON.parseObject(response.getResponseMsg()).getInteger("task_type");
////                switch (EKafkaMsgType.getMsgTypeEnum(msgType)) {
////                    case MSG_POST:
////                    case MSG_COMMENT:
////                    case MSG_LIKE:
////                    case MSG_SHARE:
////                    case MSG_REPORT:
////                    case MSG_ADDFRIEND_FOLLOW:
////                        processGuideResultResponse(response.getResponseMsg());
////                        break;
////                    case MSG_TARGET_ACCOUNT_INFO:
////                        processTargetAccountResponse(response.getResponseMsg());
////                        break;
////                    case MSG_GUIDE_ACCOUNT_STATUS:
////                        processAccountStatusResponse(response.getResponseMsg());
////                        break;
////                    default:
////                        logger.error("unhandled kafka message type {}, please complete your code", msgType);
////                }
//            }
//        }
//    }
//
////    /**
////     * process kafka response about guide task execute result
////     *
////     * @param response kafka response
////     */
////    private void processGuideResultResponse(String response) {
////        TaskExecuteResponseBO taskResponse = JSON.parseObject(response, TaskExecuteResponseBO.class);
////
////        //update task detail record
////        TaskExecuteResultVO executeResult = new TaskExecuteResultVO();
////        executeResult.setDetailID(taskResponse.getSub_task_id());
////        executeResult.setReallyReportType(taskResponse.getReally_type());
////        executeResult.setExecSuccess(taskResponse.getResult() == 1);
////        executeResult.setWithdraw(taskResponse.isCancel());
////        executeResult.setPostUrl(taskResponse.getMessage());
////        executeResult.setFailReason(taskResponse.getFail_reason());
////        taskDetailsMapper.updateTaskDetail(executeResult);
////
////        //update guide task record
////        guideTaskMapper.updateGuideTask(taskResponse.getTask_id());
////
////        //inform calling client about task execute progress
////        TaskProgressSocketVO progressSocket = new TaskProgressSocketVO();
////        progressSocket.setTaskID(taskResponse.getTask_id());
////        progressSocket.setDetailID(taskResponse.getSub_task_id());
////        progressSocket.setAccountID(taskResponse.getUser_id());
////        if (!taskResponse.isCancel()) {
////            if (taskResponse.getResult() == 1) {
////                progressSocket.setMessageType(EProgressNote.NOTE_GUIDE_SUCCESS.getNoteTypeVal());
////            } else {
////                progressSocket.setMessageType(EProgressNote.NOTE_GUIDE_FAIL.getNoteTypeVal());
////            }
////        } else {
////            if (taskResponse.getResult() == 1) {
////                progressSocket.setMessageType(EProgressNote.NOTE_WITHDRAW_SUCCESS.getNoteTypeVal());
////            } else {
////                progressSocket.setMessageType(EProgressNote.NOTE_WITHDRAW_FAIL.getNoteTypeVal());
////            }
////        }
////        progressSocket.setSendTime(DateUtils.format(new Date(taskResponse.getSend_time())));
////        String creatorUser = guideTaskMapper.getTaskCreateUser(taskResponse.getTask_id());
////        String socketMsg = JSON.toJSONString(progressSocket);
////        boolean isSendSuccess = WebSocket.sendMessage(creatorUser, EPagePosition.TASK_PROGRESS, socketMsg);
////        if (isSendSuccess) {
////            logger.info("send web websocket message {} to request client succeed", socketMsg);
////        } else {
////            logger.info("send web websocket message {} to request client failed", socketMsg);
////        }
////    }
//
////    /**
////     * process kafka response about crawled guide target account information
////     *
////     * @param response kafka response
////     */
////    private void processTargetAccountResponse(String response) {
////        TargetAccountResponseBO targetAccountResponse = JSON.parseObject(response, TargetAccountResponseBO.class);
////
////        //update guide target information
////        GuideTargetVO guideTarget = new GuideTargetVO();
////        guideTarget.setTargetId(IDGenerator.generate32MD5ID(targetAccountResponse.getTarget_url()));
////        guideTarget.setTargetType(targetAccountResponse.getTarget_type());
////        guideTarget.setTargetHomepage(targetAccountResponse.getUser_data().getHomepage());
////        guideTarget.setTargetName(targetAccountResponse.getUser_data().getNickname());
////        guideTarget.setTargetAvatar(targetAccountResponse.getUser_data().getAvatar_url());
////        if (targetAccountResponse.getTarget_type() == ETargetType.TYPE_POST_URL.getTypeValue()) {
////            guideTarget.setPostUrl(targetAccountResponse.getTarget_url());
////        } else if (targetAccountResponse.getTarget_type() == ETargetType.TYPE_ACCOUNT_HOMEPAGE.getTypeValue()) {
////            List<String> introList = targetAccountResponse.getUser_data().getIntro();
////            StringBuilder builder = new StringBuilder();
////            for (String intro : introList) {
////                builder.append(intro).append(DivideConstant.CEIEC_DIVIDE);
////            }
////            guideTarget.setTargetInfoStr(builder.toString());
////        }
////        guideTargetMapper.updateGuideTarget(guideTarget);
////
////        //if guide target is account homepage, inform calling client about crawled target information
////        if (targetAccountResponse.getTarget_type() == ETargetType.TYPE_ACCOUNT_HOMEPAGE.getTypeValue()) {
////            String socketMsg = JSON.toJSONString(targetAccountResponse.getUser_data());
////            boolean isSuccess = WebSocket.sendMessage(TokenUtils.getUserID(targetAccountResponse.getToken()), EPagePosition.TARGET_HOMEPAGE_INFO, socketMsg);
////            if (isSuccess) {
////                logger.info("send message {} succeed", socketMsg);
////            } else {
////                logger.info("send message {} failed", socketMsg);
////            }
////        }
////    }
//
////    /**
////     * process kafka response about guide account status information
////     *
////     * @param response kafka response
////     */
////    private void processAccountStatusResponse(String response) {
////        AccountStatusResponseBO accountStatusResponse = JSON.parseObject(response, AccountStatusResponseBO.class);
////        String accountId = accountStatusResponse.getUser_id();
////        String systemUserID = accountStatusResponse.getBelong_user();
////        int responseStatus = accountStatusResponse.getAccount_status();
////        //update account
////        if (responseStatus == EKafkaAccountStatus.KAFKA_ACCOUNT_STATUS_NORMAL.getStatusVal()) { //account is usable
////            TargetAccountDataVO accountData = accountStatusResponse.getUser_data();
////            // generate introduce string
////            List<String> introList = accountData.getIntro();
////            StringBuilder builder = new StringBuilder();
////            if (!CollectionUtils.isEmpty(introList)) {
////                for (String intro : introList) {
////                    builder.append(intro).append(DivideConstant.CEIEC_DIVIDE);
////                }
////            }
////            accountData.setIntroStr(builder.toString());
////            // update account information
////            accountLibMapper.updateAccountCrawlerData(accountId, accountData);
////        } else { //account is unusable
////            int accountStatus;
////            if (responseStatus == EKafkaAccountStatus.KAFKA_ACCOUNT_STATUS_LOGIN_FAILED.getStatusVal()) {
////                accountStatus = EAccountStatus.SUB_STATUS_WRONG.getStatusValue();
////            } else if (responseStatus == EKafkaAccountStatus.KAFKA_ACCOUNT_STATUS_UNUSABLE.getStatusVal()) {
////                accountStatus = EAccountStatus.SUB_STATUS_BLOCKED.getStatusValue();
////            } else if (responseStatus == EKafkaAccountStatus.KAFKA_ACCOUNT_STATUS_UNKNOWN.getStatusVal()) {
////                accountStatus = EAccountStatus.SUB_STATUS_VERIFY.getStatusValue();
////            } else {
////                throw new RuntimeException("unhandled account status: " + responseStatus + " from kafka");
////            }
////            //update account status
////            accountLibMapper.updateAccountStatus(accountId, accountStatus, accountStatusResponse.getUnavailable_reason());
////            //insert a new account warn
////            AccountWarnVO accountWarn = new AccountWarnVO();
////            accountWarn.setWarnID(IDGenerator.generate32UUID());
////            accountWarn.setWarnAccount(accountId);
////            accountWarn.setWarnUser(systemUserID);
////            accountWarn.setWarnContent(accountStatusResponse.getUnavailable_reason());
////            accountWarnMapper.addAccountWarn(accountWarn);
////        }
////        //inform request client about account's status change
////        WebSocket.sendMessage(systemUserID, EPagePosition.ACCOUNT_STATUS_CHANGE, "has status changed accounts");
////    }
//}
