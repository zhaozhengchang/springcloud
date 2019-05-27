package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonCriminalEf;
import com.ceiec.twmp.tmp.vo.person.add.PersonCriminalVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;

import java.util.List;

/**
 * CreateDate：2019/3/6 11:11 </br>
 * Author：shihsh  </br>
 * Description: TODO </br>
 **/


public interface ICriminalService {

    void addOrEditCiminal(String token, PersonCriminalVO personCriminalVO, String personName);

    void deleteCriminal(String token, Long criminalId, String personName);

    List<TwmpBsPersonCriminalEf> getCriminalList(String token, PersonQueryVO personCriminalVO);

    boolean criminalExitsFlag(String token, Long criminalId);

}
