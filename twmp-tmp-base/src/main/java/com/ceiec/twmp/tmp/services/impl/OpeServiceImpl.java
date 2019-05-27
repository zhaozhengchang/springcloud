package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.DepartmentRedis;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.LifecycleEventType;
import com.ceiec.twmp.tmp.common.dict.OpeStatus;
import com.ceiec.twmp.tmp.common.dict.OpeType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.*;
import com.ceiec.twmp.tmp.mapper.*;
import com.ceiec.twmp.tmp.services.IDeviceService;
import com.ceiec.twmp.tmp.services.IOpeService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.fence.FenceShapeVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishDetailVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishResultVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeToResultVO;
import com.ceiec.twmp.tmp.vo.ope.OpeUpdateVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: ope service implements
 * @create 2019-04-11 9:30
 **/
@Service
public class OpeServiceImpl implements IOpeService {

    @Autowired
    private TwmpOpeInstallMapper twmpOpeInstallMapper;
    @Autowired
    private TwmpOpeDismantleMapper twmpOpeDismantleMapper;
    @Autowired
    private TwmpOpeChangeMapper twmpOpeChangeMapper;
    @Autowired
    private TwmpBsPersonEfMapper twmpBsPersonEfMapper;
    @Autowired
    private TwmpDevDeviceMapper twmpDevDeviceMapper;
    @Autowired
    private TwmpMonitorTaskEfMapper twmpMonitorTaskEfMapper;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private TwmpMonitorTaskFenceEfMapper twmpMonitorTaskFenceEfMapper;
    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @Override
    public List<OpeToResultVO> queryToOpeList(String token, OpeToQueryVO opeQueryVO) {
        List<Long> departmentIds = DepartmentStrToList.getDepartmentIdList(token);
        opeQueryVO.setOwnDepartmentId(departmentIds);


        List<OpeToResultVO> list = new ArrayList<>();
        if(opeQueryVO.getOpeType() == OpeType.toInstall.value){
            list = twmpOpeInstallMapper.queryToInstallByPage(opeQueryVO);

        }else if(opeQueryVO.getOpeType() == OpeType.toDismantle.value){
            list = twmpOpeDismantleMapper.queryToDismantleByPage(opeQueryVO);

        }else if(opeQueryVO.getOpeType() == OpeType.toChange.value){
            list = twmpOpeChangeMapper.queryToChangeByPage(opeQueryVO);

        }

        if(list!=null && list.size()>0){
            for(OpeToResultVO opeToResultVO: list){
                opeToResultVO.setTaskEndTimeStr(DateFormatUtils.dateTimeToString(opeToResultVO.getTaskEndTime()));
                opeToResultVO.setTaskBeginTimeStr(DateFormatUtils.dateTimeToString(opeToResultVO.getTaskBeginTime()));
            }
        }

        return list;
    }

