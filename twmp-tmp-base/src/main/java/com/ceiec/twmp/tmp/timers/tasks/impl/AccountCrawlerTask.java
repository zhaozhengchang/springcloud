package com.ceiec.twmp.tmp.timers.tasks.impl;//package com.ceiec.twmp.tmp.timers.tasks.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ceiec.twmp.tmp.SystemInit;
//import EKafkaMsgType;
//import ETimerTasks;
////import EKafkaTopic;
////import KafkaFactory;
//import com.ceiec.twmp.tmp.mapper.IAccountLibMapper;
//import ABaseTask;
//import SpringUtil;
//import com.ceiec.twmp.tmp.vo.AccountKafkaInfoVO;
//import com.ceiec.twmp.tmp.vo.UsableIPVO;
//import DESUtils;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * CreateDate：2018/10/11 <br/>
// * Author：wenliang <br/>
// * Description:
// **/
//public class AccountCrawlerTask extends ABaseTask {
//
//    /** account mapper */
//    private IAccountLibMapper accountLibMapper = SpringUtil.getBean("IAccountLibMapper", IAccountLibMapper.class);
//
//    /**
//     * construction function
//     *
//     * @param timerTask timer name
//     */
//    public AccountCrawlerTask(ETimerTasks timerTask) {
//        super(timerTask.getTaskName());
//    }
//
//    @Override
//    protected void executeTask() {
//
////        List<AccountKafkaInfoVO> guideAccounts = accountLibMapper.getAccountKafkaInfo();
////        for (AccountKafkaInfoVO guideAccount : guideAccounts) {
////            String message = generateKafkaMsg(guideAccount);
////            KafkaFactory.getKafkaProducer().sendMsg(EKafkaTopic.TOPIC_FROM_BACKGROUND, message);
////            logExecution(getTaskName(), "send kafka message:" + message);
////        }
//    }
//
//    /**
//     * generate kafka message for check account status
//     *
//     * @param accountKafkaInfoVO account kafka information
//     * @return kafka info json string
//     */
//    private String generateKafkaMsg(AccountKafkaInfoVO accountKafkaInfoVO) {
//        JSONObject message = new JSONObject();
//        JSONObject userInfo = new JSONObject();
//        userInfo.put("user_id", accountKafkaInfoVO.getUser_id());
//        userInfo.put("username", accountKafkaInfoVO.getUsername());
//        userInfo.put("password", DESUtils.decrypt(accountKafkaInfoVO.getPassword()));
//        message.put("user_info", userInfo);
//        message.put("belong_user", accountKafkaInfoVO.getBelong_user());
//        message.put("task_type", EKafkaMsgType.MSG_GUIDE_ACCOUNT_STATUS.getTypeValue());
//        message.put("website", accountKafkaInfoVO.getWebsite());
//        UsableIPVO ip = SystemInit.getMostSuitIP(false);
//        message.put("proxy_ip", ip.getIp());
//        ip.setLastUseTime(new Date());
//        SystemInit.resortUsableIPs();
//        message.put("send_time", new Date());
//        return message.toJSONString();
//    }
//}