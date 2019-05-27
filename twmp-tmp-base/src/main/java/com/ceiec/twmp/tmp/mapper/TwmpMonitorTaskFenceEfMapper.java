package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskFenceEf;
import com.ceiec.twmp.tmp.vo.fence.FenceShapeVO;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonTaskResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpMontorTaskFenceEfMapper")
public interface TwmpMonitorTaskFenceEfMapper extends Mapper<TwmpMonitorTaskFenceEf> {

    void addFenceList(List<TwmpMonitorTaskFenceEf> list);

    void editFenceList(List<TwmpMonitorTaskFenceEf> list);

    void delFenceList(List<TwmpMonitorTaskFenceEf> list);

    void updateChangeTaskId(Long taskId);

    List<FenceShapeVO> selectEnableFenceSpaceByTaskId(Long taskId);

}