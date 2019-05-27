package com.ceiec.twmp.tmp;


import com.ceiec.twmp.tmp.cache.redis.DepartmentRedis;
import com.ceiec.twmp.tmp.services.IDepartmentService;
import com.ceiec.twmp.tmp.services.IDictService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentListResultVO;
import com.ceiec.twmp.tmp.vo.department.result.RedisDepartmentVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.ceiec.twmp.tmp.mapper")
public class TmpApplication {



    private static ApplicationContext applicationContext;


    public static void main(String[] args) {

        applicationContext = SpringApplication.run(TmpApplication.class, args);

        /**
         *  //load some system memory
         */
        saveDictInRedis();
        saveDepartmentInfo();
    }


    /*************************************************************************************************************************************
     ** @Description save dict in redis
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/6 9:50
     **
     ************************************************************************************************************************************/
    private static void saveDictInRedis(){
        IDictService dictService = applicationContext.getBean(IDictService.class);
        dictService.saveDictRedis();
    }

    /*************************************************************************************************************************************
     ** @Description save departmentInfo info in redis
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/10 10:37
     **
     ************************************************************************************************************************************/
    private static void saveDepartmentInfo(){
        IDepartmentService departmentService = applicationContext.getBean(IDepartmentService.class);
        List<DepartmentListResultVO> departmentListResultVOList = departmentService.getAllDepartment();
        DepartmentRedis.deleteDepartmentInfoAll();
        if(departmentListResultVOList!=null && departmentListResultVOList.size()>0){
            for(DepartmentListResultVO departmentListResultVO: departmentListResultVOList){
                RedisDepartmentVO departmentVO = new RedisDepartmentVO();
                ObjectUtils.copy(departmentListResultVO, departmentVO);
                DepartmentRedis.saveDepartmentInfo(departmentVO);
            }
        }
    }


}
