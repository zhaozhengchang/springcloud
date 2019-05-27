package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskOutsideRecordEf;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordQueryVO;
import com.ceiec.twmp.tmp.vo.taskOutsideRecord.TaskOutsideRecordResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TwmpMonitorTaskOutsideRecordEfMapper extends Mapper<TwmpMonitorTaskOutsideRecordEf> {

    List<TaskOutsideRecordResultVO> queryTaskOutsideRecordByPage(TaskOutsideRecordQueryVO taskOutsideRecordQueryVO);

    Long countTaskOutsideRecord(TaskOutsideRecordQueryVO taskOutsideRecordQueryVO);
}