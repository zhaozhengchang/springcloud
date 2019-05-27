package com.ceiec.twmp.tmp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_log_operate")
public class TwmpLogOperate {
    @Id
    @Column(name = "operate_id")
    private Long operateId;

    /**
     * 操作对象名称
     */
    @Column(name = "operate_name")
    private String operateName;

    /**
     * 操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过
     */
    @Column(name = "operate_type")
    private Byte operateType;

    /**
     * 操作对象类型
     */
    @Column(name = "operate_object_type")
    private Byte operateObjectType;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作人id
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作备注
     */
    private String comment;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 组织机构名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * @return operate_id
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     * @param operateId
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     * 获取操作对象名称
     *
     * @return operate_name - 操作对象名称
     */
    public String getOperateName() {
        return operateName;
    }

    /**
     * 设置操作对象名称
     *
     * @param operateName 操作对象名称
     */
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    /**
     * 获取操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过
     *
     * @return operate_type - 操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过
     */
    public Byte getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过
     *
     * @param operateType 操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过
     */
    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取操作对象类型
     *
     * @return operate_object_type - 操作对象类型
     */
    public Byte getOperateObjectType() {
        return operateObjectType;
    }

    /**
     * 设置操作对象类型
     *
     * @param operateObjectType 操作对象类型
     */
    public void setOperateObjectType(Byte operateObjectType) {
        this.operateObjectType = operateObjectType;
    }

    /**
     * 获取操作时间
     *
     * @return operate_time - 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作人id
     *
     * @return operator_id - 操作人id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作人id
     *
     * @param operatorId 操作人id
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取操作备注
     *
     * @return comment - 操作备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置操作备注
     *
     * @param comment 操作备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取组织机构名称
     *
     * @return department_name - 组织机构名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置组织机构名称
     *
     * @param departmentName 组织机构名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}