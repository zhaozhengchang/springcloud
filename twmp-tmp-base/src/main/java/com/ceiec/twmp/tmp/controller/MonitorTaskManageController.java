package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IMonitorTaskService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.monitor.add.MonitorTaskAddVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskDetailQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskDetailQueryResultVO;
import com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskQueryResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @title: MonitorTaskManageController </br>
 * @createDate：2019/3/12 17:08 </br>
 * @author：shihsh </br>
 * @description: 监控任务管理</br>
 **/

@RestController
@RequestMapping("/task")
public class MonitorTaskManageController {


    @Autowired
    private IMonitorTaskService monitorTaskService;

    @PostMapping("/taskData")
    public ResponseContent addOrEditTask(@RequestHeader String token, @RequestBody MonitorTaskAddVO monitorTaskAddVO) {
        monitorTaskService.addOrEditMonitorTask(token, monitorTaskAddVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    @PostMapping("/monitorTaskDel/{taskId}")
    public ResponseContent delMonitorTask(@RequestHeader String token, @PathVariable Long taskId) {
        monitorTaskService.delMonitorTask(token, taskId);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    @PostMapping("/monitorTaskList")
    public ResponseContent getMonitorTaskByPage(@RequestHeader String token, @RequestBody MonitorTaskQueryVO monitorTaskQueryVO) {
        PagedItemsVO<MonitorTaskQueryResultVO> taskInfo = monitorTaskService.queryTaskListByPage(token, monitorTaskQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, taskInfo);
    }

    @PostMapping("/taskDetail")
    public ResponseContent getMonitorTaskDetail(@RequestHeader String token, @RequestBody MonitorTaskDetailQueryVO monitorTaskDetailQueryVO) {
        MonitorTaskDetailQueryResultVO monitorTaskDetailQueryResultVO = monitorTaskService.queryMonitorTaskDetail(token, monitorTaskDetailQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, monitorTaskDetailQueryResultVO);
    }
}
