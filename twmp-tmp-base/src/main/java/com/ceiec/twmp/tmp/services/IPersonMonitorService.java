package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO;

/**
 * CreateDate：2019/3/7 11:44 </br>
 * Author：shihsh  </br>
 * Description: TODO </br>
 **/

public interface IPersonMonitorService {

    /**
    *
    * @Description: 获取被监控人员基本信息详情
    * @param: token
    * @param: personId
    * @return: com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO
    * @Author: shihsh
    * @Date: 2019/3/11
    */
    PersonBasicDetailResultVO getPersonDetail(String token, Long personId);

}
