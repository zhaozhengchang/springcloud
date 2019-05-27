package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysDepartment;
import com.ceiec.twmp.tmp.vo.department.delete.DepartmentDleteVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentTreeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Component(value = "twmpSysDepartmentMapper")
public interface TwmpSysDepartmentMapper extends Mapper<TwmpSysDepartment> {

    /**
     * TODO Mapper.xml中需实现   根据departmentName查询部门列表
     * @param departmentName
     * @return
     */
    List<DepartmentListResultVO> getDepartmentList(String departmentName);

/*************************************************************************************************************************************
 ** @Description get all department
 ** @param:
 ** @Return java.util.List<com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO>
 ** @Author Ding
 ** @Date 2019/3/5 14:32
 **
 ************************************************************************************************************************************/
    List<DepartmentListResultVO> getAllDepartment();

    /**
     * 查询某父组织机构下的所有组织机构id
     * @param list
     * @return
     */
    List<Long> listDepartmentIdsByParentIds(List list);
    /**
     * 查询某父组织机构下的所有组织机构id
     * @param id
     * @return
     */
    List<DepartmentTreeVO> listDepartmentsByParentId(Long id);

    /**
     * 删除组织机构
     * @param map
     */
    void deleteDepartmentsById(Map map);

    /**
     * 批量更新组织机构下的被监控人数
     * @param list list != null 并且 list.size() != 0
     */
    void updateBatchPersonNum(List<TwmpSysDepartment> list);
}