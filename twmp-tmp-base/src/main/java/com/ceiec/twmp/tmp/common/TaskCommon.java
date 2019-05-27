package com.ceiec.twmp.tmp.common;//package com.ceiec.twmp.tmp.common;
//
//import com.ceiec.twmp.tmp.bo.KafkaMaterialInfoBO;
//import com.ceiec.twmp.tmp.bo.KafkaTaskInfoBO;
//import EKafkaMsgType;
//import com.ceiec.twmp.tmp.enums.ELikeType;
//import ETaskStatus;
//import com.ceiec.twmp.tmp.enums.ETaskType;
//import com.ceiec.twmp.tmp.vo.KafkaAccountInfoVO;
//import com.ceiec.twmp.tmp.vo.KafkaMediaContentVO;
//import com.ceiec.twmp.tmp.vo.KafkaTaskInfoVO;
//import com.ceiec.twmp.tmp.vo.MediaContentVO;
//import org.apache.commons.lang.ArrayUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * CreateDate：2018/9/10 <br/>
// * Author：wenliang <br/>
// * Description: place common codes related to guide tasks
// **/
//public class TaskCommon {
//
//    /**
//     * transfer tasks information from database to proper format kafka need
//     *
//     * @param tasks tasks information from database
//     * @return proper tasks format kafka need
//     */
//    public static List<KafkaTaskInfoBO> formatKafkaTasks(List<KafkaTaskInfoVO> tasks) {
//        List<KafkaTaskInfoBO> kafkaTasks = new ArrayList<>();
//        for (KafkaTaskInfoVO taskInfo : tasks) {
//            KafkaTaskInfoBO kafkaTaskInfo = new KafkaTaskInfoBO();
//            kafkaTaskInfo.setTask_id(taskInfo.getBelongTaskID());
//            kafkaTaskInfo.setSub_task_id(taskInfo.getDetailID());
//            kafkaTaskInfo.setSub_task_status(taskInfo.getSubStatus());
//            kafkaTaskInfo.setPlan_execute_time(taskInfo.getPlanExecTime());
//            kafkaTaskInfo.setUser_info(new KafkaAccountInfoVO(taskInfo.getAccountID(), taskInfo.getAccountUsername(), taskInfo.getAccountHomepage()));
//            kafkaTaskInfo.setWebsite(taskInfo.getWebsite());
//            //transfer task type
//            int guideTaskType = taskInfo.getTaskType();
//            ETaskType taskTypeEnum = ETaskType.getTaskTypeEnum(guideTaskType);
//            switch (taskTypeEnum) {
//                case TYPE_POST:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_POST.getTypeValue());
//                    break;
//                case TYPE_COMMENT:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_COMMENT.getTypeValue());
//                    break;
//                case TYPE_LIKE:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_LIKE.getTypeValue());
//                    break;
//                case TYPE_SHARE:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_SHARE.getTypeValue());
//                    break;
//                case TYPE_REPORT:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_REPORT.getTypeValue());
//                    break;
//                case TYPE_ADD_FRIEND:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_ADDFRIEND_FOLLOW.getTypeValue());
//                    break;
//                case TYPE_FOLLOW:
//                    kafkaTaskInfo.setTask_type(EKafkaMsgType.MSG_ADDFRIEND_FOLLOW.getTypeValue());
//                    break;
//                default:
//                    throw new RuntimeException("unhandled guide task type " + guideTaskType + " please complete your code");
//            }
//            //init guide material
//            if (ArrayUtils.contains(new ETaskType[]{ETaskType.TYPE_POST, ETaskType.TYPE_COMMENT, ETaskType.TYPE_SHARE, ETaskType.SUB_TYPE_SHARE_COMMENT}, taskTypeEnum)) {
//                KafkaMaterialInfoBO message = new KafkaMaterialInfoBO();
//                message.setContent(taskInfo.getWordContent());
//                List<MediaContentVO> mediaContentList = MaterialCommon.parseMedia(taskInfo.getMediaContent());
//                List<KafkaMediaContentVO> media_content_list = new ArrayList<>();
//                for (MediaContentVO mediaContent : mediaContentList) {
//                    media_content_list.save(new KafkaMediaContentVO(mediaContent.getMediaType(), mediaContent.getMediaUrl(), mediaContent.getMediaUrl().replaceAll("^.+/", "")));
//                }
//                message.setMedia_lst(media_content_list);
//                kafkaTaskInfo.setMessage(message);
//            }
//            //init guide target url
//            if (ArrayUtils.contains(new ETaskType[]{ETaskType.TYPE_COMMENT, ETaskType.TYPE_SHARE, ETaskType.TYPE_LIKE, ETaskType.TYPE_REPORT}, taskTypeEnum)) {
//                kafkaTaskInfo.setTarget_url(taskInfo.getPostUrl());
//            } else if (ArrayUtils.contains(new ETaskType[]{ETaskType.TYPE_ADD_FRIEND, ETaskType.TYPE_FOLLOW}, taskTypeEnum)) {
//                kafkaTaskInfo.setTarget_url(taskInfo.getTargetHomepage());
//            } else if (taskTypeEnum == ETaskType.TYPE_POST) {
//                kafkaTaskInfo.setTarget_url(taskInfo.getStatusAddInfo());
//            } else if (taskInfo.getSubStatus() == ETaskStatus.SUB_STATUS_WITHDRAWING.getStatusValue()) {
//                kafkaTaskInfo.setTarget_url(taskInfo.getStatusAddInfo().split(";")[1]);
//            }
//            //init like type
//            if (taskTypeEnum == ETaskType.TYPE_LIKE) {
//                kafkaTaskInfo.setLike_type(ELikeType.matchCWType(taskInfo.getSubType()));
//            }
//            //init report type
//            if (taskTypeEnum == ETaskType.TYPE_REPORT) {
//                String reportTypeStr = taskInfo.getSubType() + "";
//                kafkaTaskInfo.setReport_type(Integer.parseInt(reportTypeStr.substring(1, reportTypeStr.length())));
//            }
//            kafkaTaskInfo.setCancel(taskInfo.getSubStatus() == ETaskStatus.SUB_STATUS_WITHDRAWING.getStatusValue());
//            kafkaTasks.save(kafkaTaskInfo);
//        }
//        return kafkaTasks;
//    }
//}
