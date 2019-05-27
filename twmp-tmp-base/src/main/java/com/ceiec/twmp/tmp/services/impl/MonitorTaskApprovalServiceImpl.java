package com.ceiec.twmp.tmp.services.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.*;
import com.ceiec.twmp.tmp.mapper.*;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IMonitorTaskApprovalService;
import com.ceiec.twmp.tmp.services.IMonitorTaskService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorker;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.fence.FenceShapeVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.ChangeParameterApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.DealApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.SubmitApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.query.ApprovalListQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.result.ApprovalListQueryResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.ceiec.twmp.tmp.websocket.message.MessageSender;
import com.github.pagehelper.PageHelper;
import org.apache.zookeeper.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title: MonitorTaskApprovalServiceImpl </br>
 * @createDate: 2019/3/27 11:10 </br>
 * @author: shihsh  </br>
 * @description: 监控任务审批service </br>
 * @version: V1.0
 **/


@Service
@Transactional(rollbackFor = Exception.class)
public class MonitorTaskApprovalServiceImpl implements IMonitorTaskApprovalService {

    private static final Long AUTHORITYID = 67L;

    @Autowired
    private ILogService logService;

    @Autowired
    private TwmpMonitorTaskApprovalEfMapper twmpMonitorTaskApprovalEfMapper;

    @Autowired
    private TwmpBsPersonEfMapper twmpBsPersonEfMapper;

    @Autowired
    private TwmpMonitorTaskEfMapper twmpMonitorTaskEfMapper;

    @Autowired
    private TwmpSysMessageMapper twmpSysMessageMapper;

    @Autowired
    private TwmpMonitorTaskChangeEfMapper twmpMonitorTaskChangeEfMapper;

    @Autowired
    private IMonitorTaskService monitorTaskService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @Autowired
    private TwmpMonitorTaskTeamEfMapper twmpMonitorTaskTeamEfMapper;

    @Autowired
    private TwmpMonitorTaskFenceEfMapper twmpMonitorTaskFenceEfMapper;

    @Autowired
    private TwmpOpeChangeMapper twmpOpeChangeMapper;

    @Autowired
    private TwmpOpeInstallMapper twmpOpeInstallMapper;

    @Autowired
    private TwmpOpeDismantleMapper twmpOpeDismantleMapper;

    @Autowired
    private TwmpSysRoleAuthorityMapper twmpSysRoleAuthorityMapper;

    /**
    *
    * @description: 监控任务审批查询
    * @param: token
    * @param: approvalListQueryVO
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.monitor.approval.result.ApprovalListQueryResultVO>
    * @author: shihsh
    * @date: 2019/3/27
    */
    @Override
    public PagedItemsVO<ApprovalListQueryResultVO> listTaskApproval(String token, ApprovalListQueryVO approvalListQueryVO) {
        List<Long> departmentIds = DepartmentStrToList.getDepartmentIdList(token);
        approvalListQueryVO.setDepartmentIds(departmentIds);
        approvalListQueryVO.setStartTimeDate(DateFormatUtils.stringToDateTime(approvalListQueryVO.getStartTime()));
        approvalListQueryVO.setEndTimeDate(DateFormatUtils.stringToDateTime(approvalListQueryVO.getEndTime()));
        Long total = twmpMonitorTaskApprovalEfMapper.countMonitorTaskApproval(approvalListQueryVO);
        PageHelper.startPage(approvalListQueryVO.getPageNo(), approvalListQueryVO.getPageSize(), "submit_time desc");
        List<ApprovalListQueryResultVO> approvalList = twmpMonitorTaskApprovalEfMapper.listMonitorTaskApproval(approvalListQueryVO);
        for (ApprovalListQueryResultVO approval : approvalList) {
            approval.setSubmitTime(DateFormatUtils.dateTimeToString(approval.getSubmitTimeDate()));
            approval.setApprovalTime(DateFormatUtils.dateTimeToString(approval.getApprovalTimeDate()));
        }
        return new PagedItemsVO<>(total, approvalList);
    }

    /**
    *
    * @description:  新建监控任务审批
    * @param: token
    * @param: submitApprovalVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/2
    */
    @Override
    public void createTaskApproval(String token, SubmitApprovalVO submitApprovalVO) {
        //新建监控任务审批
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(submitApprovalVO.getTaskId());
        // 判断是否已经有用户提交了审批
        if (task.getTaskStatus() != TaskStatus.initial.value) {
            // 若已提交审批，则监控任务状态就不再为 “初始化”状态
            return;
        }
        TwmpMonitorTaskApprovalEf approvalEf = new TwmpMonitorTaskApprovalEf();
        addApproval(submitApprovalVO, approvalEf, task, userInfo);
        // 设置监控任务状态为审批中,更新监控任务状态
        updateTask(task, TaskStatus.initial_approval, userInfo);

        // 审批任务保存后 具备审批权限的用户，直接通过审批，不发申请消息,普通用户需要发送审批消息，等待有审批权限的用户审批
        acceptOrAuthority(userInfo, approvalEf);
    }

