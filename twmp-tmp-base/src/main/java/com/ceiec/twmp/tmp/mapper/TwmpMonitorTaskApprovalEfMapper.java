package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskApprovalEf;
import com.ceiec.twmp.tmp.vo.monitor.approval.query.ApprovalListQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.approval.result.ApprovalListQueryResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpMonitorTaskApprovalEfMapper")
public interface TwmpMonitorTaskApprovalEfMapper extends Mapper<TwmpMonitorTaskApprovalEf> {

    List<ApprovalListQueryResultVO> listMonitorTaskApproval(ApprovalListQueryVO approvalListQueryVO);

    Long countMonitorTaskApproval(ApprovalListQueryVO approvalListQueryVO);

    TwmpMonitorTaskApprovalEf queryLatestApproval(Long taskId);

    Long countSubmittedApproval(TwmpMonitorTaskApprovalEf approval);

    void refuseApproval(Long taskId);
}