package com.ceiec.twmp.tmp.vo.systemlog.operatelog.result;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @title: OperateLogQueryResultVO </br>
 * @createDate: 2019/3/21 10:01 </br>
 * @author: shihsh  </br>
 * @description: 操作日志结果VO </br>
 * @version: V1.0
 **/


public class OperateLogQueryResultVO extends PageParentVO {

    private static final long serialVersionUID = 4214502270484471762L;

    private Long operateId;

    /**
     * 操作时间字符串类型
     */
    private String operateTime;
    //设置输出格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operateTimeDate;

    /**
     * 操作类型
     */
    private Byte operateType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作对象类型
     */
    private Byte operateObjectType;

    /**
     * 操作对象名称
     */
    private String operateName;

    /**
     * 操作内容
     */
    private String content;

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Date getOperateTimeDate() {
        return operateTimeDate;
    }

    public void setOperateTimeDate(Date operateTimeDate) {
        this.operateTimeDate = operateTimeDate;
    }

    public Byte getOperateObjectType() {
        return operateObjectType;
    }

    public void setOperateObjectType(Byte operateObjectType) {
        this.operateObjectType = operateObjectType;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
