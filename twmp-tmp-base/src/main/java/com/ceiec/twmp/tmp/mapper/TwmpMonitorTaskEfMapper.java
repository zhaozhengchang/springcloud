package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.vo.monitor.query.TaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskDetailQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.*;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Component(value = "twmpMonitorTaskEfMapper")
public interface TwmpMonitorTaskEfMapper extends Mapper<TwmpMonitorTaskEf> {

    List<MonitorTaskQueryResultVO> getTaskList(MonitorTaskQueryVO monitorTaskQueryVO);

    Long countTaskList(MonitorTaskQueryVO monitorTaskQueryVO);

    MonitorTaskDetailQueryResultVO getMonitorTaskDetail(MonitorTaskDetailQueryVO monitorTaskDetailQueryVO);

    List<FenceResultVO> getFenceList(Long taskId);

    List<MonitorGuardianResultVO> getGuardianList(Long taskId);

    List<TwmpMonitorTaskEf> getTaskByTime(Date startTime, Date endTime);

    List<PersonTaskResultVO> getPersonTaskList(String personName);

    TwmpMonitorTaskEf getTaskByTaskCode(String taskCode);

    List<TwmpMonitorTaskEf> getInstalledAndMonitoringTasks();

    List<TaskResultVO> queryMonitorPerson(TaskQueryVO taskQueryVO);

    Long countMonitorPerson(TaskQueryVO monitorPersonQueryVO);

}