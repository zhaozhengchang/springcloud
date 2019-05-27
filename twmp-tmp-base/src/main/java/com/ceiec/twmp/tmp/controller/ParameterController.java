package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpSysParameter;
import com.ceiec.twmp.tmp.entity.TwmpSysRole;
import com.ceiec.twmp.tmp.services.IParameterService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.parameter.edit.SysParameterEditVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-15 10:01
 * Description:
 **/
@RestController
@RequestMapping("/systemManagement")
public class ParameterController {

    @Autowired
    IParameterService parameterService;


    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
/**
 * @description:查询可配置参数
 * @Param: token
 * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
 * @author: zhaozhengchang
 * @date: 2019/3/25 14:45
 */
    @GetMapping("/systemParameters")
    public ResponseContent listParameters(@RequestHeader String token) {

        List<TwmpSysParameter> parameters = null;
        try {
            parameters = parameterService.listParameters(token);
        } catch (Exception e) {
            logger.error("query system parameters failed.",e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS,parameters);
    }
/**
 * @description:更新系统参数
 * @Param: token
 * @Param: sysParameterEditVO
 * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
 * @author: zhaozhengchang
 * @date: 2019/3/25 14:45
 */
    @PostMapping("/systemParametersUpdate")
    public ResponseContent editParameters(@RequestHeader String token ,@Valid @RequestBody SysParameterEditVO sysParameterEditVO) {
        try {
            parameterService.editParameters(token,sysParameterEditVO);
        } catch (Exception e) {
            logger.error("update system parameters failed.",e);
            return new ResponseContent(ResponseType.FAIL);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
