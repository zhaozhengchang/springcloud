package com.ceiec.twmp.tmp.vo.monitor.add;

import java.util.List;

/**
 * @version V1.0
 * @title: FenceShapeVO </br>
 * @createDate：2019/3/12 17:40 </br>
 * @author：shihsh </br>
 * @description: 围栏VO </br>
 **/


public class FenceVO {

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
    private Byte fenceTypeId;

    /**
     * 围栏开始时间
     */
    private String startTime;

    /**
     * 围栏结束时间
     */
    private String endTime;

    /**
     * 启用状态
     */
    private Byte fenceStatus;

    /**
     * 删除的围栏Id，多个用逗号分隔
     */
    private String deleteFenceAreaId;

    /**
     * 围栏区域集合
     */
    private List<FenceAreaGatherVO> fenceAreaGather;


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

    public Byte getFenceTypeId() {
        return fenceTypeId;
    }

    public void setFenceTypeId(Byte fenceTypeId) {
        this.fenceTypeId = fenceTypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Byte getFenceStatus() {
        return fenceStatus;
    }

    public void setFenceStatus(Byte fenceStatus) {
        this.fenceStatus = fenceStatus;
    }

    public String getDeleteFenceAreaId() {
        return deleteFenceAreaId;
    }

    public void setDeleteFenceAreaId(String deleteFenceAreaId) {
        this.deleteFenceAreaId = deleteFenceAreaId;
    }

    public List<FenceAreaGatherVO> getFenceAreaGather() {
        return fenceAreaGather;
    }

    public void setFenceAreaGather(List<FenceAreaGatherVO> fenceAreaGather) {
        this.fenceAreaGather = fenceAreaGather;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