    @Override
    public void installFinish(String token, OpeUpdateVO opeUpdateVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        /**
         * update install
         */
        TwmpOpeInstall twmpOpeInstall = new TwmpOpeInstall();
        twmpOpeInstall.setInstallId(opeUpdateVO.getOpeId());
        twmpOpeInstall = twmpOpeInstallMapper.selectByPrimaryKey(twmpOpeInstall);
        TwmpBsPersonEf person = new TwmpBsPersonEf();
        person.setPersonId(person.getPersonId());
        person = twmpBsPersonEfMapper.selectByPrimaryKey(person);
        twmpOpeInstall.setPersonIdCard(person.getPersonIdCard());
        twmpOpeInstall.setPersonNumber(person.getPersonNumber());
        twmpOpeInstall.setPersonDepartmentId(person.getDepartmentId());
        twmpOpeInstall.setPersonDepartmentName(DepartmentRedis.getDepartmentById(person.getDepartmentId()).getDepartmentName());
        twmpOpeInstall.setPersonUrl(person.getPersonUrl());
        twmpOpeInstall.setPersonBirthDate(person.getBirthDate());
        twmpOpeInstall.setPersonGender(person.getGender());
        twmpOpeInstall.setPersonMaritalStatus(person.getMaritalStatus());
        twmpOpeInstall.setPersonCareer(person.getCareer());
        twmpOpeInstall.setPersonPhone(person.getPhone());
        twmpOpeInstall.setPersonEmail(person.getEmail());
        twmpOpeInstall.setPersonAddress(person.getAddress());
        twmpOpeInstall.setPersonComment(person.getComment());
        twmpOpeInstall.setPersonFormerName(person.getPersonFormerName());
        twmpOpeInstall.setFileUrl(opeUpdateVO.getFileUrl());
        twmpOpeInstall.setOps(user.getUserName());
        twmpOpeInstall.setOpsId(user.getUserId());
        twmpOpeInstall.setInstallTime(new Date());
        TwmpDevDevice devDevice = new TwmpDevDevice();
        devDevice.setDeviceId(opeUpdateVO.getDeviceId());
        devDevice = twmpDevDeviceMapper.selectByPrimaryKey(devDevice);
        if(devDevice.getOpeStatus() != OpeStatus.initial.value){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.ope.failed", user.getLanguage()));
        }
        devDevice.setOpeStatus(OpeStatus.installed.value);
        twmpDevDeviceMapper.updateByPrimaryKeySelective(devDevice);
        twmpOpeInstall.setDeviceId(devDevice.getDeviceId());
        twmpOpeInstall.setDeviceNumber(devDevice.getDeviceNumber());
        twmpOpeInstallMapper.updateByPrimaryKeySelective(twmpOpeInstall);


        //insert device life cycle
        deviceService.insertDeviceLifecycle(token, devDevice, LifecycleEventType.install);

        /**
         * save device info
         */
        RedisDeviceInfoVO redisDeviceInfoVO = new RedisDeviceInfoVO();
        redisDeviceInfoVO.setDeviceId(devDevice.getDeviceId());
        redisDeviceInfoVO.setDeviceNumber(devDevice.getDeviceNumber());
        redisDeviceInfoVO.setPersonId(person.getPersonId());
        TwmpMonitorTaskEf task = new TwmpMonitorTaskEf();
        task.setTaskId(twmpOpeInstall.getTaskId());
        task = twmpMonitorTaskEfMapper.selectByPrimaryKey(task);
        List<FenceShapeVO> fenceShapeVOS = twmpMonitorTaskFenceEfMapper.selectEnableFenceSpaceByTaskId(task.getTaskId());
        redisDeviceInfoVO.setFenceShapeList(fenceShapeVOS);
        redisDeviceInfoVO.setTaskId(twmpOpeInstall.getTaskId());
        redisDeviceInfoVO.setTaskCode(twmpOpeInstall.getTaskCode());
        redisDeviceInfoVO.setTaskLevel(task.getTaskLevel());
        redisDeviceInfoVO.setOpeStatus(OpeStatus.installed.value);
        redisDeviceInfoVO.setTaskStatus(task.getTaskStatus());
        redisDeviceInfoVO.setDepartmentId(devDevice.getDepartmentId());
        redisDeviceInfoVO.setPersonName(person.getPersonName());
        DeviceInfoRedis.saveDevice(redisDeviceInfoVO);
    }

