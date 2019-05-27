package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpGisAddress;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.services.IGisService;
import com.ceiec.twmp.tmp.services.IMonitorService;
import com.ceiec.twmp.tmp.services.IMonitorTaskService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.gps.GpsResultVO;
import com.ceiec.twmp.tmp.vo.monitor.query.TaskQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.TaskResultVO;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonTaskResultVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutSideRecordVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordQueryVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordResultVO;
import com.ceiec.twmp.tmp.vo.track.TrackQueryVO;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: monitor controller
 * @create 2019-03-29 14:50
 **/
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private IMonitorService monitorService;

    @Autowired
    private IMonitorTaskService monitorTaskService;


    @Autowired
    private IGisService gisService;

    /*************************************************************************************************************************************
     ** @Description query gps track
     ** @param: token
     ** @param: trackQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/29 15:17
     **
     ************************************************************************************************************************************/
    @PostMapping("/queryTrack")
    public ResponseContent queryAlarm(@RequestHeader String token, @Valid @RequestBody TrackQueryVO trackQueryVO) throws DocumentException, IOException {
        TwmpMonitorTaskEf task = monitorTaskService.getTaskByTaskCode(trackQueryVO.getTaskCode());
        if(task==null){
            return new ResponseContent(ResponseType.SUCCESS , null);
        }else{
            GpsResultVO gpsResultVO = monitorService.queryGpsInfo(task.getDeviceNumber(), task.getTaskCode(), trackQueryVO.getDate());
            return new ResponseContent(ResponseType.SUCCESS , gpsResultVO);
        }
    }

    /*************************************************************************************************************************************
     ** @Description import gps track
     ** @param: token
     ** @param: file
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/29 15:18
     **
     ************************************************************************************************************************************/
    @PostMapping("/importTrack")
    public ResponseContent importTrack(@RequestHeader String token, @RequestParam("file") MultipartFile file)
            throws IOException, DocumentException {
        GpsResultVO gpsResultVO = monitorService.importGpsResult(file);
        return new ResponseContent(ResponseType.SUCCESS , gpsResultVO);
    }

    /*************************************************************************************************************************************
     ** @Description export gps track
     ** @param: token
     ** @param: response
     ** @param: trackQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/29 15:20
     **
     ************************************************************************************************************************************/
    @PostMapping("/exportTrack")
    public ResponseContent exportTrack(@RequestHeader String token, HttpServletResponse response, @Valid @RequestBody TrackQueryVO trackQueryVO)
            throws IOException, TransformerException {
        TwmpMonitorTaskEf task = monitorTaskService.getTaskByTaskCode(trackQueryVO.getTaskCode());
        if(task!=null){
            monitorService.exportGpsInfo(response, task.getDeviceNumber(), task.getTaskCode(), trackQueryVO.getDate());
        }
        return new ResponseContent(ResponseType.SUCCESS , null);
    }

    /*************************************************************************************************************************************
     ** @Description query task list by personName
     ** @param: token
     ** @param: personName
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/29 15:40
     **
     ************************************************************************************************************************************/
    @PostMapping("/queryPersonTaskList/{personName}")
    public ResponseContent queryPersonTaskList(@RequestHeader String token, @PathVariable String personName) {
        List<PersonTaskResultVO> result = monitorTaskService.getPersonTaskList(personName);
        if(result!=null && result.size()>0){
            for(PersonTaskResultVO personTaskResultVO: result){
                personTaskResultVO.setStartTimeStr(DateFormatUtils.dateTimeToString(personTaskResultVO.getStartTime()));
                personTaskResultVO.setEndTimeStr(DateFormatUtils.dateToString(personTaskResultVO.getEndTime()));
            }
        }

        return new ResponseContent(ResponseType.SUCCESS , result);
    }

    /*************************************************************************************************************************************
     ** @Description query address by address name
     ** @param: token
     ** @param: addressName
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/1 9:54
     **
     ************************************************************************************************************************************/
    @PostMapping("/queryAddress/{addressName}")
    public ResponseContent queryAddress(@RequestHeader String token, @PathVariable String addressName) {
        List<TwmpGisAddress> list = gisService.queryAddressByName(addressName);
        return new ResponseContent(ResponseType.SUCCESS , list);
    }


    /*************************************************************************************************************************************
     ** @Description query monitor task/person
     ** @param: token
     ** @param: monitorPersonQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 16:12
     **
     ************************************************************************************************************************************/
    @PostMapping("/taskSearch")
    public ResponseContent personSearch(@RequestHeader String token, @RequestBody TaskQueryVO taskQueryVO) {
        PagedItemsVO<TaskResultVO> result = monitorTaskService.queryMonitorPerson(token, taskQueryVO);

        return new ResponseContent(ResponseType.SUCCESS, result);
    }


    /*************************************************************************************************************************************
     ** @Description query task outside record
     ** @param: token
     ** @param: taskQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/23 11:04
     **
     ************************************************************************************************************************************/
    @PostMapping("/taskOutsideRecord")
    public ResponseContent taskOutsideRecord(@RequestHeader String token, @RequestBody TaskOutsideRecordQueryVO taskOutsideRecordQueryVO) {
        PagedItemsVO<TaskOutsideRecordResultVO> result = monitorTaskService.queryTaskOutSideRecordByPage(taskOutsideRecordQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, result);
    }

    /*************************************************************************************************************************************
     ** @Description add or update task outside record
     ** @param: token
     ** @param: taskOutsideRecordQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/23 11:37
     **
     ************************************************************************************************************************************/
    @PostMapping("/taskOutsideRecordData")
    public ResponseContent taskOutsideRecordData(@RequestHeader String token, @RequestBody TaskOutSideRecordVO taskOutsideRecordVO) {
        monitorTaskService.updateTaskOutsideRecord(token, taskOutsideRecordVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description del task outside record
     ** @param: token
     ** @param: taskOutsideRecordId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/23 11:49
     **
     ************************************************************************************************************************************/
    @PostMapping("/delTaskOutsideRecord/{taskOutsideRecordId}")
    public ResponseContent delTaskOutsideRecord(@RequestHeader String token, @PathVariable Long taskOutsideRecordId) {
        monitorTaskService.delTaskOutsideRecord(token, taskOutsideRecordId);
        return new ResponseContent(ResponseType.SUCCESS);
    }



}
