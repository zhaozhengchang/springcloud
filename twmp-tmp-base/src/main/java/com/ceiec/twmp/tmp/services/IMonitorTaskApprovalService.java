package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskApprovalEf;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.ChangeParameterApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.DealApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.query.ApprovalListQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.SubmitApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.result.ApprovalListQueryResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;

/**
 * @title: IMonitorTaskApprovalService </br>
 * @createDate: 2019/3/27 11:07 </br>
 * @author: shihsh  </br>
 * @description: 监控任务审批Service </br>
 * @version: V1.0
 **/


public interface IMonitorTaskApprovalService {
    PagedItemsVO<ApprovalListQueryResultVO> listTaskApproval(String token, ApprovalListQueryVO approvalListQueryVO);


    void createTaskApproval(String token, SubmitApprovalVO submitApprovalVO);

    void completeTaskApproval(String token, SubmitApprovalVO submitApprovalVO);

    void changeDeviceApproval(String token, SubmitApprovalVO submitApprovalVO);

    void changeParamterApproval(String token, ChangeParameterApprovalVO parameterApprovalVO);

    void stopTaskkApproval(String token, SubmitApprovalVO submitApprovalVO);

    void approveCreateTask(String token, DealApprovalVO dealApprovalVO);

    void approveCompleteTask(String token, DealApprovalVO dealApprovalVO);

    void approveChangeDevice(String token, DealApprovalVO dealApprovalVO);

    void approveChangeParameter(String token, DealApprovalVO dealApprovalVO);

    void approveStopTask(String token, DealApprovalVO dealApprovalVO);

    void addApproval(SubmitApprovalVO submitApprovalVO, TwmpMonitorTaskApprovalEf approvalEf, TwmpMonitorTaskEf task, RedisUserInfoVO userInfo);

    void dealApproval(TwmpMonitorTaskApprovalEf approvalEf, ApprovalStatus approvalStatus, OperateType operateType, RedisUserInfoVO userInfo);

    void updateTask(TwmpMonitorTaskEf task, TaskStatus taskStatus, RedisUserInfoVO userInfo);

    void sendMessage(Long approvalId, RedisUserInfoVO userInfo, MessageType messageType, MessageSubType messageSubType, String comment, MessageStatus messageStatus);



}
