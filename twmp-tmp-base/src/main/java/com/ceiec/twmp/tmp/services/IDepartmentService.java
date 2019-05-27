package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentAddResultVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentDeleteResultVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentTreeVO;
import com.ceiec.twmp.tmp.vo.department.update.DepartmentUpdateVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;

import java.math.BigInteger;
import java.util.List;


/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: interface for department service
 */
public interface IDepartmentService {


    /**
     * Department query list paged items vo.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the DepartmentListResultVO List
     */
    List<DepartmentListResultVO> departmentQueryList(String token, String departmentName);


    /**
     * Add department department save result vo.
     *
     * @param token           the token
     * @param departmentAddVO the department save vo
     * @return the department save result vo
     * @throws ParameterException the parameter exception
     */
    DepartmentAddResultVO addDepartment(String token, DepartmentAddVO departmentAddVO) throws ParameterException;


    /**
     * Edit department.
     *
     * @param token              the token
     * @param departmentAddVO the department update vo
     * @throws ParameterException the parameter exception
     */
    void editDepartment(String token, DepartmentAddVO departmentAddVO) throws ParameterException;


    /**
     * Name exist flag boolean.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the boolean
     */
    boolean nameExistFlag(String token, String departmentName);


    /**
     * Delete department.
     *
     * @param token        the token
     * @param departmentId the department id
     */
    void deleteDepartment(String token, BigInteger departmentId);


/*************************************************************************************************************************************
 ** @Description  get all department
 ** @param: token
 * @param: departmentName
 ** @Return java.util.List<com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO>
 ** @Author Ding
 ** @Date 2019/3/5 14:31
 **
 ************************************************************************************************************************************/
    List<DepartmentListResultVO> getAllDepartment();

/*************************************************************************************************************************************
 ** @Description get department tree
 ** @param: fatherDepartmentId
 ** @Return com.ceiec.twmp.tmp.vo.department.result.DepartmentTreeVO
 ** @Author Ding
 ** @Date 2019/3/5 14:46
 **
 ************************************************************************************************************************************/
    DepartmentTreeVO getDepartmentTree(Long fatherDepartmentId);


    /*************************************************************************************************************************************
     ** @Description get own departmentIds
     ** @param: fatherDepartmentId
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/5 16:06
     **
     ************************************************************************************************************************************/
    String getOwnDepartmentIds(Long fatherDepartmentId);

    /**
     * departmentAddVO.departmentId为空：新加
     * departmentAddVO.departmentId不为空：编辑
     * @param token
     * @param departmentAddVO
     * @return
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    DepartmentAddResultVO addOrEditDepartment(String token, DepartmentAddVO departmentAddVO) throws BusinessException;
    /**
     * 删除组织机构
     * 1.如果要删除的组织机构及其所有下级组织机构中任意一个组织机构绑定了用户或者设备，将删除失败
     * 2.如果都没绑定用户或者设备，则删除传入的组织机构及其下属的所有组织机构
     * @param token
     * @param departmentId
     * @return
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    DepartmentDeleteResultVO deleteDepartments(String token, Long departmentId)throws BusinessException;

}
