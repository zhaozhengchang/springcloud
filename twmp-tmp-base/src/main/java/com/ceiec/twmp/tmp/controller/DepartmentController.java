package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.services.IDepartmentService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentAddResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-06 19:51
 * Description:
 **/
@RestController
@RequestMapping("/systemManagement")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /**
    * @description:新加 / 编辑 组织机构
    * @Param: token
    * @Param: departmentAddVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: zhaozhengchang
    * @date: 2019/3/25 17:51
    */
    @PostMapping("/departmentData")
    public ResponseContent addOrEditDepartment(@RequestHeader String token, @Valid @RequestBody DepartmentAddVO departmentAddVO) {
            DepartmentAddResultVO departmentAddResultVO =departmentService.addOrEditDepartment(token,departmentAddVO);
            return new ResponseContent(departmentAddResultVO.getResponseType());
    }
   /**
    * @description: 删除组织机构
    *      * 1.如果要删除的组织机构及其所有下级组织机构中任意一个组织机构绑定了用户或者设备，将删除失败
    *      * 2.如果都没绑定用户或者设备，则删除传入的组织机构及其下属的所有组织机构
    * @Param: token
    * @Param: departmentId
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: zhaozhengchang
    * @date: 2019/3/25 17:51
    */
    @PostMapping("/departmentDel/{departmentId}")
    public ResponseContent deleteDepartment(@RequestHeader String token,  @PathVariable Long departmentId) {
        try {
              departmentService.deleteDepartments(token,departmentId);
        } catch (BusinessException e) {
            return new ResponseContent(ResponseType.DELETE_DEPARTMENT_FAILED);
        }
        return new ResponseContent(ResponseType.SUCCESS);
    }


}
