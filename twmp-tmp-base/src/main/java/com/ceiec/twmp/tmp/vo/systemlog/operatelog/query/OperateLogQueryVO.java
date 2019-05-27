package com.ceiec.twmp.tmp.vo.systemlog.operatelog.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @title: OperateLogQueryVO </br>
 * @createDate: 2019/3/21 9:55 </br>
 * @author: shihsh  </br>
 * @description: 操作日志查询VO </br>
 * @version: V1.0
 **/


public class OperateLogQueryVO extends PageParentVO {

    private static final long serialVersionUID = 4432517348251759305L;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作对象名
     */
    private String operateName;

    /**
     * 操作类型： 0.全部  1.添加（导入） 2.删除 3.修改 4.导出
     */
    private Byte operateType;

    /**
     * 操作类型为0，表示全部，则list中加入1,2,3,4
     */
    private List<Byte> operateTypeList;

    /**
     * 操作对象类型
     */
    private Byte operateObjectType;

    /**
     * 开始时间
     */
    private String startTime;

    private Date startTimeDate;

    /**
     * 结束时间
     */
    private String endTime;

    private Date endTimeDate;

    /**
     * 权限管理
     */
    private List<Long> departmentIds;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public List<Byte> getOperateTypeList() {
        return operateTypeList;
    }

    public void setOperateTypeList(List<Byte> operateTypeList) {
        this.operateTypeList = operateTypeList;
    }

    public Byte getOperateObjectType() {
        return operateObjectType;
    }

    public void setOperateObjectType(Byte operateObjectType) {
        this.operateObjectType = operateObjectType;
    }

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

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
