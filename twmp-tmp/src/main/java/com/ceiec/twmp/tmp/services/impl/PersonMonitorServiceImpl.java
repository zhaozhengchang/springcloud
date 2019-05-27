package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonCriminalEf;
import com.ceiec.twmp.tmp.mapper.TwmpBsPersonCriminalEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpBsPersonEfMapper;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IPersonMonitorService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonCriminalResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * CreateDate：2019/3/7 11:46 </br>
 * Author：shihsh  </br>
 * Description: 人员监控服务 </br>
 **/


@Service
@Transactional(rollbackFor = Exception.class)
public class PersonMonitorServiceImpl implements IPersonMonitorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 被监控人员mapper
     */
    @Autowired
    private TwmpBsPersonEfMapper twmpBsPersonEfMapper;


    @Autowired
    private TwmpBsPersonCriminalEfMapper twmpBsPersonCriminalEfMapper;

    /**
    *
    * @description: 查询被监控人员详情
    * @param: token
    * @param: personId
    * @return: com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public PersonBasicDetailResultVO getPersonDetail(String token, Long personId) {
        // 组织机构权限
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String departmentIds = redisUserInfoVO.getOwnDepartmentId();
        String[] departmentIdArr= departmentIds.split(",");
        List<Long> ownDepartmentIds = new ArrayList<>();
        for (String id : departmentIdArr) {
            ownDepartmentIds.add(Long.valueOf(id));
        }
        PersonQueryVO personQueryVO = new PersonQueryVO();
        personQueryVO.setDepartmentIds(ownDepartmentIds);
        personQueryVO.setPersonId(personId);

        PersonBasicDetailResultVO personDetail = twmpBsPersonEfMapper.getPersonDetail(personQueryVO);

        // 经纬度先设个默认值
        personDetail.setLongtitude("0");
        personDetail.setLatitude("0");
        if (personDetail.getDeviceId() != null) {
            try {
                String deviceNum = personDetail.getDeviceId().toString();
                RedisDeviceInfoVO device = DeviceInfoRedis.getDevice(deviceNum);
                personDetail.setLongtitude(device.getLongitude());
                personDetail.setLatitude(device.getLatitude());
            }catch (Exception e) {
                logger.error("Failed to get location from Redis");
            }
        }

        List<TwmpBsPersonCriminalEf> criminalList = twmpBsPersonCriminalEfMapper.getCriminalListByPersonId(personId.toString());
        List<PersonCriminalResultVO> criminalResultVOList = new ArrayList<>();
        for (TwmpBsPersonCriminalEf criminalEf : criminalList) {
            PersonCriminalResultVO criminalResultVO = new PersonCriminalResultVO();
            ObjectUtils.copy(criminalEf, criminalResultVO);
            criminalResultVOList.add(criminalResultVO);

        }
        personDetail.setCriminalList(criminalResultVOList);
        return personDetail;
    }
}
