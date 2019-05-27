package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.incident.push.PushAlarmVO;
import com.ceiec.twmp.tmp.vo.incident.result.IncidentTypeVO;
import com.ceiec.twmp.tmp.vo.incident.result.PushAlarmResultVO;

import java.util.List;

/**
 * @title: IIncidentService </br>
 * @createDate: 2019/4/18 16:02 </br>
 * @author: shihsh  </br>
 * @description: 警情处理 </br>
 * @version: V1.0
 **/


public interface IIncidentService {
    List<IncidentTypeVO> getIncidentTypes();

    PushAlarmResultVO pushAlarmToCAD(String token, PushAlarmVO pushAlarmVO);
}
