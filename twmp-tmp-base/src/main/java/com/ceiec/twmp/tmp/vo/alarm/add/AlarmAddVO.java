package com.ceiec.twmp.tmp.vo.alarm.add;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 15:46
 **/
public class AlarmAddVO {

    private Long taskId;

    private String remark;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
