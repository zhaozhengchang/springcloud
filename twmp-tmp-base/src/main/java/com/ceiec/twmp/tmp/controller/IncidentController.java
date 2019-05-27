package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IIncidentService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.incident.push.PushAlarmVO;
import com.ceiec.twmp.tmp.vo.incident.result.IncidentTypeVO;
import com.ceiec.twmp.tmp.vo.incident.result.PushAlarmResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @title: IncidentController </br>
 * @createDate: 2019/4/18 16:00 </br>
 * @author: shihsh  </br>
 * @description: 警情处理 </br>
 * @version: V1.0
 **/

@RestController
@RequestMapping("/alarm")
public class IncidentController {

    @Autowired
    private IIncidentService incidentService;

    /**
    *
    * @description: 获取接处警警情类型
    * @param: token
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/4/19
    */
    @PostMapping("/incidentTypeList")
    public ResponseContent getIncidentTypeList(@RequestHeader String token) {
        List<IncidentTypeVO> incidentTypes = incidentService.getIncidentTypes();
        return new ResponseContent(ResponseType.SUCCESS, incidentTypes);
    }

    @PostMapping("/pushAlarm")
    public ResponseContent pushAlarm(@RequestHeader String token, @RequestBody PushAlarmVO pushAlarmVO) {
        PushAlarmResultVO resultVO = incidentService.pushAlarmToCAD(token, pushAlarmVO);
        if (resultVO.getSuccess()) {
            return new ResponseContent(ResponseType.SUCCESS, resultVO);
        } else {
            return new ResponseContent(ResponseType.FAIL, resultVO);
        }
    }

}