    @Override
    public void dismantleFinish(String token, OpeUpdateVO opeUpdateVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        /**
         * update dismantle
         */
        TwmpOpeDismantle object = new TwmpOpeDismantle();
        object.setDismantleId(opeUpdateVO.getOpeId());
        object = twmpOpeDismantleMapper.selectByPrimaryKey(object);
        TwmpBsPersonEf person = new TwmpBsPersonEf();
        person.setPersonId(person.getPersonId());
        person = twmpBsPersonEfMapper.selectByPrimaryKey(person);
        object.setPersonIdCard(person.getPersonIdCard());
        object.setPersonNumber(person.getPersonNumber());
        object.setPersonDepartmentId(person.getDepartmentId());
        object.setPersonDepartmentName(DepartmentRedis.getDepartmentById(person.getDepartmentId()).getDepartmentName());
        object.setPersonUrl(person.getPersonUrl());
        object.setPersonBirthDate(person.getBirthDate());
        object.setPersonGender(person.getGender());
        object.setPersonMaritalStatus(person.getMaritalStatus());
        object.setPersonCareer(person.getCareer());
        object.setPersonPhone(person.getPhone());
        object.setPersonEmail(person.getEmail());
        object.setPersonAddress(person.getAddress());
        object.setPersonComment(person.getComment());
        object.setPersonFormerName(person.getPersonFormerName());
        object.setOps(user.getUserName());
        object.setOpsId(user.getUserId());
        object.setDismantleTime(new Date());
        twmpOpeDismantleMapper.updateByPrimaryKeySelective(object);

        //insert device life cycle
        TwmpDevDevice devDevice = new TwmpDevDevice();
        devDevice.setDeviceId(opeUpdateVO.getDeviceId());
        devDevice = twmpDevDeviceMapper.selectByPrimaryKey(devDevice);

        if(devDevice.getOpeStatus() != OpeStatus.installed.value){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.ope.failed", user.getLanguage()));
        }
        devDevice.setOpeStatus(opeUpdateVO.getOpeStatus());
        twmpDevDeviceMapper.updateByPrimaryKeySelective(devDevice);

        deviceService.insertDeviceLifecycle(token, devDevice, LifecycleEventType.dismantle);

        /**
         * save device info
         */
        RedisDeviceInfoVO redisDeviceInfoVO = DeviceInfoRedis.getDevice(devDevice.getDeviceNumber());
        redisDeviceInfoVO.setOpeStatus(opeUpdateVO.getOpeStatus());
        redisDeviceInfoVO.setPersonName(person.getPersonName());
        DeviceInfoRedis.saveDevice(redisDeviceInfoVO);
    }

    @Override
    public void changeFinish(String token, OpeUpdateVO opeUpdateVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        /**
         * update change
         */
        TwmpOpeChange object = new TwmpOpeChange();
        object.setChangeId(opeUpdateVO.getOpeId());
        object = twmpOpeChangeMapper.selectByPrimaryKey(object);
        TwmpBsPersonEf person = new TwmpBsPersonEf();
        person.setPersonId(person.getPersonId());
        person = twmpBsPersonEfMapper.selectByPrimaryKey(person);
        object.setPersonIdCard(person.getPersonIdCard());
        object.setPersonNumber(person.getPersonNumber());
        object.setPersonDepartmentId(person.getDepartmentId());
        object.setPersonDepartmentName(DepartmentRedis.getDepartmentById(person.getDepartmentId()).getDepartmentName());
        object.setPersonUrl(person.getPersonUrl());
        object.setPersonBirthDate(person.getBirthDate());
        object.setPersonGender(person.getGender());
        object.setPersonMaritalStatus(person.getMaritalStatus());
        object.setPersonCareer(person.getCareer());
        object.setPersonPhone(person.getPhone());
        object.setPersonEmail(person.getEmail());
        object.setPersonAddress(person.getAddress());
        object.setPersonComment(person.getComment());
        object.setPersonFormerName(person.getPersonFormerName());
        object.setOps(user.getUserName());
        object.setOpsId(user.getUserId());
        object.setChangeTime(new Date());
        TwmpDevDevice devDevice = new TwmpDevDevice();
        devDevice.setDeviceId(opeUpdateVO.getDeviceId());
        devDevice = twmpDevDeviceMapper.selectByPrimaryKey(devDevice);
        object.setDeviceId(devDevice.getDeviceId());
        object.setDeviceNumber(devDevice.getDeviceNumber());

        if(devDevice.getOpeStatus() != OpeStatus.initial.value){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.ope.failed", user.getLanguage()));
        }
        devDevice.setOpeStatus(OpeStatus.installed.value);
        twmpDevDeviceMapper.updateByPrimaryKeySelective(devDevice);


        twmpOpeChangeMapper.updateByPrimaryKeySelective(object);


        //insert device life cycle
        TwmpDevDevice oldDevice = new TwmpDevDevice();
        oldDevice.setDeviceId(object.getOldDeviceId());
        oldDevice = twmpDevDeviceMapper.selectByPrimaryKey(oldDevice);

        if(oldDevice.getOpeStatus() != OpeStatus.installed.value){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.ope.failed", user.getLanguage()));
        }
        oldDevice.setOpeStatus(OpeStatus.initial.value);
        twmpDevDeviceMapper.updateByPrimaryKeySelective(oldDevice);

        deviceService.insertDeviceLifecycle(token, devDevice, LifecycleEventType.install);
        deviceService.insertDeviceLifecycle(token, oldDevice, LifecycleEventType.dismantle);




        /**
         * save device info
         */
        RedisDeviceInfoVO redisDeviceInfoVO = new RedisDeviceInfoVO();
        redisDeviceInfoVO.setDeviceId(devDevice.getDeviceId());
        redisDeviceInfoVO.setDeviceNumber(devDevice.getDeviceNumber());
        redisDeviceInfoVO.setPersonId(person.getPersonId());
        TwmpMonitorTaskEf task = new TwmpMonitorTaskEf();
        task.setTaskId(object.getTaskId());
        task = twmpMonitorTaskEfMapper.selectByPrimaryKey(task);
        List<FenceShapeVO> fenceShapeVOS = twmpMonitorTaskFenceEfMapper.selectEnableFenceSpaceByTaskId(task.getTaskId());
        redisDeviceInfoVO.setFenceShapeList(fenceShapeVOS);
        redisDeviceInfoVO.setTaskId(object.getTaskId());
        redisDeviceInfoVO.setTaskCode(object.getTaskCode());
        redisDeviceInfoVO.setTaskLevel(task.getTaskLevel());
        redisDeviceInfoVO.setOpeStatus(OpeStatus.installed.value);
        redisDeviceInfoVO.setTaskStatus(task.getTaskStatus());
        redisDeviceInfoVO.setDepartmentId(devDevice.getDepartmentId());
        redisDeviceInfoVO.setPersonName(person.getPersonName());
        DeviceInfoRedis.saveDevice(redisDeviceInfoVO);


        RedisDeviceInfoVO oldRedisDeviceInfoVO = DeviceInfoRedis.getDevice(oldDevice.getDeviceNumber());
        oldRedisDeviceInfoVO.setOpeStatus(OpeStatus.initial.value);
        DeviceInfoRedis.saveDevice(oldRedisDeviceInfoVO);

    }

