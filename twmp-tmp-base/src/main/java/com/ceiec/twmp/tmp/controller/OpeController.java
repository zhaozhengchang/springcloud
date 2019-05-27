package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IOpeService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.OpeUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ding
 * @version V1.0
 * @Description: controller for operation
 * @create 2019-04-11 9:29
 **/
@RestController
@RequestMapping("/ope")
public class OpeController {

    @Autowired
    private IOpeService opeService;


    /*************************************************************************************************************************************
     ** @Description query task list to install, to dismantle, to change
     ** @param: token
     ** @param: opeQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/11 11:57
     **
     ************************************************************************************************************************************/
    @PostMapping("/monitorTaskList")
    public ResponseContent monitorTaskList(@RequestHeader String token, @RequestBody OpeToQueryVO opeQueryVO) {
        return new ResponseContent(ResponseType.SUCCESS, opeService.queryToOpeList(token, opeQueryVO));
    }



    /*************************************************************************************************************************************
     ** @Description install device
     ** @param: token
     ** @param: opeQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/11 11:59
     **
     ************************************************************************************************************************************/
    @PostMapping("/installFinish")
    public ResponseContent installFinish(@RequestHeader String token, @RequestBody OpeUpdateVO opeUpdateVO) {
        opeService.installFinish(token, opeUpdateVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description dismantle device
     ** @param: token
     ** @param: opeUpdateVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/12 14:56
     **
     ************************************************************************************************************************************/
    @PostMapping("/dismantleFinish")
    public ResponseContent dismantleFinish(@RequestHeader String token, @RequestBody OpeUpdateVO opeUpdateVO) {
        opeService.dismantleFinish(token, opeUpdateVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description change device finish
     ** @param: token
     ** @param: opeUpdateVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/12 14:55
     **
     ************************************************************************************************************************************/
    @PostMapping("/changeFinish")
    public ResponseContent changeFinish(@RequestHeader String token, @RequestBody OpeUpdateVO opeUpdateVO) {
        opeService.changeFinish(token, opeUpdateVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description query ope finish result list
     ** @param: token
     ** @param: opeQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/15 14:34
     **
     ************************************************************************************************************************************/
    @PostMapping("/finishList")
    public ResponseContent finishList(@RequestHeader String token, @RequestBody OpeFinishQueryVO opeQueryVO) {
        return new ResponseContent(ResponseType.SUCCESS, opeService.queryFinishOpeList(token, opeQueryVO));
    }

    /*************************************************************************************************************************************
     ** @Description query ope finish detail info
     ** @param: token
     ** @param: opeQueryVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/15 15:08
     **
     ************************************************************************************************************************************/
    @PostMapping("/finishDetail")
    public ResponseContent finishDetail(@RequestHeader String token, @RequestBody OpeFinishDetailQueryVO opeQueryVO) {
        return new ResponseContent(ResponseType.SUCCESS, opeService.queryFinishDetail(opeQueryVO));
    }




}
