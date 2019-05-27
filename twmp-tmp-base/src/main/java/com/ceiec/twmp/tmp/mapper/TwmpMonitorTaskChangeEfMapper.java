package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskChangeEf;
import com.ceiec.twmp.tmp.vo.monitor.query.MonitorTaskDetailQueryVO;
import com.ceiec.twmp.tmp.vo.monitor.result.MonitorTaskDetailQueryResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "twmpMonitorTaskChangeEfMapper")
public interface TwmpMonitorTaskChangeEfMapper extends Mapper<TwmpMonitorTaskChangeEf> {

    MonitorTaskDetailQueryResultVO getMonitorTaskDetail(MonitorTaskDetailQueryVO monitorTaskDetailQueryVO);

}