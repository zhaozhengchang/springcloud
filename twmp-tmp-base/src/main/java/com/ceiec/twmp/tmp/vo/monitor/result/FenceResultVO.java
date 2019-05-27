package com.ceiec.twmp.tmp.vo.monitor.result;

import com.ceiec.twmp.tmp.vo.monitor.add.FenceAreaGatherVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: FenceResultVO </br>
 * @createDate: 2019/3/18 11:38 </br>
 * @author: shihsh </br>
 * @description: 围栏结果VO </br>
 **/


public class FenceResultVO implements Serializable {

    private static final long serialVersionUID = -4561490661124010855L;

    /**
     * 围栏ID
     */
    private Long fenceId;

    private Long taskId;
    /**
     * 围栏描述
     */
    private String comment;

    /**
     * 围栏类型
     */
    private Byte fenceType;

    /**
     * 围栏开始时间
     */
    private String startTime;

    private Date startTimeDate;

    /**
     * 围栏结束时间
     */
    private String endTime;

    private Date endTimeDate;

    /**
     * 启用状态
     */
    private Byte fenceStatus;

    /**
     * 围栏区域集合
     */
    private List<FenceAreaGatherResultVO> fenceCoordinate;

    public Long getFenceId() {
        return fenceId;
    }

    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte getFenceType() {
        return fenceType;
    }

    public void setFenceType(Byte fenceType) {
        this.fenceType = fenceType;
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

    public Byte getFenceStatus() {
        return fenceStatus;
    }

    public void setFenceStatus(Byte fenceStatus) {
        this.fenceStatus = fenceStatus;
    }

    public List<FenceAreaGatherResultVO> getFenceCoordinate() {
        return fenceCoordinate;
    }

    public void setFenceCoordinate(List<FenceAreaGatherResultVO> fenceCoordinate) {
        this.fenceCoordinate = fenceCoordinate;
    }
}
