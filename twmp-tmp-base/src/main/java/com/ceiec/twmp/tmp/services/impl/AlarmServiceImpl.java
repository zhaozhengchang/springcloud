package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.AlarmNumberRedis;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.PAARedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.AlarmStatus;
import com.ceiec.twmp.tmp.common.dict.AlarmType;
import com.ceiec.twmp.tmp.common.dict.DisposeType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.entity.TwmpSmAlarmDisposeEf;
import com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf;
import com.ceiec.twmp.tmp.mapper.TwmpMonitorTaskEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmAlarmDisposeEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSmAlarmEfMapper;
import com.ceiec.twmp.tmp.services.IAlarmService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorker;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmAddVO;
import com.ceiec.twmp.tmp.vo.alarm.AlarmNumberVO;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmDisposeVO;
import com.ceiec.twmp.tmp.vo.alarm.query.AlarmQueryVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDetailVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDisposeDetailVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmListVO;
import com.ceiec.twmp.tmp.vo.alarm.result.RedisPAAVO;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-18 10:24
 **/
@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class AlarmServiceImpl implements IAlarmService {


    @Autowired
    private TwmpSmAlarmEfMapper twmpSmAlarmEfMapper;
    @Autowired
    private TwmpSmAlarmDisposeEfMapper twmpSmAlarmDisposeEfMapper;
    @Autowired
    private LocaleMessageSourceService localeMessageService;
    @Autowired
    private TwmpMonitorTaskEfMapper twmpMonitorTaskEfMapper;



    @Override
    public void saveAlarm(TwmpSmAlarmEf twmpSmAlarmEf) {
        twmpSmAlarmEfMapper.insertSelective(twmpSmAlarmEf);
    }

    @Override
    public void updateAlarmHandleUser(Long alarmId, Long userId, String userName) {
        TwmpSmAlarmEf twmpSmAlarmEf = twmpSmAlarmEfMapper.selectByPrimaryKey(alarmId);
        twmpSmAlarmEf.setHandleUser(userName);
        twmpSmAlarmEf.setHandleUserId(userId);
        twmpSmAlarmEf.setAlarmStatus(AlarmStatus.toDispose.value);
        twmpSmAlarmEfMapper.updateByPrimaryKeySelective(twmpSmAlarmEf);
    }

    @Override
    public void updateAlarmHandleUserBatch(Map<Long, List<Long>> userAlarmMap, Map<Long, String> userNameMap) {
        if(userAlarmMap!=null && userAlarmMap.size()>0 && userNameMap!=null && userNameMap.size()>0){
            List<TwmpSmAlarmEf> updateList = new ArrayList<>();
            for(Long userId: userAlarmMap.keySet()){
                String userName = userNameMap.get(userId);
                List<Long> alarmList = userAlarmMap.get(userId);
                if(alarmList!=null && alarmList.size()>0){
                    for(Long alarmId: alarmList){
                        TwmpSmAlarmEf twmpSmAlarmEf = new TwmpSmAlarmEf();
                        twmpSmAlarmEf.setAlarmId(alarmId);
                        twmpSmAlarmEf.setAlarmStatus(AlarmStatus.toDispose.value);
                        twmpSmAlarmEf.setHandleUser(userName);
                        twmpSmAlarmEf.setHandleUserId(userId);
                        updateList.add(twmpSmAlarmEf);
                    }
                }
            }
            twmpSmAlarmEfMapper.updateAlarmHandleUserBatch(updateList);
        }
    }

    @Override
    public List<TwmpSmAlarmEf> getUnDisposedAlarm() {

        return  twmpSmAlarmEfMapper.getUnDisposedAlarm(AlarmStatus.disposed.value);
    }

    @Override
    public List<TwmpSmAlarmEf> getToDisposeAlarmByHandleUserId(Long userId) {
        TwmpSmAlarmEf twmpSmAlarmEf = new TwmpSmAlarmEf();
        twmpSmAlarmEf.setHandleUserId(userId);
        twmpSmAlarmEf.setAlarmStatus(AlarmStatus.toDispose.value);

        return twmpSmAlarmEfMapper.select(twmpSmAlarmEf);
    }

    @Override
    public void updateAlarmStatusByHandUserId(Byte fromAlarmStatus, Byte toAlarmStatus, Long handUserId) {
        twmpSmAlarmEfMapper.updateAlarmStatusByHandUserId(AlarmStatus.toDispose.value, AlarmStatus.undistributed.value, handUserId);
    }

    @Override
    public AlarmNumberVO queryAlarmNumber(String token) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        List<Long> ownDepartmentId = DepartmentStrToList.getDepartmentIdList(token);

        AlarmNumberVO alarmNumberVO = new AlarmNumberVO();
        alarmNumberVO.setToDisposeNum(twmpSmAlarmEfMapper.countToDisposeAlarmNum(redisUserInfoVO.getUserId()));
        alarmNumberVO.setToAllocateNum(twmpSmAlarmEfMapper.countToAllocateNum(ownDepartmentId));
        alarmNumberVO.setDisposedNum(twmpSmAlarmEfMapper.countDisposedAlarmNum(ownDepartmentId));

        return alarmNumberVO;
    }

    @Override
    public List queryToDisposeAlarm(AlarmQueryVO alarmQueryVO) {
        List<AlarmListVO> list = twmpSmAlarmEfMapper.queryAlarm(alarmQueryVO);

        return list;

    }

    @Override
    public PagedItemsVO queryToAllocateAlarm(AlarmQueryVO alarmQueryVO) {
        Long total = twmpSmAlarmEfMapper.countAlarm(alarmQueryVO);
        List<AlarmListVO> list = twmpSmAlarmEfMapper.queryAlarm(alarmQueryVO);

        return new PagedItemsVO<>(total, list);
    }



    @Override
    public PagedItemsVO queryDisposedAlarm(AlarmQueryVO alarmQueryVO) {

        Long total = twmpSmAlarmEfMapper.countAlarm(alarmQueryVO);
        List<AlarmListVO> list = twmpSmAlarmEfMapper.queryAlarm(alarmQueryVO);

        return new PagedItemsVO<>(total, list);
    }

    @Override
    public void claimAlarm(String token, Long alarmId) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        TwmpSmAlarmEf alarm = new TwmpSmAlarmEf();
        alarm.setAlarmId(alarmId);

        alarm = twmpSmAlarmEfMapper.selectByPrimaryKey(alarm);

        if(alarm.getAlarmStatus()!= AlarmStatus.undistributed.value){
            throw new BusinessException(localeMessageService.getMessageLocal("prompt.alarmStatus.distributed", user.getLanguage()));
        }


        alarm.setHandleUserId(user.getUserId());
        alarm.setHandleUser(user.getUserName());
        alarm.setUpdater(user.getUserName());
        alarm.setUpdaterId(user.getUserId());
        alarm.setUpdateTime(new Date());

        twmpSmAlarmEfMapper.updateByPrimaryKeySelective(alarm);

        PAARedis.delPAA(alarmId);
    }

    @Override
    public AlarmDetailVO getAlarmDetail(Long alarmId) {
        AlarmDetailVO alarmDetailVO = twmpSmAlarmEfMapper.getAlarmDetail(alarmId);
        if(alarmDetailVO!=null){
            //TODO 接处警类型的文字描述
            alarmDetailVO.setAlarmTime(DateFormatUtils.stringToDateTime(alarmDetailVO.getAlarmTimeStr()));

            List<AlarmDisposeDetailVO> disposeList = twmpSmAlarmDisposeEfMapper.getAlarmDispose(alarmId);
            if(disposeList!=null && disposeList.size()>0){
                for(AlarmDisposeDetailVO dispose: disposeList){
                    dispose.setDisposeTimeStr(DateFormatUtils.dateTimeToString(dispose.getDisposeTime()));
                }
                alarmDetailVO.setDisposeList(disposeList);
            }
        }

        return alarmDetailVO;
    }

    @Override
    public void createAlarm(String token, AlarmAddVO alarmAddVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        TwmpMonitorTaskEf task = new TwmpMonitorTaskEf();
        task.setTaskId(alarmAddVO.getTaskId());
        task = twmpMonitorTaskEfMapper.selectByPrimaryKey(task);

        RedisDeviceInfoVO redisDeviceInfoVO = DeviceInfoRedis.getDevice(task.getDeviceNumber());


        TwmpSmAlarmEf alarmEf = new TwmpSmAlarmEf();
        alarmEf.setAlarmId(SnowflakeIdWorkerUtil.generateLongId());
        alarmEf.setAlarmNumber(AlarmNumberRedis.generateAlarmNumber());
        alarmEf.setTaskId(redisDeviceInfoVO.getTaskId());
        alarmEf.setTaskLevel(redisDeviceInfoVO.getTaskLevel());
        alarmEf.setDeviceNumber(redisDeviceInfoVO.getDeviceNumber());
        alarmEf.setAlarmType(AlarmType.overFence.value);
        alarmEf.setAlarmTime(new Date());
        alarmEf.setAlarmStatus(AlarmStatus.undistributed.value);
        alarmEf.setDepartmentId(redisDeviceInfoVO.getDepartmentId());
        alarmEf.setCreator(user.getUserName());
        alarmEf.setCreatorId(user.getUserId());
        alarmEf.setCreateTime(new Date());
        alarmEf.setPersonId(redisDeviceInfoVO.getPersonId());
        alarmEf.setDeviceId(redisDeviceInfoVO.getDeviceId());
        alarmEf.setPersonName(redisDeviceInfoVO.getPersonName());
        alarmEf.setRemark(alarmAddVO.getRemark());

        saveAlarm(alarmEf);
    }

    @Override
    public List<AlarmListVO> queryDisposedAlarmByTaskId(Long taskId) {
        List<AlarmListVO> list = twmpSmAlarmEfMapper.queryDisposedAlarmByTaskId(taskId);
        if(list!=null && list.size()>0){
            for(AlarmListVO alarmListVO: list){
                alarmListVO.setAlarmTimeStr(DateFormatUtils.dateTimeToString(alarmListVO.getAlarmTime()));
            }
        }

        return list;
    }

    @Override
    public void disposeAlarm(String token, AlarmDisposeVO alarmDisposeVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        TwmpSmAlarmDisposeEf disposeEf = new TwmpSmAlarmDisposeEf();
        disposeEf.setAlarmDisposeId(SnowflakeIdWorkerUtil.generateLongId());
        disposeEf.setAlarmId(alarmDisposeVO.getAlarmId());
        disposeEf.setComment(alarmDisposeVO.getComment());
        disposeEf.setUserId(user.getUserId());
        disposeEf.setUserName(user.getUserName());
        disposeEf.setDisposeTime(new Date());
        disposeEf.setDisposeType(alarmDisposeVO.getDisposeType());

        twmpSmAlarmDisposeEfMapper.insertSelective(disposeEf);

        //if type is backoff, the alarm will being back
        if(disposeEf.getDisposeType() == DisposeType.backoff.value){
            TwmpSmAlarmEf alarmEf = new TwmpSmAlarmEf();
            alarmEf.setAlarmId(alarmDisposeVO.getAlarmId());
            alarmEf = twmpSmAlarmEfMapper.selectByPrimaryKey(alarmEf);


            alarmEf.setHandleUser(null);
            alarmEf.setHandleUserId(null);
            alarmEf.setUpdaterId(user.getUserId());
            alarmEf.setUpdater(user.getUserName());
            alarmEf.setUpdateTime(new Date());
            if(StringUtils.isNullOrEmpty(alarmEf.getRejectUserId())){
                alarmEf.setRejectUserId(user.getUserId().toString());
            }else{
                alarmEf.setRejectUserId(alarmEf.getRejectUserId()+","+user.getUserId().toString());
            }

            twmpSmAlarmEfMapper.updateByPrimaryKeySelective(alarmEf);

            //add paa cache into redis, so we can auto allocate
            RedisPAAVO redisPAAVO = new RedisPAAVO();
            redisPAAVO.setRejectUserId(alarmEf.getRejectUserId());
            redisPAAVO.setTaskLevel(alarmEf.getTaskLevel());
            redisPAAVO.setAlarmId(alarmEf.getAlarmId());
            redisPAAVO.setAlarmTime(alarmEf.getAlarmTime());
            redisPAAVO.setAlarmType(alarmEf.getAlarmType());
            redisPAAVO.setDepartmentId(alarmEf.getDepartmentId());
            redisPAAVO.setTaskId(alarmEf.getTaskId());
            PAARedis.addPAA(redisPAAVO);
        }

    }
}
