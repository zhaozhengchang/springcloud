package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IDepartmentService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentAddResultVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO;
import com.ceiec.twmp.tmp.vo.department.update.DepartmentUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * CreateDate：2019/1/18 <br/>
 * Author：wenliang <br/>
 * Description: System Department Manage Controller
 */
@RestController
@RequestMapping("/departmentNameManage")
public class DepartmentManageController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * TODO 树形结构后台返回一棵树？
     * Department name query response content.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the response content
     */
    @PostMapping("/departmentNameQuery/{departmentName}")
    public ResponseContent departmentNameQuery(@RequestHeader String token, @PathVariable String departmentName) {
        List<DepartmentListResultVO> departmentListResultVOList = departmentService.departmentQueryList(token, departmentName);
        return new ResponseContent(ResponseType.SUCCESS, departmentListResultVOList);
    }


    /**
     * Add department name response content.
     *
     * @param token           the token
     * @param departmentAddVO the department save vo
     * @return the response content
     */
    @PostMapping("/addDepartmentName")
    public ResponseContent addDepartmentName(@RequestHeader String token, @Valid @RequestBody DepartmentAddVO departmentAddVO) {
        DepartmentAddResultVO departmentAddResultVO = departmentService.addDepartment(token, departmentAddVO);
        return new ResponseContent(departmentAddResultVO.getResponseType(), departmentAddResultVO.getDepartmentId(), "departmentId");
    }


    /**
     * Edit department name response content.
     *
     * @param token              the token
     * @param departmentAddVO the department update vo
     * @return the response content
     */
    @PostMapping("/editDepartmentName")
    public ResponseContent editDepartmentName(@RequestHeader String token, @Valid @RequestBody DepartmentAddVO departmentAddVO) {
        departmentService.editDepartment(token, departmentAddVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }


    /**
     * Name exist response content.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the response content
     */
    @PostMapping("/nameExist/{departmentName}")
    public ResponseContent nameExist(@RequestHeader String token, @PathVariable String departmentName) {
        boolean existFalg = departmentService.nameExistFlag(token, departmentName);
        if (existFalg) {
            return new ResponseContent(ResponseType.DEPARTMENTNAME_EXIST);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }


    /**
     * Del department name response content.
     *
     * @param token        the token
     * @param departmentId the department id
     * @return the response content
     */
    @PostMapping("/delDepartmentName/{departmentId}")
    public ResponseContent delDepartmentName(@RequestHeader String token, @PathVariable BigInteger departmentId) {
        departmentService.deleteDepartment(token, departmentId);
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