    @Override
    public List<OpeFinishResultVO> queryFinishOpeList(String token, OpeFinishQueryVO opeFinishQueryVO) {
        opeFinishQueryVO.setBeginTime(DateFormatUtils.stringToDateTime(opeFinishQueryVO.getBeginTimeStr()));
        opeFinishQueryVO.setEndTime(DateFormatUtils.stringToDateTime(opeFinishQueryVO.getEndTimeStr()));
        List<Long> departmentIds = DepartmentStrToList.getDepartmentIdList(token);
        opeFinishQueryVO.setOwnDepartmentId(departmentIds);


        List<OpeFinishResultVO> list = new ArrayList<>();
        if(opeFinishQueryVO.getOpeType() == OpeType.toInstall.value){
            list = twmpOpeInstallMapper.queryInstalledByPage(opeFinishQueryVO);

        }else if(opeFinishQueryVO.getOpeType() == OpeType.toDismantle.value){
            list = twmpOpeDismantleMapper.queryDismantledByPage(opeFinishQueryVO);

        }else if(opeFinishQueryVO.getOpeType() == OpeType.toChange.value){
            list = twmpOpeChangeMapper.queryChangedByPage(opeFinishQueryVO);

        }

        if(list!=null && list.size()>0){
            for(OpeFinishResultVO opeFinishResultVO: list){
                opeFinishResultVO.setRecordTimeStr(DateFormatUtils.dateTimeToString(opeFinishResultVO.getRecordTime()));
            }
        }

        return list;
    }

    @Override
    public OpeFinishDetailVO queryFinishDetail(OpeFinishDetailQueryVO opeFinishDetailQueryVO) {
        OpeFinishDetailVO result = new OpeFinishDetailVO();
        if(opeFinishDetailQueryVO.getOpeType() == OpeType.toInstall.value){
            result = twmpOpeInstallMapper.queryInstalledDetail(opeFinishDetailQueryVO);

        }else if(opeFinishDetailQueryVO.getOpeType() == OpeType.toDismantle.value){
            result = twmpOpeDismantleMapper.queryDismantledDetail(opeFinishDetailQueryVO);

        }else if(opeFinishDetailQueryVO.getOpeType() == OpeType.toChange.value){
            result = twmpOpeChangeMapper.queryChangedDetail(opeFinishDetailQueryVO);
        }

        return result;
    }
}
