package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonEf;
import com.ceiec.twmp.tmp.vo.monitor.result.PersonBasicDetailResultVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonAndDeviceVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpBsPersonEfMapper")
public interface TwmpBsPersonEfMapper extends Mapper<TwmpBsPersonEf> {

    List<PersonInfoListResultVO> getPersonListByPage(PersonQueryVO personQueryVO);

    Long countPersonList(PersonQueryVO personQueryVO);

    PersonBasicDetailResultVO getPersonDetail(PersonQueryVO personQueryVO);

    PersonAndDeviceVO getPersonAndDeviceInfo(Long personId);

    void insertPersonInfoList(List<TwmpBsPersonEf> list);

    List<TwmpBsPersonEf> repeatedPerson(List<TwmpBsPersonEf> list);

    List<PersonInfoListResultVO> getPersonList(PersonQueryVO personQueryVO);
}