package com.ceiec.twmp.tmp.vo.monitor.approval.add;

import com.ceiec.twmp.tmp.vo.monitor.add.MonitorTaskAddVO;
import com.ceiec.twmp.tmp.vo.person.add.PersonDataVO;

/**
 * @title: ChangeParameterApprovalVO </br>
 * @createDate: 2019/4/2 18:09 </br>
 * @author: shihsh  </br>
 * @description: 提交参数变更审批 </br>
 * @version: V1.0
 **/


public class ChangeParameterApprovalVO extends MonitorTaskAddVO {
    private Byte approvalType;

    private String changeReason;

    public Byte getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Byte approvalType) {
        this.approvalType = approvalType;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }
}
