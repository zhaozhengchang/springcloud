package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.dict.ApprovalType;
import com.ceiec.twmp.tmp.services.IMonitorTaskApprovalService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.ChangeParameterApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.DealApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.query.ApprovalListQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.add.SubmitApprovalVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.result.ApprovalListQueryResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: MonitorTaskApprovalController </br>
 * @createDate: 2019/3/27 11:32 </br>
 * @author: shihsh  </br>
 * @description: 监控任务审批Controller </br>
 * @version: V1.0
 **/

@RestController
@RequestMapping("/task")
public class MonitorTaskApprovalController {

    @Autowired
    private IMonitorTaskApprovalService monitorTaskApprovalService;


    /**
    *
    * @description: 查询监控任务审批列表
    * @param: token
    * @param: approvalListQueryVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/27
    */
    @PostMapping("/approvalList")
    public ResponseContent listApprovalTask(@RequestHeader String token, @RequestBody ApprovalListQueryVO approvalListQueryVO) {
        PagedItemsVO<ApprovalListQueryResultVO> approvalListPage = monitorTaskApprovalService.listTaskApproval(token, approvalListQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, approvalListPage);
    }

    @PostMapping("/submitApprove")
    public ResponseContent submitApproval(@RequestHeader String token, @RequestBody SubmitApprovalVO submitApprovalVO) {
        if (submitApprovalVO == null || submitApprovalVO.getApprovalType() == null) {
            return new ResponseContent(ResponseType.FAIL);
        }
        if (ApprovalType.deviceChangeApproval.value == submitApprovalVO.getApprovalType()
                || ApprovalType.stopTaskApproval.value == submitApprovalVO.getApprovalType()) {
            if (StringUtils.isNullOrEmpty(submitApprovalVO.getChangeReason())) {
                // 设备变更或者结束任务，需要填写原因
                return new ResponseContent(ResponseType.FAIL);
            }
        }
        if (submitApprovalVO.getApprovalType() == ApprovalType.createTaskApproval.value) {
            // 新建监控任务审批
            monitorTaskApprovalService.createTaskApproval(token, submitApprovalVO);
        } else if (submitApprovalVO.getApprovalType() == ApprovalType.deviceChangeApproval.value) {
            // 设备变更审批
            monitorTaskApprovalService.changeDeviceApproval(token, submitApprovalVO);
        } else if (submitApprovalVO.getApprovalType() == ApprovalType.endMonitorTaskApproval.value) {
            // 结束监控任务审批
            monitorTaskApprovalService.completeTaskApproval(token, submitApprovalVO);

        } else if ( submitApprovalVO.getApprovalType() == ApprovalType.stopTaskApproval.value) {
            // 终止监控任务审批
            monitorTaskApprovalService.stopTaskkApproval(token, submitApprovalVO);
        } else {
            // 其他情况 即参数变更请求用其他接口，不在此处处理
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }

    @PostMapping("changeParameter")
    public ResponseContent changeParameter(@RequestHeader String token, @RequestBody ChangeParameterApprovalVO parameterApproval) {
        monitorTaskApprovalService.changeParamterApproval(token, parameterApproval);
        return new ResponseContent(ResponseType.SUCCESS);
    }


    @PostMapping("/approve")
    public ResponseContent dealApproval(@RequestHeader String token, @RequestBody DealApprovalVO dealApprovalVO) {
        if (dealApprovalVO.getApprovalType() == ApprovalType.createTaskApproval.value) {
            // 新建监控任务审批
            monitorTaskApprovalService.approveCreateTask(token, dealApprovalVO);
        } else if (dealApprovalVO.getApprovalType() == ApprovalType.deviceChangeApproval.value) {
            // 设备变更审批
            monitorTaskApprovalService.approveChangeDevice(token, dealApprovalVO);
        } else if (dealApprovalVO.getApprovalType() == ApprovalType.endMonitorTaskApproval.value) {
            // 结束监控任务审批
            monitorTaskApprovalService.approveCompleteTask(token, dealApprovalVO);

        } else if ( dealApprovalVO.getApprovalType() == ApprovalType.stopTaskApproval.value) {
            // 终止监控任务审批
            monitorTaskApprovalService.approveStopTask(token, dealApprovalVO);
        } else {
            // 其他情况 即参数变更请求用其他接口，不在此处处理
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
