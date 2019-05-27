package com.ceiec.twmp.tmp.vo.monitor.approval.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @title: ApprovalListQueryVO </br>
 * @createDate: 2019/3/27 10:43 </br>
 * @author: shihsh  </br>
 * @description: 监控任务审批列表查询 </br>
 * @version: V1.0
 **/


public class ApprovalListQueryVO extends PageParentVO {
    private static final long serialVersionUID = 4921458167476000338L;

    private String startTime;

    private Date startTimeDate;

    private String endTime;

    private Date endTimeDate;

    private String taskCode;

    private Byte approvalStatus;

    private List<Long> departmentIds;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Byte getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Byte approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
