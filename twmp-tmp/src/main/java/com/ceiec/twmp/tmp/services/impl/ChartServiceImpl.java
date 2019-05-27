package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.mapper.*;
import com.ceiec.twmp.tmp.services.IChartService;
import com.ceiec.twmp.tmp.vo.chart.query.ChartQueryVO;
import com.ceiec.twmp.tmp.vo.chart.result.DeviceUsedReportVO;
import com.ceiec.twmp.tmp.vo.chart.result.PersonAlarmTop10MaxNumResultVO;
import com.ceiec.twmp.tmp.vo.chart.result.PersonAlarmTop10ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 9:34
 * Description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ChartServiceImpl implements IChartService {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TwmpSmDeviceAlarmReportMapper twmpSmDeviceAlarmReportMapper;
    @Autowired
    TwmpSysDepartmentMapper twmpSysDepartmentMapper;
    @Autowired
    TwmpSmDeviceAlarmPersonReportMapper twmpSmDeviceAlarmPersonReportMapper;
    @Autowired
    TwmpBsPersonCriminalEfMapper twmpBsPersonCriminalEfMapper;
    @Autowired
    TwmpSmDeviceUsedReportMapper twmpSmDeviceUsedReportMapper;


    @Override
    public List<TwmpSmDeviceAlarmReport> queryAlarm(String token, ChartQueryVO chartQueryVO) {

        //查询当前组织机构及其下级组织机构id,（不用做权限控制，登录时已经生成权限树，不会选到当前用户上级的组织机构）
        List<Long> departmentIdlist = listAllDepartmentIds(chartQueryVO.getDepartmentId());
        chartQueryVO.setDepartmentIdList(departmentIdlist);

        return twmpSmDeviceAlarmReportMapper.queryAlarm(chartQueryVO);
    }

    @Override
    public List<TwmpSmDeviceAlarmReport> queryalarmType(String token, ChartQueryVO chartQueryVO) {
        //查询当前组织机构及其下级组织机构id,（不用做权限控制，登录时已经生成权限树，不会选到当前用户上级的组织机构）
        List<Long> departmentIdlist = listAllDepartmentIds(chartQueryVO.getDepartmentId());
        chartQueryVO.setDepartmentIdList(departmentIdlist);

        return twmpSmDeviceAlarmReportMapper.queryalarmType(chartQueryVO);
    }

    @Override
    public PersonAlarmTop10MaxNumResultVO personAlarmCountTOP10(String token, ChartQueryVO chartQueryVO) {
        //查询当前组织机构及其下级组织机构id,（不用做权限控制，登录时已经生成权限树，不会选到当前用户上级的组织机构）
        List<Long> departmentIdlist = listAllDepartmentIds(chartQueryVO.getDepartmentId());
        chartQueryVO.setDepartmentIdList(departmentIdlist);

        List<PersonAlarmTop10ResultVO> alarmTop10ResultVOList = twmpSmDeviceAlarmPersonReportMapper.personAlarmCountTOP10(chartQueryVO);

        //获取maxNum
        long maxNum = 0;
        for (PersonAlarmTop10ResultVO o : alarmTop10ResultVOList) {
            maxNum = maxNum < o.getNum() ? o.getNum() : maxNum;
        }
        return new PersonAlarmTop10MaxNumResultVO(maxNum, alarmTop10ResultVOList);
    }

    /**
     * @description:设备使用率查询
     * @Param: token
     * @Param: chartQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.DeviceUsedReportResultVO>
     * @author: zhaozhengchang
     * @date: 2019/4/23 15:56
     */
    @Override
    public List<DeviceUsedReportVO> deviceUsageRate(String token, ChartQueryVO chartQueryVO) {
        //查询当前组织机构及其下级组织机构id,（不用做权限控制，登录时已经生成权限树，不会选到当前用户上级的组织机构）
        List<Long> departmentIdlist = listAllDepartmentIds(chartQueryVO.getDepartmentId());
        chartQueryVO.setDepartmentIdList(departmentIdlist);

        List<DeviceUsedReportVO> deviceUsedReport =  twmpSmDeviceUsedReportMapper.deviceUsageRate(chartQueryVO);
        return deviceUsedReport;
    }


    /**
     * 查询当前组织机构及其下级组织机构id
     *
     * @param departmentId
     * @return
     */
    private List<Long> listAllDepartmentIds(Long departmentId) {
        List tempList = new ArrayList();
        List resultList = new ArrayList();
        tempList.add(departmentId);
        resultList.add(departmentId);
        List<Long> list = null;
        do {
            list = twmpSysDepartmentMapper.listDepartmentIdsByParentIds(tempList);
            resultList.addAll(list);
            tempList = list;
        } while (list != null && list.size() > 0);

        return resultList;
    }
}
