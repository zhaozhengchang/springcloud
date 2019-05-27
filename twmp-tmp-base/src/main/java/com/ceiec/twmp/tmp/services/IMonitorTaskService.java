package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.common.dict.MonitorRequestType;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskFenceEf;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskFenceSpaceEf;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskTeamEf;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.monitor.add.FenceVO;
import com.ceiec.twmp.tmp.vo.monitor.add.MonitorGuardianVO;
import com.ceiec.twmp.tmp.vo.monitor.add.MonitorTaskAddVO;
import com.ceiec.twmp.tmp.vo.monitor.query.TaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskDetailQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.TaskResultVO;
import com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskDetailQueryResultVO;
import com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskQueryResultVO;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonTaskResultVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutSideRecordVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordQueryVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;

import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: IMonitorTaskService </br>
 * @createDate：2019/3/12 17:12 </br>
 * @author：shihsh </br>
 * @description: 监控任务Service </br>
 **/

public interface IMonitorTaskService {

    void addOrEditMonitorTask(String token, MonitorTaskAddVO monitorTaskAddVO);

    void addMonitorTask(String token, RedisUserInfoVO user, TwmpMonitorTaskEf twmpMonitorTaskEf, MonitorRequestType monitorRequestType);

    void editMonitorTask(String token, RedisUserInfoVO user, TwmpMonitorTaskEf twmpMonitorTaskEf);

    PagedItemsVO<MonitorTaskQueryResultVO> queryTaskListByPage(String token, MonitorTaskQueryVO monitorTaskQueryVO);


    /**
    *
    * @description: 查询监控任务详情
    * @param: token
    * @param: monitorTaskDetailQueryVO
    * @return: com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskDetailQueryResultVO
    * @author: shihsh
    * @date: 2019/3/17
    */
    MonitorTaskDetailQueryResultVO queryMonitorTaskDetail(String token, MonitorTaskDetailQueryVO monitorTaskDetailQueryVO);

    void delMonitorTask(String token, Long taskId);

    void addOrEditTaskTeam(String token, RedisUserInfoVO user, List<MonitorGuardianVO> list, Long taskId, MonitorRequestType monitorRequestType);

    void addMonitorTaskTeam(String token, List<TwmpMonitorTaskTeamEf> list);

    void editMonitorTaskTeam(String token,  List<TwmpMonitorTaskTeamEf> list);

    void delMonitorTaskTeam(String token, RedisUserInfoVO user, List<Long> teamList);

    void addOrEditFenceList(String token, RedisUserInfoVO user, Long taskId, List<FenceVO> fenceVO, MonitorRequestType monitorRequestType);

    void addFenceList(String token,  List<TwmpMonitorTaskFenceEf> fenceVO);

    void editFenceList(String token, List<TwmpMonitorTaskFenceEf> fenceVO);

    void delFenceList(String token, RedisUserInfoVO user, List<Long> fenceList);

    void addFenceSpaceList(String token, List<TwmpMonitorTaskFenceSpaceEf> fenceSpaceList);

    void editFenceSpaceList(String token, List<TwmpMonitorTaskFenceSpaceEf> fenceSpaceList);

    void restoreMonitorTask(String token,Long personId, Long changeTaskId, Long taskId);

    List<TwmpMonitorTaskEf> getTaskByTime(Date startTime, Date endTime);

    TwmpMonitorTaskEf getTaskByTaskCode(String taskCode);

    List<PersonTaskResultVO> getPersonTaskList(String personName);

    PagedItemsVO<TaskResultVO> queryMonitorPerson(String token, TaskQueryVO taskQueryVO);

    PagedItemsVO<TaskOutsideRecordResultVO> queryTaskOutSideRecordByPage(TaskOutsideRecordQueryVO taskOutsideRecordQueryVO);

    void updateTaskOutsideRecord(String token, TaskOutSideRecordVO taskOutSideRecordVO);

    void delTaskOutsideRecord(String token, Long taskOutsideRecordId);
}
