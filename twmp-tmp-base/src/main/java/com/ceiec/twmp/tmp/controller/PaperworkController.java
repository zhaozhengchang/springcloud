package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpSysPaperwork;
import com.ceiec.twmp.tmp.services.IPaperworkService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.paperwork.add.PaperworkAddVO;
import com.ceiec.twmp.tmp.vo.paperwork.query.PaperworkQueryVO;
import com.ceiec.twmp.tmp.vo.paperwork.result.PaperworkResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-06 19:53
 * Description:
 **/
@RestController
@RequestMapping("/systemManagement")
public class PaperworkController {

    @Autowired
    private IPaperworkService paperworkService;

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/paperworkData")
    public ResponseContent addOrEditPaperwork(@RequestHeader String token, @Valid @RequestBody PaperworkAddVO paperworkAddVO) {
        PaperworkResultVO paperworkResultVO;
        try {
            paperworkResultVO =  paperworkService.addOrEditPaperwork(token,paperworkAddVO);
            //处理service自定义错误
            if(ResponseType.PAPERWORK_NAME_EXISTED.getCode().equals(paperworkResultVO.getResponseType().getCode())){
                return new ResponseContent(ResponseType.PAPERWORK_NAME_EXISTED);
            }
        } catch (Exception e) {
            logger.error("update or edit paperwork failed.",e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }

    @PostMapping("/paperworkDel")
    public ResponseContent deletePaperwork(@RequestHeader String token,  @RequestParam Long paperworkId) {

        try {
            paperworkService.deletePaperwork(token , paperworkId);
        } catch (Exception e) {
            logger.error("delete paperwork failed.",e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }

    @PostMapping("/paperworkQuery")
    public ResponseContent listPaperwork(@RequestHeader String token,  @RequestBody PaperworkQueryVO paperworkQueryVO) {
        PagedItemsVO<TwmpSysPaperwork> list = null;
        try {
             list = paperworkService.listPaperwork(token , paperworkQueryVO);
            System.out.println("****************21" );
             list.setPageSize(paperworkQueryVO.getPageSize());
             list.setPageIndex(paperworkQueryVO.getPageNo());
        } catch (Exception e) {
            logger.error("query paperwork failed.",e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS,list);

    }
}
