package com.ceiec.twmp.tmp.vo.taskOutsideRecord;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-23 11:07
 **/
public class TaskOutsideRecordQueryVO extends PageParentVO {

    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
