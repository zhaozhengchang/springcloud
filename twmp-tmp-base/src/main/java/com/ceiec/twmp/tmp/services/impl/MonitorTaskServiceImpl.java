package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.entity.*;
import com.ceiec.twmp.tmp.mapper.*;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IMonitorTaskService;
import com.ceiec.twmp.tmp.services.IPersonService;
import com.ceiec.twmp.tmp.utils.ConvertUtil;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.monitor.add.FenceAreaGatherVO;
import com.ceiec.twmp.tmp.vo.monitor.add.FenceVO;
import com.ceiec.twmp.tmp.vo.monitor.add.MonitorGuardianVO;
import com.ceiec.twmp.tmp.vo.monitor.add.MonitorTaskAddVO;
import com.ceiec.twmp.tmp.vo.monitor.delete.MonitorGuardianDeleteVO;
import com.ceiec.twmp.tmp.vo.monitor.query.TaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskDetailQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.*;
import com.ceiec.twmp.tmp.vo.person.result.PersonAndDeviceVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonCriminalResultVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutSideRecordVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordQueryVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: MonitorTaskServiceImpl </br>
 * @createDate：2019/3/12 17:23 </br>
 * @author：shihsh </br>
 * @description: 监控任务Service </br>
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class MonitorTaskServiceImpl implements IMonitorTaskService {

    @Autowired
    private TwmpMonitorTaskEfMapper twmpMonitorTaskEfMapper;

    @Autowired
    private TwmpSysDepartmentMapper twmpSysDepartmentMapper;

    @Autowired
    private TwmpMonitorTaskChangeEfMapper twmpMonitorTaskChangeEfMapper;

    @Autowired
    private ILogService logService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private TwmpMonitorTaskTeamEfMapper twmpMonitorTaskTeamEfMapper;

    @Autowired
    private TwmpMonitorTaskFenceEfMapper twmpMonitorTaskFenceEfMapper;

    @Autowired
    private TwmpMonitorTaskFenceSpaceEfMapper twmpMonitorTaskFenceSpaceEfMapper;

    @Autowired
    private TwmpBsPersonEfMapper twmpBsPersonEfMapper;

    @Autowired
    private TwmpMonitorTaskOutsideRecordEfMapper twmpMonitorTaskOutsideRecordEfMapper;

    @Override
    public void addOrEditMonitorTask(String token, MonitorTaskAddVO monitorTaskAddVO) {
        TwmpMonitorTaskEf twmpMonitorTaskEf = new TwmpMonitorTaskEf();
        ObjectUtils.copy(monitorTaskAddVO, twmpMonitorTaskEf);
        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        PersonAndDeviceVO personAndDeviceInfo = personService.getPersonAndDeviceInfo(token, monitorTaskAddVO.getPersonId());

//        twmpMonitorTaskEf.setDeviceId(personAndDeviceInfo.getDeviceId());
//        twmpMonitorTaskEf.setDeviceNumber(personAndDeviceInfo.getDeviceNumber());
        twmpMonitorTaskEf.setPersonName(personAndDeviceInfo.getPersonName());
        twmpMonitorTaskEf.setDepartmentId(personAndDeviceInfo.getDepartmentId());
        twmpMonitorTaskEf.setDepartmentName(personAndDeviceInfo.getDepartmentName());
        Long taskId = twmpMonitorTaskEf.getTaskId();
        MonitorRequestType monitorRequestType = MonitorRequestType.get(monitorTaskAddVO.getTaskType());
        // 判断是否为新建监控任务、参数变更
        if (monitorRequestType == MonitorRequestType.add || monitorRequestType == MonitorRequestType.change) {
            taskId = SnowflakeIdWorkerUtil.generateLongId();
            twmpMonitorTaskEf.setTaskId(taskId);
            monitorTaskAddVO.setTaskId(taskId);
            TwmpSysDepartment twmpSysDepartment = twmpSysDepartmentMapper.selectByPrimaryKey(twmpMonitorTaskEf.getDepartmentId());
            String taskCode = twmpMonitorTaskEf.getDepartmentId() + "T" +
                    DateFormatUtils.dateToString(new Date(), DateFormatUtils.NUMBER_DATE_FORMATE) + (twmpSysDepartment.getTaskNum() + 1);
            twmpMonitorTaskEf.setTaskCode(taskCode);
            twmpMonitorTaskEf.setTaskStatus(TaskStatus.initial.value);
            twmpMonitorTaskEf.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
            addMonitorTask(token, user, twmpMonitorTaskEf, monitorRequestType);
        } else if (monitorRequestType == MonitorRequestType.edit) {
            editMonitorTask(token, user, twmpMonitorTaskEf);
        }

        //处理监护人
        String deleteMontiorTesms = monitorTaskAddVO.getDeleteMonitorTaskTeamId();
        List<Long> teamList = ConvertUtil.stringToLongList(deleteMontiorTesms, ",");
        // 删除监护成员，如果是参数变更所有数据新建，忽略删除操作
        if (monitorRequestType != MonitorRequestType.change) {
            if (teamList != null && teamList.size() != 0) {
                delMonitorTaskTeam(token, user, teamList);
            }
        }
        addOrEditTaskTeam(token, user, monitorTaskAddVO.getGuardianList(), twmpMonitorTaskEf.getTaskId(), monitorRequestType);

        //处理围栏信息
        String delFenceIds = monitorTaskAddVO.getDeleteFenceId();
        List<Long> delFenceList = ConvertUtil.stringToLongList(delFenceIds, ",");
        // 删除电子围栏, 参数变更忽略删除操作，应该是删除所有
        if (monitorRequestType != MonitorRequestType.change) {
            if (delFenceList != null && delFenceList.size() != 0) {
                delFenceList(token, user, delFenceList);
            }
        }
        // 新增或编辑电子围栏
        addOrEditFenceList(token, user, taskId, monitorTaskAddVO.getFenceList(), monitorRequestType);

    }

    @Override
    public void addMonitorTask(String token, RedisUserInfoVO user, TwmpMonitorTaskEf twmpMonitorTaskEf, MonitorRequestType monitorRequestType) {
        twmpMonitorTaskEf.setCreator(user.getUserName());
        twmpMonitorTaskEf.setCreatorId(user.getUserId());
        twmpMonitorTaskEf.setCreateTime(new Date());
        twmpMonitorTaskEfMapper.insertSelective(twmpMonitorTaskEf);
        // 新增记录操作日志，参数变更不记录
        if (monitorRequestType == MonitorRequestType.add) {
            logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.task.value, twmpMonitorTaskEf.getTaskCode(), null);
        }
    }

    @Override
    public void editMonitorTask(String token, RedisUserInfoVO user, TwmpMonitorTaskEf twmpMonitorTaskEf) {
        twmpMonitorTaskEf.setUpdater(user.getUserName());
        twmpMonitorTaskEf.setUpdaterId(user.getUserId());
        twmpMonitorTaskEf.setUpdateTime(new Date());
        twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(twmpMonitorTaskEf);
        //操作日志
        logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.task.value, twmpMonitorTaskEf.getTaskCode(), null);
    }

    @Override
    public PagedItemsVO<MonitorTaskQueryResultVO> queryTaskListByPage(String token, MonitorTaskQueryVO monitorTaskQueryVO) {
        monitorTaskQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        if (monitorTaskQueryVO.getTaskStatus() != null) {
            List<Byte> tasksStatus = new ArrayList<>();
            monitorTaskQueryVO.setTaskStatusList(tasksStatus);
            if (monitorTaskQueryVO.getTaskStatus().equals(PersonStatus.total.value)) {
                // 查询全部
                monitorTaskQueryVO.setTaskStatusList(null);
            } else if (monitorTaskQueryVO.getTaskStatus().equals(PersonStatus.unmonitored.value) ) {
                // 前端显示未监控，对应监控任务:初始化、审批中、安装中、安装完成
                tasksStatus.add(TaskStatus.initial.value);
                tasksStatus.add(TaskStatus.initial_approval.value);
                tasksStatus.add(TaskStatus.initial_wait_to_install.value);
                tasksStatus.add(TaskStatus.initial_installed.value);
            } else if (monitorTaskQueryVO.getTaskStatus().equals(PersonStatus.monitoring.value)) {
                // 监控中对应监控任务:监控中
                tasksStatus.add(TaskStatus.monitoring.value);
            } else {
                // 监控结束对应监控任务: 监控结束、待拆机、已结束
                tasksStatus.add(TaskStatus.completed.value);
                tasksStatus.add(TaskStatus.completed_wait_to_dismantle.value);
                tasksStatus.add(TaskStatus.over.value);
            }
        }
        Long total = twmpMonitorTaskEfMapper.countTaskList(monitorTaskQueryVO);
        PageHelper.startPage(monitorTaskQueryVO.getPageNo(), monitorTaskQueryVO.getPageSize(), "create_time desc");
        // 组织机构权限

        List<MonitorTaskQueryResultVO> taskInfoList =  twmpMonitorTaskEfMapper.getTaskList(monitorTaskQueryVO);
        for (MonitorTaskQueryResultVO task : taskInfoList) {
            Byte taskStatus = task.getTaskStatus();
            if (TaskStatus.monitoring.value > taskStatus) {
                // 未开始监控
                task.setTaskStatus(PersonStatus.unmonitored.value);
                // 监控进度为0%
                task.setTaskProgress(0);
            } else if (TaskStatus.monitoring.value == taskStatus) {
                // 已开始监控
                task.setTaskStatus(PersonStatus.monitoring.value);
            } else {
                // 监控结束,进度为100%
                task.setTaskProgress(100);
                task.setTaskStatus(PersonStatus.over.value);
            }
            // 根据1.当前监控任务的状态；2.是否有在审批中的任务 判断按钮的显示与否
            task.setDetailButton(true);
            if (taskStatus == TaskStatus.initial.value) {
                if (task.getCountSubmit() == 0) {
                    task.setSubmitButton(true);
                    task.setDetailEditButton(true);
                    task.setDetailDelButton(true);
                }
            } else if (taskStatus == TaskStatus.initial_wait_to_install.value) {
                if (task.getCountSubmit() == 0) {
                    task.setChangeParameterButton(true);
                }
                task.setStopTaskButton(true);
                task.setExportInformButton(true);
            } else if (taskStatus == TaskStatus.initial_installed.value) {
                if (task.getCountSubmit() == 0) {
                    task.setChangeParameterButton(true);
                    if (task.getCountChangeDevice() == 0) {
                        task.setChangeDeviceButton(true);
                    }
                }
                task.setStopTaskButton(true);
                task.setExportInformButton(true);
            } else if (taskStatus == TaskStatus.monitoring.value) {
                if (task.getCountSubmit() == 0) {
                    task.setChangeParameterButton(true);
                    if (task.getCountChangeDevice() == 0) {
                        task.setChangeDeviceButton(true);
                    }
                }
                task.setStopTaskButton(true);
                task.setExportInformButton(true);
            } else if (taskStatus == TaskStatus.completed.value) {
                if (task.getCountSubmit() == 0) {
                    task.setChangeParameterButton(true);
                    task.setCompleteTaskButton(true);
                }
                task.setStopTaskButton(true);
            } else if (taskStatus == TaskStatus.over.value) {
                task.setExportReportButton(true);
            }
        }
        return new PagedItemsVO<>(total,taskInfoList);
    }

    @Override
    public MonitorTaskDetailQueryResultVO queryMonitorTaskDetail(String token, MonitorTaskDetailQueryVO monitorTaskDetailQueryVO) {
        // 组织机构权限
        monitorTaskDetailQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        MonitorTaskDetailQueryResultVO monitorTaskDetailQueryResultVO;
        if (monitorTaskDetailQueryVO.getType().equals(MonitorTaskChangeType.current.value)) {
            monitorTaskDetailQueryResultVO = twmpMonitorTaskEfMapper.getMonitorTaskDetail(monitorTaskDetailQueryVO);
        } else {
            monitorTaskDetailQueryResultVO = twmpMonitorTaskChangeEfMapper.getMonitorTaskDetail(monitorTaskDetailQueryVO);
        }
        if (monitorTaskDetailQueryResultVO == null) {
            return monitorTaskDetailQueryResultVO;
        }
        //处理时间，Date转化为String
        monitorTaskDetailQueryResultVO.setStartTime(DateFormatUtils.dateTimeToString(monitorTaskDetailQueryResultVO.getStartTimeDate()));
        monitorTaskDetailQueryResultVO.setEndTime(DateFormatUtils.dateTimeToString(monitorTaskDetailQueryResultVO.getEndTimeDate()));
        monitorTaskDetailQueryResultVO.setReportStartTime(DateFormatUtils.dateTimeToString(monitorTaskDetailQueryResultVO.getReportStartTimeDate()));
        monitorTaskDetailQueryResultVO.setReportEndTime(DateFormatUtils.dateTimeToString(monitorTaskDetailQueryResultVO.getReportEndTimeDate()));
        monitorTaskDetailQueryResultVO.setReportTime(DateFormatUtils.dateTimeToString(monitorTaskDetailQueryResultVO.getReportTimeDate()));

        //处理犯罪记录
        List<PersonCriminalResultVO> list = new ArrayList<>();
        for (TwmpBsPersonCriminalEf twmpBsPersonCriminalEf : monitorTaskDetailQueryResultVO.getCriminalEfList()) {
            PersonCriminalResultVO personCriminalResultVO = new PersonCriminalResultVO();
            ObjectUtils.copy(twmpBsPersonCriminalEf, personCriminalResultVO);
            list.add(personCriminalResultVO);
        }
        monitorTaskDetailQueryResultVO.setCriminalList(list);
        monitorTaskDetailQueryResultVO.setCriminalEfList(null);

        //处理围栏，一个监控任务有多个电子围栏
        List<FenceResultVO> fenceList = twmpMonitorTaskEfMapper.getFenceList(monitorTaskDetailQueryResultVO.getTaskId());
        for (FenceResultVO fenceResultVO : fenceList) {
            fenceResultVO.setStartTime(DateFormatUtils.dateTimeToString(fenceResultVO.getStartTimeDate()));
            fenceResultVO.setEndTime(DateFormatUtils.dateTimeToString(fenceResultVO.getEndTimeDate()));
            List<FenceAreaGatherResultVO> fenceAreaGatherResult = fenceResultVO.getFenceCoordinate();
            //围栏区域Strin转化为经纬度坐标集合
            for (FenceAreaGatherResultVO fenceAreaGather : fenceAreaGatherResult) {
                String space = fenceAreaGather.getSpace();
                String[] locations = space.split(" ");
                List< FenceAreaGatherResultVO.FenceCoordinateResultVO> fenceCoordinateList = new ArrayList<>();
                for (String location : locations) {
                    FenceAreaGatherResultVO.FenceCoordinateResultVO fenceCoordinateResult = new FenceAreaGatherResultVO.FenceCoordinateResultVO();
                    //经纬度坐标
                    String[] xAndY = location.split(",");
                    fenceCoordinateResult.setLongtitude(xAndY[0]);
                    fenceCoordinateResult.setLatitude(xAndY[1]);
                    fenceCoordinateList.add(fenceCoordinateResult);
                }
                fenceAreaGather.setFenceCoordinate(fenceCoordinateList);
            }
            fenceResultVO.setFenceCoordinate(fenceAreaGatherResult);
        }

        List<MonitorGuardianResultVO> guardianList = twmpMonitorTaskEfMapper.getGuardianList(monitorTaskDetailQueryResultVO.getTaskId());
        monitorTaskDetailQueryResultVO.setGuardianList(guardianList);
        monitorTaskDetailQueryResultVO.setFence(fenceList);
        return monitorTaskDetailQueryResultVO;
    }


    @Override
    public void delMonitorTask(String token, Long taskId) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        TwmpMonitorTaskEf twmpMonitorTaskEf = new TwmpMonitorTaskEf();
        twmpMonitorTaskEf.setTaskId(taskId);
        twmpMonitorTaskEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpMonitorTaskEf.setUpdater(user.getUserName());
        twmpMonitorTaskEf.setUpdaterId(user.getUserId());
        twmpMonitorTaskEf.setUpdateTime(new Date());
        twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(twmpMonitorTaskEf);
        //记录操作日志
        twmpMonitorTaskEf = twmpMonitorTaskEfMapper.selectByPrimaryKey(twmpMonitorTaskEf);
        logService.saveOperateLog(token, OperateType.delete.value, OperateObjectType.task.value, twmpMonitorTaskEf.getTaskName(), null);
    }

    @Override
    public void addOrEditTaskTeam(String token, RedisUserInfoVO user, List<MonitorGuardianVO> monitorGuardianVOList, Long taskId, MonitorRequestType monitorRequestType) {
        if (monitorGuardianVOList == null || monitorGuardianVOList.size() == 0) {
            return;
        }
        List<TwmpMonitorTaskTeamEf> addList = new ArrayList<>();
        List<TwmpMonitorTaskTeamEf> editList = new ArrayList<>();
        for (MonitorGuardianVO monitorGuardianVO : monitorGuardianVOList) {
            TwmpMonitorTaskTeamEf twmpMonitorTaskTeamEf = new TwmpMonitorTaskTeamEf();
            ObjectUtils.copy(monitorGuardianVO, twmpMonitorTaskTeamEf);
            twmpMonitorTaskTeamEf.setTaskId(taskId);
            if (monitorRequestType == MonitorRequestType.change || twmpMonitorTaskTeamEf.getTeamId() == null) {
                twmpMonitorTaskTeamEf.setTeamId(SnowflakeIdWorkerUtil.generateLongId());
                twmpMonitorTaskTeamEf.setCreator(user.getUserName());
                twmpMonitorTaskTeamEf.setCreatorId(user.getUserId());
                twmpMonitorTaskTeamEf.setCreateTime(new Date());
                addList.add(twmpMonitorTaskTeamEf);
            } else {
                twmpMonitorTaskTeamEf.setUpdater(user.getUserName());
                twmpMonitorTaskTeamEf.setUpdaterId(user.getUserId());
                twmpMonitorTaskTeamEf.setUpdateTime(new Date());
                editList.add(twmpMonitorTaskTeamEf);
            }
        }
        addMonitorTaskTeam(token, addList);
//        editMonitorTaskTeam(token, editList);
        editMonitorTaskTeam(token, editList);
    }


    @Override
    public void addMonitorTaskTeam(String token, List<TwmpMonitorTaskTeamEf> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        twmpMonitorTaskTeamEfMapper.insertTeamList(list);
    }

    @Override
    public void editMonitorTaskTeam(String token, List<TwmpMonitorTaskTeamEf> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        // 更新
        twmpMonitorTaskTeamEfMapper.updateTeamList(list);
    }

    @Override
    public void delMonitorTaskTeam(String token, RedisUserInfoVO user, List<Long> teamList) {
        List<MonitorGuardianDeleteVO> list = new ArrayList<>();
        for (Long teamId : teamList) {
           MonitorGuardianDeleteVO monitorGuardianDeleteVO = new MonitorGuardianDeleteVO();
           monitorGuardianDeleteVO.setTeamId(teamId);
           monitorGuardianDeleteVO.setUpdater(user.getUserName());
           monitorGuardianDeleteVO.setUpdaterId(user.getUserId());
           monitorGuardianDeleteVO.setUpdateTime(new Date());
           monitorGuardianDeleteVO.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
           list.add(monitorGuardianDeleteVO);
        }
        twmpMonitorTaskTeamEfMapper.deleteTeamList(list);
    }

    @Override
    public void addOrEditFenceList(String token, RedisUserInfoVO user, Long taskId, List<FenceVO> fenceList, MonitorRequestType monitorRequestType) {
        if (fenceList == null || fenceList.size() == 0) {
            return;
        }
        List<TwmpMonitorTaskFenceEf> addList = new ArrayList<>();
        List<TwmpMonitorTaskFenceEf> editList = new ArrayList<>();
        for (FenceVO fence : fenceList) {
            TwmpMonitorTaskFenceEf twmpMonitorTaskFenceEf = new TwmpMonitorTaskFenceEf();
            ObjectUtils.copy(fence, twmpMonitorTaskFenceEf);
            twmpMonitorTaskFenceEf.setFenceType(fence.getFenceTypeId());
            if (monitorRequestType == MonitorRequestType.change || twmpMonitorTaskFenceEf.getFenceId() == null) {
                twmpMonitorTaskFenceEf.setFenceId(SnowflakeIdWorkerUtil.generateLongId());
                twmpMonitorTaskFenceEf.setTaskId(taskId);
                twmpMonitorTaskFenceEf.setCreator(user.getUserName());
                twmpMonitorTaskFenceEf.setCreatorId(user.getUserId());
                twmpMonitorTaskFenceEf.setCreateTime(new Date());
                addList.add(twmpMonitorTaskFenceEf);
            } else {
                twmpMonitorTaskFenceEf.setTaskId(taskId);
                twmpMonitorTaskFenceEf.setUpdater(user.getUserName());
                twmpMonitorTaskFenceEf.setUpdaterId(user.getUserId());
                twmpMonitorTaskFenceEf.setUpdateTime(new Date());
                editList.add(twmpMonitorTaskFenceEf);
            }
            //处理围栏区域
            addOrEditFenceSpace(token, twmpMonitorTaskFenceEf.getFenceId(), fence.getFenceAreaGather());
        }
        addFenceList(token, addList);
        editFenceList(token, editList);

    }

    @Override
    public void addFenceList(String token, List<TwmpMonitorTaskFenceEf> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        twmpMonitorTaskFenceEfMapper.addFenceList(list);

    }

    @Override
    public void editFenceList(String token, List<TwmpMonitorTaskFenceEf> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        twmpMonitorTaskFenceEfMapper.editFenceList(list);
    }

    @Override
    public void delFenceList(String token, RedisUserInfoVO user, List<Long> fenceIdList) {
        if (fenceIdList == null || fenceIdList.size() == 0) {
            return;
        }
        List<TwmpMonitorTaskFenceEf> list = new ArrayList<>();
        for (Long fenceId : fenceIdList) {
            TwmpMonitorTaskFenceEf twmpMonitorTaskFenceEf = new TwmpMonitorTaskFenceEf();
            twmpMonitorTaskFenceEf.setFenceId(fenceId);
            twmpMonitorTaskFenceEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
            twmpMonitorTaskFenceEf.setUpdater(user.getUserName());
            twmpMonitorTaskFenceEf.setUpdaterId(user.getUserId());
            twmpMonitorTaskFenceEf.setUpdateTime(new Date());
        }
        twmpMonitorTaskFenceEfMapper.delFenceList(list);
    }

    public void addOrEditFenceSpace(String token,Long fenceId, List<FenceAreaGatherVO> fenceAreaList) {
        if (fenceAreaList == null || fenceAreaList.size() == 0) {
            return;
        }
        List<TwmpMonitorTaskFenceSpaceEf> addList = new ArrayList<>();
        List<TwmpMonitorTaskFenceSpaceEf> editList = new ArrayList<>();
        for (FenceAreaGatherVO fenceArea : fenceAreaList) {
            TwmpMonitorTaskFenceSpaceEf twmpMonitorTaskFenceSpaceEf = new TwmpMonitorTaskFenceSpaceEf();
            StringBuffer sb = new StringBuffer();
            List<FenceAreaGatherVO.FenceCoordinateVO> fenceCoordinates = fenceArea.getFenceCoordinate();
            if (fenceCoordinates == null || fenceCoordinates.size() == 0) {
                continue;
            }
            for (FenceAreaGatherVO.FenceCoordinateVO fenceCoordinate : fenceCoordinates) {
                sb.append(fenceCoordinate.getLongtitude()).append(",").append(fenceCoordinate.getLatitude()).append(" ");
            }
            twmpMonitorTaskFenceSpaceEf.setFenceId(fenceId);
            //去掉最后一个空格
            twmpMonitorTaskFenceSpaceEf.setSpace(sb.deleteCharAt(sb.length() - 1).toString());
            if (fenceArea.getSpaceId() == null) {
                fenceArea.setSpaceId(SnowflakeIdWorkerUtil.generateLongId());
                twmpMonitorTaskFenceSpaceEf.setShapeId(fenceArea.getSpaceId());
                addList.add(twmpMonitorTaskFenceSpaceEf);
            } else {
                twmpMonitorTaskFenceSpaceEf.setShapeId(fenceArea.getSpaceId());
                editList.add(twmpMonitorTaskFenceSpaceEf);
            }
        }
        addFenceSpaceList(token, addList);
        editFenceSpaceList(token, editList);
    }

    @Override
    public void addFenceSpaceList(String token, List<TwmpMonitorTaskFenceSpaceEf> fenceSpaceList) {
        if (fenceSpaceList == null || fenceSpaceList.size() == 0) {
            return;
        }
        //批量写入
        twmpMonitorTaskFenceSpaceEfMapper.addFenceSpaceList(fenceSpaceList);
    }

    @Override
    public void editFenceSpaceList(String token, List<TwmpMonitorTaskFenceSpaceEf> fenceSpaceList) {
        if (fenceSpaceList == null || fenceSpaceList.size() == 0) {
            return;
        }

        //批量修改
        twmpMonitorTaskFenceSpaceEfMapper.updateFenceSpaceList(fenceSpaceList);
    }

    /**
    *
    * @description:  将被监控人员的监控任务还原到申请监控任务参数变更之前
    * @param: token
    * @param: changeTaskId
    * @param: taskId
    * @return: void
    * @author: shihsh
    * @date: 2019/4/3
    */
    @Override
    public void restoreMonitorTask(String token, Long personId, Long oldTaskId, Long newTaskId) {
        TwmpBsPersonEf person = new TwmpBsPersonEf();
        person.setPersonId(personId);
        person = twmpBsPersonEfMapper.selectByPrimaryKey(person);


        TwmpMonitorTaskChangeEf taskChangeEf = new TwmpMonitorTaskChangeEf();
        taskChangeEf.setTaskChangeId(oldTaskId);
        taskChangeEf.setPersonId(personId);
        taskChangeEf.setPersonName(person.getPersonName());
        taskChangeEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpMonitorTaskChangeEfMapper.updateByPrimaryKeySelective(taskChangeEf);

        TwmpMonitorTaskEf taskOldEf = new TwmpMonitorTaskEf();
        taskOldEf.setTaskId(oldTaskId);
        taskOldEf.setPersonId(personId);
        taskOldEf.setPersonName(person.getPersonName());
        taskOldEf.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(taskOldEf);

        TwmpMonitorTaskEf taskEf = new TwmpMonitorTaskEf();
        taskEf.setTaskId(newTaskId);
        taskEf.setPersonId(personId);
        taskEf.setPersonName(person.getPersonName());
        taskEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpMonitorTaskEfMapper.updateByPrimaryKeySelective(taskEf);
    }

    @Override
    public List<TwmpMonitorTaskEf> getTaskByTime(Date startTime, Date endTime) {
        return twmpMonitorTaskEfMapper.getTaskByTime(startTime, endTime);
    }

    @Override
    public TwmpMonitorTaskEf getTaskByTaskCode(String taskCode) {
        return twmpMonitorTaskEfMapper.getTaskByTaskCode(taskCode);
    }

    @Override
    public List<PersonTaskResultVO> getPersonTaskList(String personName) {
        return twmpMonitorTaskEfMapper.getPersonTaskList(personName);
    }

    @Override
    public PagedItemsVO<TaskResultVO> queryMonitorPerson(String token, TaskQueryVO taskQueryVO) {
        taskQueryVO.setOwnDepartmentId(DepartmentStrToList.getDepartmentIdList(token));
        taskQueryVO.setTaskStatus(TaskStatus.monitoring.value);

        Long total = twmpMonitorTaskEfMapper.countMonitorPerson(taskQueryVO);
        List<TaskResultVO> list = twmpMonitorTaskEfMapper.queryMonitorPerson(taskQueryVO);

        return new PagedItemsVO<>(total, list);
    }

    @Override
    public PagedItemsVO<TaskOutsideRecordResultVO> queryTaskOutSideRecordByPage(TaskOutsideRecordQueryVO taskOutsideRecordQueryVO) {
        Long total = twmpMonitorTaskOutsideRecordEfMapper.countTaskOutsideRecord(taskOutsideRecordQueryVO);
        List<TaskOutsideRecordResultVO> list = twmpMonitorTaskOutsideRecordEfMapper.queryTaskOutsideRecordByPage(taskOutsideRecordQueryVO);
        if(list!=null && list.size()>0){
            for(TaskOutsideRecordResultVO object : list){
                object.setEventTimeStr(DateFormatUtils.dateTimeToString(object.getEventTime()));
            }
        }

        return new PagedItemsVO<>(total, list);
    }

    @Override
    public void updateTaskOutsideRecord(String token, TaskOutSideRecordVO taskOutSideRecordVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);


        TwmpMonitorTaskOutsideRecordEf recordEf = new TwmpMonitorTaskOutsideRecordEf();
        taskOutSideRecordVO.setEventTime(DateFormatUtils.stringToDateTime(taskOutSideRecordVO.getEventTimeStr()));

        ObjectUtils.copy(taskOutSideRecordVO, recordEf);
        recordEf.setDeleted((byte)1);
        if(taskOutSideRecordVO.getTaskOutsideRecordId()==null || taskOutSideRecordVO.getTaskOutsideRecordId()==0){
            //insert
            recordEf.setTaskOutsideRecordId(SnowflakeIdWorkerUtil.generateLongId());
            recordEf.setCreatorId(user.getUserId());
            recordEf.setCreator(user.getUserName());
            recordEf.setCreateTime(new Date());
            twmpMonitorTaskOutsideRecordEfMapper.insertSelective(recordEf);
        }else{
            //update
            recordEf.setUpdaterId(user.getUserId());
            recordEf.setUpdater(user.getUserName());
            recordEf.setUpdateTime(new Date());
            twmpMonitorTaskOutsideRecordEfMapper.updateByPrimaryKeySelective(recordEf);
        }

    }

    @Override
    public void delTaskOutsideRecord(String token, Long taskOutsideRecordId) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        TwmpMonitorTaskOutsideRecordEf recordEf = new TwmpMonitorTaskOutsideRecordEf();
        recordEf.setTaskOutsideRecordId(taskOutsideRecordId);
        recordEf = twmpMonitorTaskOutsideRecordEfMapper.selectByPrimaryKey(recordEf);
        recordEf.setDeleted((byte)0);
        recordEf.setUpdaterId(user.getUserId());
        recordEf.setUpdater(user.getUserName());
        recordEf.setUpdateTime(new Date());
        twmpMonitorTaskOutsideRecordEfMapper.updateByPrimaryKeySelective(recordEf);
    }
}