    @Override
    public void completeTaskApproval(String token, SubmitApprovalVO submitApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(submitApprovalVO.getTaskId());
        // 判断监控任务状态
        if (task.getTaskStatus() != TaskStatus.completed.value) {
            // 申请结束监控任务得前一状态应该为 监控完成状态
            return;
        }
        // 判断是否已经有用户提交了结束任务审批
        TwmpMonitorTaskApprovalEf submittedApproval = new TwmpMonitorTaskApprovalEf();
        submittedApproval.setApprovalStatus(ApprovalStatus.submitted.value);
        submittedApproval.setTaskId(submitApprovalVO.getTaskId());
        Long count = twmpMonitorTaskApprovalEfMapper.countSubmittedApproval(submittedApproval);
        if (count > 0) {
            // 当前有待审批的任务，不能提交审批
            return;
        }
        // 状态符合，申请监控完成审批任务
        TwmpMonitorTaskApprovalEf approvalEf = new TwmpMonitorTaskApprovalEf();
        addApproval(submitApprovalVO, approvalEf, task, userInfo);
        // 具备审批权限的用户，直接通过审批，不发申请消息,普通用户需要发送审批消息，等待有审批权限的用户审批
        acceptOrAuthority(userInfo, approvalEf);

    }

    @Override
    public void changeDeviceApproval(String token, SubmitApprovalVO submitApprovalVO) {
        // 申请设备变更审批
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(submitApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approvalEf = new TwmpMonitorTaskApprovalEf();

        // 检查监控任务状态，设备变更前一个状态应该为: 安装完成或者监控中
        if (task.getTaskStatus() == TaskStatus.monitoring.value || task.getTaskStatus() == TaskStatus.initial_installed.value) {
            // 检查审批任务，只有当前没有待审批（即已提交）状态的审批任务，才能新建更换设备审批
            TwmpMonitorTaskApprovalEf queryApproval = new TwmpMonitorTaskApprovalEf();
            queryApproval.setTaskId(submitApprovalVO.getTaskId());
            queryApproval.setApprovalStatus(ApprovalStatus.submitted.value);
            Long count = twmpMonitorTaskApprovalEfMapper.countSubmittedApproval(queryApproval);
            if (count > 0) {
                return;
            }
            // 检查是否有已经通过的更换设备审批，并且设备处于更换中。若有则不能提交审批任务
            TwmpOpeChange twmpOpeChange = new TwmpOpeChange();
            twmpOpeChange.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
            twmpOpeChange.setTaskId(task.getTaskId());
            if (twmpOpeChangeMapper.selectCount(twmpOpeChange) > 0) {
                return;
            }
            // 提交设备变更申请
            addApproval(submitApprovalVO, approvalEf, task, userInfo);
            // 具备审批权限的用户，直接通过审批，不发申请消息,普通用户需要发送审批消息，等待有审批权限的用户审批
            acceptOrAuthority(userInfo, approvalEf);
        }
    }

    /**
    *
    * @description: 参数变更，不会改变监控任务的状态
    * @param: token
    * @param: changeParameterApprovalVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/3
    */
    @Override
    public void changeParamterApproval(String token, ChangeParameterApprovalVO changeParameterApprovalVO) {
        // 两步走 1.判断是否满足参数变更条件 2.满足则存入变更后的参数，直接存入twmp_monitor_task_change_ef
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(changeParameterApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approvalEf = new TwmpMonitorTaskApprovalEf();
        if (task.getTaskStatus() == TaskStatus.initial_wait_to_install.value
                || task.getTaskStatus() == TaskStatus.initial_installed.value
                || task.getTaskStatus() == TaskStatus.monitoring.value
                || task.getTaskStatus() == TaskStatus.completed.value) {
            // 检查审批任务，只有当前没有参数变更申请（已提交）、申请结束（已提交、已通过）和已结束
            TwmpMonitorTaskApprovalEf queryApproval = new TwmpMonitorTaskApprovalEf();
            queryApproval.setTaskId(changeParameterApprovalVO.getTaskId());
            queryApproval.setApprovalStatus(ApprovalStatus.submitted.value);
            //有待审批的变更(已提交)
            Long count = twmpMonitorTaskApprovalEfMapper.countSubmittedApproval(queryApproval);
            if (count > 0) {
                return;
            }
            // 申请了结束监控任务（已通过）
            queryApproval.setApprovalType(ApprovalType.endMonitorTaskApproval.value);
            queryApproval.setApprovalStatus(ApprovalStatus.approval.value);
            count = twmpMonitorTaskApprovalEfMapper.countSubmittedApproval(queryApproval);
            if (count > 0) {
                return;
            }
            // 申请了终止监控任务
            queryApproval.setApprovalStatus(ApprovalStatus.approval.value);
            queryApproval.setApprovalType(ApprovalType.stopTaskApproval.value);
            count = twmpMonitorTaskApprovalEfMapper.countSubmittedApproval(queryApproval);
            if (count > 0) {
                return;
            }
            // 可以申请参数变更了
            // 1. 旧的参数存入change表 2.存入新的参数 3.发起参数变更审批
            TwmpMonitorTaskChangeEf oldTask = new TwmpMonitorTaskChangeEf();
            ObjectUtils.copy(task, oldTask);
            oldTask.setTaskChangeId(task.getTaskId());
            TwmpMonitorTaskChangeEf taskChangeEf = twmpMonitorTaskChangeEfMapper.selectByPrimaryKey(task.getTaskId());
            // 在将task存入task_change表的同时，需要更新监控任务相关的表：监护人员、围栏，将变更后的taskId存入其中
            if (taskChangeEf == null) {
                twmpMonitorTaskChangeEfMapper.insertSelective(oldTask);
            } else {
                twmpMonitorTaskChangeEfMapper.updateByPrimaryKeySelective(oldTask);
            }
            // 联系人和围栏不需要删除，便于恢复。但是需要存入变更的 changeTaskId
            twmpMonitorTaskTeamEfMapper.updateChangeTaskId(task.getTaskId());
            twmpMonitorTaskFenceEfMapper.updateChangeTaskId(task.getTaskId());
            // 原表中数据标记为已删除
            task.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
            twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(task);

            // 存入新的监控任务
            changeParameterApprovalVO.setTaskId(null);
            monitorTaskService.addOrEditMonitorTask(token, changeParameterApprovalVO);

            // 发起参数变更审批
            SubmitApprovalVO submitApprovalVO = new SubmitApprovalVO();
            submitApprovalVO.setApprovalType(changeParameterApprovalVO.getApprovalType());
            submitApprovalVO.setChangeReason(changeParameterApprovalVO.getChangeReason());
            // 新的taskId，再调用addOrEditMonitorTask时，生成了新的taskId
            submitApprovalVO.setTaskId(changeParameterApprovalVO.getTaskId());
            //  旧的taskId
            approvalEf.setTaskChangeId(task.getTaskId());
            approvalEf.setTaskChangeCode(oldTask.getTaskCode());
            addApproval(submitApprovalVO, approvalEf, task, userInfo);
            // 具备审批权限的用户，直接通过审批，不发申请消息,普通用户需要发送审批消息，等待有审批权限的用户审批
            acceptOrAuthority(userInfo, approvalEf);
        }
    }

    @Override
    public void stopTaskkApproval(String token, SubmitApprovalVO submitApprovalVO) {
        // 终止监控任务审批
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(submitApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approvalEf = new TwmpMonitorTaskApprovalEf();

        //  需要判断终止前的状态是否符合终止条件(设备待安装、安装完成、监控中、监控完成),等级最高，不需要关心是任务是否在审批中
        if (task.getTaskStatus() == TaskStatus.initial_wait_to_install.value || task.getTaskStatus() == TaskStatus.initial_installed.value
                || task.getTaskStatus() == TaskStatus.monitoring.value || task.getTaskStatus() == TaskStatus.completed.value) {
            addApproval(submitApprovalVO, approvalEf, task, userInfo);
            // 具备审批权限的用户，直接通过审批，不发申请消息,普通用户需要发送审批消息，等待有审批权限的用户审批
            acceptOrAuthority(userInfo, approvalEf);
        }
    }

    /**
    *
    * @description:  对新建监控任务进行审批
    * @param: token
    * @param: dealApprovalVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/2
    */
    @Override
    public void approveCreateTask(String token, DealApprovalVO dealApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if (!approvalAuthority(userInfo)) {
            // 不具备审批权限
            throw new BusinessException(localeMessageSourceService.getMessageLocal("approva.authority.false", userInfo.getLanguage()));
        }
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(dealApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approval = twmpMonitorTaskApprovalEfMapper.selectByPrimaryKey(dealApprovalVO.getApprovalId());
        if (approval == null) {
            // 不存在的审批任务
            return;
        }
        if (approval.getApprovalType().equals(dealApprovalVO.getApprovalType()) && approval.getApprovalStatus() != ApprovalStatus.submitted.value) {
            // 判断该任务是否已经被审批过了，若有则不必再进行审批操作
            return;
        }
        MessageSubType messageSubType = null;
        // 审批通过需要给运维发安装消息
        MessageSubType installMesageSubType = null;
        ApprovalStatus approvalStatus;
        TaskStatus changeTaskStatus;
        // 审批操作类型： 撤回、驳回、通过
        Byte frontApprovalStatus = dealApprovalVO.getApprovalStatus();
        OperateType operateType;
        Byte taskStatus = task.getTaskStatus();
        if (taskStatus == TaskStatus.initial_approval.value) {
            // 当前状态： taskStatus-审批中 approvalStatus-审批已提交; approvalType-创建监控任务审批。
            if (frontApprovalStatus == FrontApprovalStatus.accept.value) {
                // 审批通过
                operateType = OperateType.approval;
                approvalStatus = ApprovalStatus.approval;
                changeTaskStatus = TaskStatus.initial_wait_to_install;
                messageSubType = MessageSubType.approvalPass;
                installMesageSubType = MessageSubType.install;
                addOpeInstall(task);
            } else if (frontApprovalStatus == FrontApprovalStatus.refuse.value) {
                // 驳回即审批不通过
                operateType = OperateType.reject;
                approvalStatus = ApprovalStatus.notApproval;
                // 返回到初始化状态
                changeTaskStatus = TaskStatus.initial;
                messageSubType = MessageSubType.approvalNotPass;
            } else {
                // 撤回
                operateType = OperateType.retract;
                approvalStatus = ApprovalStatus.notSubmitted;
                changeTaskStatus = TaskStatus.initial;
                // 撤回不发通知
            }
            //更新审批任务状态 和 监控任务状态
            dealApproval(approval, approvalStatus, operateType, userInfo);
            updateTask(task, changeTaskStatus, userInfo);
            // 发送消息
            if (messageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, messageSubType, "", MessageStatus.unread);
            }
            if (installMesageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, installMesageSubType, "", MessageStatus.unread);
            }
        }
    }

    @Override
    public void approveCompleteTask(String token, DealApprovalVO dealApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if (!approvalAuthority(userInfo)) {
            // 不具备审批权限
            throw new BusinessException(localeMessageSourceService.getMessageLocal("approva.authority.false", userInfo.getLanguage()));
        }
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(dealApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approval = twmpMonitorTaskApprovalEfMapper.selectByPrimaryKey(dealApprovalVO.getApprovalId());
        if (approval == null) {
            // 不存在的审批任务
            return;
        }
        if (approval.getApprovalType().equals(dealApprovalVO.getApprovalType()) && approval.getApprovalStatus() != ApprovalStatus.submitted.value) {
            // 判断该任务是否已经被审批过了，若有则不必再进行审批操作
            return;
        }
        MessageSubType messageSubType = null;
        // 审批通过需要给运维发拆机消息
        MessageSubType dismantleMesageSubType = null;
        ApprovalStatus approvalStatus;
        TaskStatus changeTaskStatus = null;

        // 审批操作类型： 撤回、驳回、通过
        Byte frontApprovalStatus = dealApprovalVO.getApprovalStatus();
        OperateType operateType;
        if (TaskStatus.completed.value == task.getTaskStatus()) {
            // 当前状态： taskStatus-已完成 approvalStatus-审批已提交; approvalType-完成监控任务。
            if (frontApprovalStatus == FrontApprovalStatus.accept.value) {
                // 审批通过,发送审批通过消息和拆机消息
                operateType = OperateType.approval;
                approvalStatus = ApprovalStatus.approval;
                changeTaskStatus = TaskStatus.completed_wait_to_dismantle;
                messageSubType = MessageSubType.approvalPass;
                dismantleMesageSubType = MessageSubType.dismantle;
                addDismantleRecord(task);
            } else if (frontApprovalStatus == FrontApprovalStatus.refuse.value) {
                // 驳回即审批不通过
                operateType = OperateType.reject;
                approvalStatus = ApprovalStatus.notApproval;
                messageSubType = MessageSubType.approvalNotPass;
            } else {
                // 撤回不发消息
                operateType =OperateType.retract;
                approvalStatus = ApprovalStatus.notSubmitted;
            }
            //更新审批任务状态 和 监控任务状态
            dealApproval(approval, approvalStatus, operateType, userInfo);
            updateTask(task, changeTaskStatus, userInfo);
            // 发送消息
            if (messageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, messageSubType, "", MessageStatus.unread);
            }
            if (dismantleMesageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, dismantleMesageSubType, "", MessageStatus.unread);
            }
        }
    }

    @Override
    public void approveChangeDevice(String token, DealApprovalVO dealApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if (!approvalAuthority(userInfo)) {
            // 不具备审批权限
            throw new BusinessException(localeMessageSourceService.getMessageLocal("approva.authority.false", userInfo.getLanguage()));
        }
        TwmpMonitorTaskApprovalEf approval = twmpMonitorTaskApprovalEfMapper.selectByPrimaryKey(dealApprovalVO.getApprovalId());
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(dealApprovalVO.getTaskId());
        if (approval == null) {
            // 不存在的审批任务
            return;
        }
        if (approval.getApprovalType().equals(dealApprovalVO.getApprovalType()) && approval.getApprovalStatus() != ApprovalStatus.submitted.value) {
            // 判断该任务是否已经被审批过了，若有则不必再进行审批操作
            return;
        }
        MessageSubType messageSubType = null;
        // 审批通过需要给运维发拆机消息
        MessageSubType changeDeviceMesageSubType = null;
        ApprovalStatus approvalStatus;

        // 审批操作类型： 撤回、驳回、通过
        Byte frontApprovalStatus = dealApprovalVO.getApprovalStatus();
        OperateType operateType;
        if (TaskStatus.initial_installed.value == task.getTaskStatus() || TaskStatus.monitoring.value == task.getTaskStatus()) {
            // 当前状态： taskStatus-安装完成或者监控中 approvalStatus-审批已提交; approvalType-更换设备。
            if (frontApprovalStatus == FrontApprovalStatus.accept.value) {
                // 审批通过, 监控任务的状态保持不变。发送审批通过消息和更换设备消息
                operateType = OperateType.approval;
                approvalStatus = ApprovalStatus.approval;
                messageSubType = MessageSubType.approvalPass;
                changeDeviceMesageSubType = MessageSubType.changeDevice;
                // 新建更换设备的任务
                addOpeChangeRecord(task);
            } else if (frontApprovalStatus == FrontApprovalStatus.refuse.value) {
                // 驳回即审批不通过
                operateType = OperateType.reject;
                approvalStatus = ApprovalStatus.notApproval;
                messageSubType = MessageSubType.approvalNotPass;
            } else {
                // 撤回不发消息
                operateType = OperateType.retract;
                approvalStatus = ApprovalStatus.notSubmitted;
            }
            //更新审批任务状态， 监控任务状态保持不变
            dealApproval(approval, approvalStatus, operateType, userInfo);
            // 发送消息
            if (messageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, messageSubType, "", MessageStatus.unread);
            }
            if (changeDeviceMesageSubType != null) {
                // 变更设备的请求存入twmp_ope_change表
                addOpeChangeRecord(task);
                // 发送更换设备的消息,
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, changeDeviceMesageSubType, "", MessageStatus.unread);
            }
        }
    }

    /**
    *
    * @description:  审批被驳回或撤回后，需要将变更的参数还原
    * @param: token
    * @param: dealApprovalVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/2
    */
    @Override
    public void approveChangeParameter(String token, DealApprovalVO dealApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if (!approvalAuthority(userInfo)) {
            // 不具备审批权限
            throw new BusinessException(localeMessageSourceService.getMessageLocal("approva.authority.false", userInfo.getLanguage()));
        }
        TwmpMonitorTaskApprovalEf approval = twmpMonitorTaskApprovalEfMapper.selectByPrimaryKey(dealApprovalVO.getApprovalId());
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(dealApprovalVO.getTaskId());
        if (approval == null) {
            // 不存在的审批任务
            return;
        }
        if (approval.getApprovalType().equals(dealApprovalVO.getApprovalType()) && approval.getApprovalStatus() != ApprovalStatus.submitted.value) {
            // 判断该任务是否已经被审批过了（除了正常情况下其他用户审批，还有可能是监控任务的状态法生了变化，审批自动被设置为不通过）
            return;
        }
        MessageSubType messageSubType = null;
        ApprovalStatus approvalStatus;

        // 审批操作类型： 撤回、驳回、通过。 参数变更不会改变监控任务的状态
        Byte frontApprovalStatus = dealApprovalVO.getApprovalStatus();
        OperateType operateType;
        // 判断条件
        if (TaskStatus.initial_wait_to_install.value == task.getTaskStatus()
                || TaskStatus.initial_installed.value == task.getTaskStatus()
                || TaskStatus.monitoring.value == task.getTaskStatus()
                || TaskStatus.completed.value == task.getTaskStatus()) {
            // 当前状态： taskStatus-待安裝，安装完成，监控中，已完成, approvalStatus-审批已提交; approvalType-参数变更。
            if (frontApprovalStatus == FrontApprovalStatus.accept.value) {
                operateType = OperateType.approval;
                // 审批通过,发送审批通过消息和更换设备消息，监控任务信息已经在提交申请时更新了，此处不需要处理
                approvalStatus = ApprovalStatus.approval;
                messageSubType = MessageSubType.approvalPass;
            } else if (frontApprovalStatus == FrontApprovalStatus.refuse.value) {
                // 驳回即审批不通过。需要将监控任务信息恢复成原来的（在twmp_monitor_task_change_ef表中）
                operateType = OperateType.reject;
                approvalStatus = ApprovalStatus.notApproval;
                messageSubType = MessageSubType.approvalNotPass;
                monitorTaskService.restoreMonitorTask(token, task.getPersonId(), approval.getTaskChangeId(), approval.getTaskId());

            } else {
                // 撤回不发消息。需要将监控任务信息恢复成原来的（在twmp_monitor_task_change_ef表中）
                operateType = OperateType.retract;
                approvalStatus = ApprovalStatus.notSubmitted;
                monitorTaskService.restoreMonitorTask(token, task.getPersonId(), approval.getTaskChangeId(), approval.getTaskId());
            }
            //更新审批任务状态， 监控任务状态保持不变
            dealApproval(approval, approvalStatus, operateType, userInfo);
            // 发送消息
            if (messageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, messageSubType, "", MessageStatus.unread);
            }
        }


        updateRedisDeviceByApproveChangeParameter(task.getTaskId(), task.getDeviceNumber());

    }


    /*************************************************************************************************************************************
     ** @Description if approve change parameter , should update device fence shape info
     ** @param: taskId
     ** @param: deviceNumber
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/12 14:53
     **
     ************************************************************************************************************************************/
    private void updateRedisDeviceByApproveChangeParameter(Long taskId, String deviceNumber){
        List<FenceShapeVO> fenceShapeVOS = twmpMonitorTaskFenceEfMapper.selectEnableFenceSpaceByTaskId(taskId);
        RedisDeviceInfoVO redisDeviceInfoVO = DeviceInfoRedis.getDevice(deviceNumber);
        redisDeviceInfoVO.setFenceShapeList(fenceShapeVOS);
        DeviceInfoRedis.saveDevice(redisDeviceInfoVO);


    }

    /**
    *
    * @description: 审批终止任务
    * @param: token
    * @param: dealApprovalVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/18
    */
    @Override
    public void approveStopTask(String token, DealApprovalVO dealApprovalVO) {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if (!approvalAuthority(userInfo)) {
            // 不具备审批权限
            throw new BusinessException(localeMessageSourceService.getMessageLocal("approva.authority.false", userInfo.getLanguage()));
        }
        TwmpMonitorTaskEf task = twmpMonitorTaskEfMapper.selectByPrimaryKey(dealApprovalVO.getTaskId());
        TwmpMonitorTaskApprovalEf approval = twmpMonitorTaskApprovalEfMapper.selectByPrimaryKey(dealApprovalVO.getApprovalId());
        if (approval == null) {
            // 不存在的审批任务
            return;
        }
        if (approval.getApprovalType().equals(dealApprovalVO.getApprovalType()) && approval.getApprovalStatus() != ApprovalStatus.submitted.value) {
            // 判断该任务是否已经被审批过了，若有则不必再进行审批操作
            return;
        }
        MessageSubType messageSubType = null;
        // 审批通过需要给运维发拆机消息
        MessageSubType dismantleMesageSubType = null;
        ApprovalStatus approvalStatus;
        TaskStatus changeTaskStatus = null;

        // 审批操作类型： 撤回、驳回、通过
        Byte frontApprovalStatus = dealApprovalVO.getApprovalStatus();
        OperateType operateType;
        if (TaskStatus.initial.value != task.getTaskStatus()
                && TaskStatus.completed_wait_to_dismantle.value != task.getTaskStatus()
                && TaskStatus.initial_approval.value != task.getTaskStatus()
                && TaskStatus.over.value != task.getTaskStatus()) {
            if (frontApprovalStatus == FrontApprovalStatus.accept.value) {
                // 审批通过,发送审批通过消息和拆机消息
                operateType = OperateType.approval;
                approvalStatus = ApprovalStatus.approval;
                if (TaskStatus.initial_wait_to_install.value == task.getTaskStatus()) {
                    // 待安装状态， 监控任务直接进入终止状态
                    changeTaskStatus = TaskStatus.over;
                } else if (dealApprovalVO.getEscape() == 1) {
                    changeTaskStatus = TaskStatus.over;
                    task.setEscape((byte)1);
                } else {
                    // 除了未安装状态，和逃跑状态，其他状态需要进行拆机
                    dismantleMesageSubType = MessageSubType.dismantle;
                    changeTaskStatus = TaskStatus.completed_wait_to_dismantle;
                    addDismantleRecord(task);
                }
                // 驳回其他待审批的任务
                twmpMonitorTaskApprovalEfMapper.refuseApproval(task.getTaskId());
                messageSubType = MessageSubType.approvalPass;
                // task终止原因
                task.setTaskReason(approval.getChangeReason());
            } else if (frontApprovalStatus == FrontApprovalStatus.refuse.value) {
                // 驳回即审批不通过
                operateType = OperateType.reject;
                approvalStatus = ApprovalStatus.notApproval;
                messageSubType = MessageSubType.approvalNotPass;
            } else {
                // 撤回不发消息
                operateType = OperateType.retract;
                approvalStatus = ApprovalStatus.notSubmitted;
            }
            //更新审批任务状态 和 监控任务状态
            dealApproval(approval, approvalStatus, operateType,  userInfo);
            updateTask(task, changeTaskStatus, userInfo);
            // 发送消息
            if (messageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, messageSubType, "", MessageStatus.unread);
            }
            if (dismantleMesageSubType != null) {
                sendMessage(approval.getApprovalId(), userInfo, MessageType.notice, dismantleMesageSubType, "", MessageStatus.unread);
            }
        }
    }


    /**
    *
    * @description:  提交审批任务
    * @param: submitApprovalVO
    * @param: approvalEf
    * @param: userInfo
    * @return: void
    * @author: shihsh
    * @date: 2019/4/2
    */
    @Override
    public void addApproval(SubmitApprovalVO submitApprovalVO, TwmpMonitorTaskApprovalEf approvalEf, TwmpMonitorTaskEf task, RedisUserInfoVO userInfo) {
        if (submitApprovalVO == null || approvalEf == null || userInfo ==null) {
            return;
        }
        approvalEf.setApprovalId(SnowflakeIdWorkerUtil.generateLongId());
        approvalEf.setTaskId(submitApprovalVO.getTaskId());
        approvalEf.setTaskCode(task.getTaskCode());
        approvalEf.setApprovalType(submitApprovalVO.getApprovalType());
        approvalEf.setApprovalStatus(ApprovalStatus.submitted.value);
        approvalEf.setChangeReason(submitApprovalVO.getChangeReason());
        approvalEf.setSubmitId(userInfo.getUserId());
        approvalEf.setSubmitter(userInfo.getUserName());
        approvalEf.setSubmitTime(new Date());
        approvalEf.setDepartmentId(userInfo.getDepartmentId());
        approvalEf.setDepartmentName(userInfo.getDepartmentName());
        twmpMonitorTaskApprovalEfMapper.insertSelective(approvalEf);
        // 记录日志
        logService.saveOperateLog(userInfo.getToken(), OperateType.insert.value, OperateObjectType.approval.value, approvalEf.getTaskChangeCode(), null);
    }

    /**
    *
    * @description:  更新审批状态
    * @param: approvalEf
    * @param: approvalStatus
    * @param: userInfo
    * @return: void
    * @author: shihsh
    * @date: 2019/4/2
    */
    @Override
    public void dealApproval(TwmpMonitorTaskApprovalEf approvalEf, ApprovalStatus approvalStatus, OperateType operateType, RedisUserInfoVO userInfo) {
        approvalEf.setApprovalStatus(approvalStatus.value);
        approvalEf.setApprovalUserId(userInfo.getUserId());
        approvalEf.setApprovalUser(userInfo.getUserName());
        approvalEf.setApprovalTime(new Date());
        twmpMonitorTaskApprovalEfMapper.updateByPrimaryKeySelective(approvalEf);
        // 处理审批，记录日志
        String comment = "{\napprovalId:" + approvalEf.getApprovalId();
        if (!StringUtils.isNullOrEmpty(approvalEf.getChangeReason())) {
            comment = comment + ",\nchangeReason:" + approvalEf.getChangeReason();
        }
        if (!StringUtils.isNullOrEmpty(approvalEf.getApprovalNote())) {
            comment += ",\napprovalNote:" + approvalEf.getApprovalNote();
        }
        comment += "\n}";
        logService.saveOperateLog(userInfo.getToken(), operateType.value, OperateObjectType.approval.value, approvalEf.getTaskCode(), comment);
    }

    @Override
    public void updateTask(TwmpMonitorTaskEf task, TaskStatus taskStatus, RedisUserInfoVO userInfo) {
        if (taskStatus == null) {
            return;
        }
        task.setTaskStatus(taskStatus.value);
        task.setUpdaterId(userInfo.getUserId());
        task.setUpdater(userInfo.getUserName());
        task.setUpdateTime(new Date());
        twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(task);
        logService.saveOperateLog(userInfo.getToken(), OperateType.update.value, OperateObjectType.task.value, task.getTaskCode(), null);
    }

    @Override
    public void sendMessage(Long approvalId, RedisUserInfoVO userInfo, MessageType messageType, MessageSubType messageSubType, String comment, MessageStatus messageStatus) {
        TwmpSysMessage message = new TwmpSysMessage();
        message.setDepartmentId(userInfo.getDepartmentId());
        // 业务Id是否为监控审批任务Id
        message.setBusinessId(approvalId);
        // 消息内容
        message.setMessageComment(comment);
        message.setMessageType(messageType.value);
        message.setMessageSubType(messageSubType.value);
        message.setMessageStatus(messageStatus.value);
        message.setMessageTime(new Date());
        message.setMessageId(SnowflakeIdWorkerUtil.generateLongId());
        twmpSysMessageMapper.insertSelective(message);
        MessageSender messageSender = new MessageSender();
        messageSender.sendDepartmentMessage(message.getDepartmentId(), message);

    }


    /**
    *
    * @description:  新建任务审批通过后，向安装表中增加一条待安装记录
    * @param: task
    * @param: userInfo
    * @return: void
    * @author: shihsh
    * @date: 2019/4/10
    */
    private void addOpeInstall(TwmpMonitorTaskEf task) {
        // 更新安装信息表，插入待安装任务
        TwmpOpeInstall twmpOpeInstall = new TwmpOpeInstall();
        twmpOpeInstall.setInstallId(SnowflakeIdWorkerUtil.generateLongId());
        twmpOpeInstall.setPersonId(task.getPersonId());
        twmpOpeInstall.setTaskCode(task.getTaskCode());
        twmpOpeInstall.setTaskId(task.getTaskId());
        twmpOpeInstall.setDepartmentId(task.getDepartmentId());
        twmpOpeInstall.setDepartmentName(task.getDepartmentName());
        twmpOpeInstall.setCreateTime(new Date());
        twmpOpeInstall.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        TwmpBsPersonEf personEf = twmpBsPersonEfMapper.selectByPrimaryKey(task.getPersonId());
        twmpOpeInstall.setPersonName(personEf.getPersonName());
        twmpOpeInstallMapper.insertSelective(twmpOpeInstall);
    }

    /**
    *
    * @description: 设备变更审批通过后，新建设备变更记录
    * @param: task
    * @return: void
    * @author: shihsh
    * @date: 2019/4/10
    */
    private void addOpeChangeRecord(TwmpMonitorTaskEf task) {
        TwmpOpeChange changeDevice = new TwmpOpeChange();
        changeDevice.setChangeId(SnowflakeIdWorkerUtil.generateLongId());
        changeDevice.setOldDeviceId(task.getDeviceId());
        changeDevice.setOldDeviceNumber(task.getDeviceNumber());
        changeDevice.setPersonId(task.getPersonId());
        changeDevice.setTaskId(task.getTaskId());
        changeDevice.setTaskCode(task.getTaskCode());
        changeDevice.setDepartmentId(task.getDepartmentId());
        changeDevice.setDepartmentName(task.getDepartmentName());
        changeDevice.setCreateTime(new Date());
        changeDevice.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        TwmpBsPersonEf personEf = twmpBsPersonEfMapper.selectByPrimaryKey(task.getPersonId());
        changeDevice.setPersonName(personEf.getPersonName());
        twmpOpeChangeMapper.insertSelective(changeDevice);
    }

    /**
    *
    * @description: 新建一条拆机记录
    * @param: task
    * @return: void
    * @author: shihsh
    * @date: 2019/4/10
    */
    private void addDismantleRecord(TwmpMonitorTaskEf task) {
        TwmpOpeDismantle twmpOpeDismantle = new TwmpOpeDismantle();
        twmpOpeDismantle.setDismantleId(SnowflakeIdWorkerUtil.generateLongId());
        twmpOpeDismantle.setPersonId(task.getPersonId());
        twmpOpeDismantle.setTaskId(task.getTaskId());
        twmpOpeDismantle.setDeviceId(task.getDeviceId());
        twmpOpeDismantle.setDeviceNumber(task.getDeviceNumber());
        twmpOpeDismantle.setDepartmentId(task.getDepartmentId());
        twmpOpeDismantle.setDepartmentName(task.getDepartmentName());
        twmpOpeDismantle.setCreateTime(new Date());
        twmpOpeDismantle.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        TwmpBsPersonEf personEf = twmpBsPersonEfMapper.selectByPrimaryKey(task.getPersonId());
        twmpOpeDismantle.setPersonName(personEf.getPersonName());
        twmpOpeDismantleMapper.insertSelective(twmpOpeDismantle);
    }


    /**
    *
    * @description:  判断登陆用户是否有审批权限
    * @param: userInfo
    * @return: boolean
    * @author: shihsh
    * @date: 2019/4/15
    */
    private boolean approvalAuthority(RedisUserInfoVO userInfo) {
        boolean result = false;
        TwmpSysRoleAuthority twmpSysRoleAuthority = new TwmpSysRoleAuthority();
        twmpSysRoleAuthority.setRoleId(userInfo.getRoleId());
        twmpSysRoleAuthority.setAuthorityId(AUTHORITYID);
        int count = twmpSysRoleAuthorityMapper.selectCount(twmpSysRoleAuthority);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    private void acceptOrAuthority(RedisUserInfoVO userInfo, TwmpMonitorTaskApprovalEf approvalEf) {
        if (approvalAuthority(userInfo)) {
            // 具备审批权限的用户，直接通过审批，不发申请消息
            DealApprovalVO dealApprovalVO = new DealApprovalVO();
            dealApprovalVO.setApprovalId(approvalEf.getApprovalId());
            dealApprovalVO.setTaskId(approvalEf.getTaskId());
            dealApprovalVO.setApprovalType(ApprovalType.stopTaskApproval.value);
            dealApprovalVO.setApprovalStatus(ApprovalStatus.approval.value);
            approveStopTask(userInfo.getToken(), dealApprovalVO);
        } else {
            // 没有审批权限的用户，发送审批消息
            sendMessage(approvalEf.getApprovalId(), userInfo, MessageType.notice, MessageSubType.approval, "", MessageStatus.unread);
        }
    }
}
