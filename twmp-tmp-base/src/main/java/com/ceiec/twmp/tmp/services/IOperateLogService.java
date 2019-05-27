package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpLogOperate;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.query.OperateLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author zhaoZhengchang
 * @create_date 2019-03-20 17:34
 * Description:
 **/
public interface IOperateLogService {

    /**
     *
     * @description:  查询操作日志列表
     * @param: token
     * @param: operateLogQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO>
     * @author: shihsh
     * @date: 2019/3/21
     */
    PagedItemsVO<OperateLogQueryResultVO> listOperateLog(String token, OperateLogQueryVO operateLogQueryVO);

    /**
    *
    * @description:  导出操作日志
    * @param: token
    * @param: operateLogQueryVO
    * @return: void
    * @author: shihsh
    * @date: 2019/4/16
    */

    void exportOperateLog(String token, OperateLogQueryVO operateLogQueryVO, HttpServletResponse response) throws IOException;

}
