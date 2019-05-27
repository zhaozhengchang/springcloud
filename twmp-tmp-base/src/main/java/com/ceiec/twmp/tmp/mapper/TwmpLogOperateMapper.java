package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpLogOperate;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.query.OperateLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpLogOperateMapper")
public interface TwmpLogOperateMapper extends Mapper<TwmpLogOperate> {



    /**
     *
     * @description:  查询操作日志列表
     * @param: operateLogQueryVO
     * @return: java.util.List<com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO>
     * @author: shihsh
     * @date: 2019/3/21
     */
    List<OperateLogQueryResultVO> getOperateLogList(OperateLogQueryVO operateLogQueryVO);


    /**
    *
    * @description:  分页查询操作日志
    * @param: operateLogQueryVO
    * @return: java.util.List<com.ceiec.twmp.tmp.vo.systemlog.operatelog.result.OperateLogQueryResultVO>
    * @author: shihsh
    * @date: 2019/4/16
    */
    List<OperateLogQueryResultVO> getOperateLogListByPage(OperateLogQueryVO operateLogQueryVO);

   /**
    * @description:查询操作日志数量，与 getOperateLogListByPage 一同组成分页查询的返回结果
    * @Param: operateLogQueryVO
    * @return: int
    * @author: zhaozhengchang
    * @date: 2019/3/25 17:56
    */
    int countLogTotal(OperateLogQueryVO operateLogQueryVO);
}