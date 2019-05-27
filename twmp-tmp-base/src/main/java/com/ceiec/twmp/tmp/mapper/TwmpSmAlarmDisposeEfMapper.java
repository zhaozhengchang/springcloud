package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSmAlarmDisposeEf;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDisposeDetailVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TwmpSmAlarmDisposeEfMapper extends Mapper<TwmpSmAlarmDisposeEf> {

    List<AlarmDisposeDetailVO> getAlarmDispose(Long alarmId);
}