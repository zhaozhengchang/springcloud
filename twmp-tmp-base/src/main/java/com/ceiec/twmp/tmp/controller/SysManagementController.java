
package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IDepartmentService;
import com.ceiec.twmp.tmp.services.IDictService;
import com.ceiec.twmp.tmp.services.IParameterService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentAddResultVO;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.dict.save.DictSaveVo;
import com.ceiec.twmp.tmp.vo.parameter.SysParameterSetVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * CreateDate：2019/1/14 <br/>
 * Author：wenliang <br/>
 * Description: System Manage Controller
 **/

@RestController
@RequestMapping("/systemManagement")
public class SysManagementController {

    @Autowired
    private IParameterService parameterService;

    @Autowired
    private IDictService dictService;



/**
     * logger
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//todo zzc 表已经修改了，不知道这个接口还需要不 2019.3.18
//    @PostMapping("/setParameter")
//    public ResponseContent setParameter(@RequestHeader String token, @Valid @RequestBody SysParameterSetVO sysParameterSetVO, BindingResult bindingResult)throws IOException {
//        if(bindingResult.hasErrors()){
//            String errorMess ="";
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                errorMess = fieldError.getDefaultMessage();
//                logger.warn("setParameter (in system management) is error="+errorMess);
//                break;
//            }
//            return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,errorMess);
//        }else {
//            parameterService.editParameter(sysParameterSetVO);
//            return new ResponseContent(ResponseType.SUCCESS);
//        }
//    }


    /*************************************************************************************************************************************
     ** @Description get dict from database
     ** @param: token
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/6 10:53
     **
     ************************************************************************************************************************************/
    @PostMapping("/dictTypes")
    public ResponseContent getSystemDictTypes() {
        List<DictResultVo> dictResultVoList = dictService.getAllDict();
        return new ResponseContent(ResponseType.SUCCESS, dictResultVoList);
    }


    /*************************************************************************************************************************************
     ** @Description save or update dict
     ** @param:
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/6 10:54
     **
     ************************************************************************************************************************************/
    @PostMapping("/dictData")
    public ResponseContent saveOrUpdateDict(@RequestHeader String token, @Valid @RequestBody DictSaveVo dictSaveVo) {
        Long dictId = dictService.addOrUpdateDict(token, dictSaveVo);
        return new ResponseContent(ResponseType.SUCCESS, dictId);
    }


    @PostMapping("/dictDel/{dictId}")
    public ResponseContent delDict(@RequestHeader String token, @PathVariable Long dictId) {
        dictService.deleteDict(token, dictId);
        return new ResponseContent(ResponseType.SUCCESS);
    }

}
