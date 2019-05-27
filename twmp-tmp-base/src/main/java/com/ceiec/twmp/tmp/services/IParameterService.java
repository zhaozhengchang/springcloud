package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSysParameter;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.parameter.edit.SysParameterEditVO;

import java.util.List;

/**
 * CreateDate：2019/1/21<br/>
 * Author：wenliang <br/>
 * Description: interface for parameter service
 */
public interface IParameterService {

    /**
     * Edit parameter.
     *
     * @param sysParameterSetVO the sys parameter set vo
     */
//    void editParameter(SysParameterSetVO sysParameterSetVO);

    /**
     * 查询可配置参数
     * @param token
     * @return
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    List<TwmpSysParameter> listParameters(String token);

    /**
     * 修改配置信息
     * @param token
     * @param sysParameterEditVO
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    void editParameters(String token, SysParameterEditVO sysParameterEditVO);

    /*************************************************************************************************************************************
     ** @Description query by parameter name
     ** @param: parameterName
     ** @Return com.ceiec.twmp.tmp.entity.TwmpSysParameter
     ** @Author Ding
     ** @Date 2019/3/25 15:00
     **
     ************************************************************************************************************************************/
    TwmpSysParameter queryByParameterName(String parameterName);
}
