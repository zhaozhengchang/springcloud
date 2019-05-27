package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.vo.department.result.RedisDepartmentVO;

import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: department redis
 * @create 2019-04-10 10:08
 **/
public class DepartmentRedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description  save department
     ** @param: twmpSysDepartment
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/10 10:19
     **
     ************************************************************************************************************************************/
    public static void saveDepartmentInfo(RedisDepartmentVO redisDepartmentVO){
        saveByMap(DEPARTMENT_INFO, redisDepartmentVO.getDepartmentId().toString(), redisDepartmentVO);
    }

    /*************************************************************************************************************************************
     ** @Description get department by name
     ** @param: departmentName
     ** @Return com.ceiec.twmp.tmp.entity.TwmpSysDepartment
     ** @Author Ding
     ** @Date 2019/4/10 10:19
     **
     ************************************************************************************************************************************/
    public static RedisDepartmentVO getDepartmentInfoByName(String departmentName){
        Map<String, RedisDepartmentVO> departmentMap = getDepartmentInfo();
        if(departmentMap!=null && departmentMap.size()>0){
            for(String departmentId : departmentMap.keySet()){
                if(departmentName.equals(departmentMap.get(departmentId).getDepartmentName())){
                    return departmentMap.get(departmentId);
                }
            }
        }
        return null;
    }

    /*************************************************************************************************************************************
     ** @Description get department map
     ** @param:
     ** @Return java.util.Map<java.lang.String,com.ceiec.twmp.tmp.entity.TwmpSysDepartment>
     ** @Author Ding
     ** @Date 2019/4/10 10:19
     **
     ************************************************************************************************************************************/
    public static Map<String, RedisDepartmentVO> getDepartmentInfo(){
        return getMap(DEPARTMENT_INFO, RedisDepartmentVO.class);
    }

    /**
    *
    * @description:  get department by departmentId
    * @param: id
    * @return: com.ceiec.twmp.tmp.vo.department.result.RedisDepartmentVO
    * @author: shihsh
    * @date: 2019/4/10
    */
    public static RedisDepartmentVO getDepartmentById(Long id) {
        return (RedisDepartmentVO) getByMap(DEPARTMENT_INFO, id.toString(), RedisDepartmentVO.class);
    }

    /*************************************************************************************************************************************
     ** @Description delete department
     ** @param: twmpSysDepartment
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/10 10:19
     **
     ************************************************************************************************************************************/
    public static void deleteDepartmentInfo(RedisDepartmentVO redisDepartmentVO){
        deleteByMap(DEPARTMENT_INFO, redisDepartmentVO.getDepartmentId().toString());
    }

    /*************************************************************************************************************************************
     ** @Description delete all department
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/10 10:19
     **
     ************************************************************************************************************************************/
    public static void deleteDepartmentInfoAll(){
        del(DEPARTMENT_INFO);
    }
}
