package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskFenceSpaceEf;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpMonitorTaskFenceSpaceEfMapper")
public interface TwmpMonitorTaskFenceSpaceEfMapper extends Mapper<TwmpMonitorTaskFenceSpaceEf> {

    void addFenceSpaceList(List<TwmpMonitorTaskFenceSpaceEf> list);


    void updateFenceSpaceList(List<TwmpMonitorTaskFenceSpaceEf> list);


    TwmpMonitorTaskEf getTaskByTaskCode(String taskCode);
}