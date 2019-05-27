package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskTeamEf;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.vo.monitor.delete.MonitorGuardianDeleteVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpMonitorTaskTeamEfMapper")
public interface TwmpMonitorTaskTeamEfMapper extends Mapper<TwmpMonitorTaskTeamEf> {

    void insertTeamList(List<TwmpMonitorTaskTeamEf> list);

    void updateTeamList(List<TwmpMonitorTaskTeamEf> list);

    void deleteTeamList(List<MonitorGuardianDeleteVO> list);

    void updateChangeTaskId(Long taskId);
}