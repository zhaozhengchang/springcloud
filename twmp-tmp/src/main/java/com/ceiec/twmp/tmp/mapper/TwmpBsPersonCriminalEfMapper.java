package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonCriminalEf;
import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmPersonReport;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpBsPersonCriminalEfMapper")
public interface TwmpBsPersonCriminalEfMapper extends Mapper<TwmpBsPersonCriminalEf> {

    List<TwmpBsPersonCriminalEf> getCriminalListByPersonId(String personId);

    List<TwmpBsPersonCriminalEf> getCriminalListByPerson(PersonQueryVO personQueryVO);


}