package com.ceiec.twmp.tmp.vo.monitor.query;

import java.util.List;

/**
 * @version V1.0
 * @title: MonitorTaskDetailQueryVO </br>
 * @createDate: 2019/3/17 20:09 </br>
 * @author: shihsh </br>
 * @description: 监控任务详情查询VO </br>
 **/


public class MonitorTaskDetailQueryVO{

    private Long taskId;

    private Byte type;

    private List<Long> departmentIds;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
