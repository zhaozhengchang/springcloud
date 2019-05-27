package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IPersonMonitorService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CreateDate：2019/3/7 11:19 </br>
 * Author：shihsh  </br>
 * Description: 人员监控Controller</br>
 **/

@RestController
@RequestMapping("/monitor")
public class PersonMonitorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 人员监控sercixe
     */
    @Autowired
    private IPersonMonitorService personMonitorService;


    /**
    *
    * @description: 查询被监控人员基本信息详情
    * @param: token
    * @param: personId
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/11
    */
    @PostMapping("/personBasicDetail/{personId}")
    public ResponseContent queryPersonBasicDetail(@RequestHeader String token, @PathVariable Long personId) {
        PersonBasicDetailResultVO personBasicDetail = personMonitorService.getPersonDetail(token, personId);
        return new ResponseContent(ResponseType.SUCCESS, personBasicDetail);
    }
}
