package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.entity.TwmpBsPersonCriminalEf;
import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.mapper.TwmpBsPersonCriminalEfMapper;
import com.ceiec.twmp.tmp.services.ICriminalService;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.person.add.PersonCriminalVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * CreateDate：2019/3/6 11:11 </br>
 * Author：shihsh  </br>
 * Description: TODO </br>
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class CriminalServiceImpl implements ICriminalService {

    /**
     * criminal_ef Mapper
     */
    @Autowired
    private TwmpBsPersonCriminalEfMapper twmpBsPersonCriminalEfMapper;

    @Autowired
    private ILogService logService;

    /**
     * 插入监控人员犯罪记录
     * @param personCriminalVO
     * @return
     */
    @Override
    public void addOrEditCiminal(String token, PersonCriminalVO personCriminalVO, String personName) {
        TwmpBsPersonCriminalEf twmpBsPersonCriminalEf = new TwmpBsPersonCriminalEf();
        ObjectUtils.copy(personCriminalVO, twmpBsPersonCriminalEf);
        twmpBsPersonCriminalEf.setUpdater(TokenUtils.getUserName(token));
        twmpBsPersonCriminalEf.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonCriminalEf.setUpdateTime(new Date());
        if (twmpBsPersonCriminalEf.getCriminalId() != null) {
            twmpBsPersonCriminalEfMapper.updateByPrimaryKeySelective(twmpBsPersonCriminalEf);
            logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.paperwork.value, personName, null);
        } else {
            twmpBsPersonCriminalEf.setCriminalId(SnowflakeIdWorkerUtil.generateLongId());
            twmpBsPersonCriminalEf.setCreateTime(new Date(System.currentTimeMillis()));
            twmpBsPersonCriminalEf.setCreator(TokenUtils.getUserName(token));
            twmpBsPersonCriminalEf.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
            twmpBsPersonCriminalEf.setDeleted( TmpBaseConstants.FLAG_DELETE_FALSE);
            twmpBsPersonCriminalEfMapper.insertSelective(twmpBsPersonCriminalEf);
            logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.paperwork.value, personName, null);
        }
    }


    /**
     * 删除犯罪记录信息
     * @param token token
     * @param criminalId 犯罪记录Id
     */
    @Override
    public void deleteCriminal(String token, Long criminalId, String personName) {
        TwmpBsPersonCriminalEf twmpBsPersonCriminalEf = new TwmpBsPersonCriminalEf();
        twmpBsPersonCriminalEf.setCriminalId(criminalId);
        twmpBsPersonCriminalEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpBsPersonCriminalEf.setUpdater(TokenUtils.getUserName(token));
        twmpBsPersonCriminalEf.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonCriminalEf.setUpdateTime(new Date());

        twmpBsPersonCriminalEfMapper.updateByPrimaryKeySelective(twmpBsPersonCriminalEf);
        logService.saveOperateLog(token, OperateType.delete.value, OperateObjectType.paperwork.value, personName, null);
    }

    @Override
    public List<TwmpBsPersonCriminalEf> getCriminalList(String token, PersonQueryVO personQueryVO) {

        List<TwmpBsPersonCriminalEf> twmpBsPersonCriminalEfList = twmpBsPersonCriminalEfMapper.getCriminalListByPerson(personQueryVO);
        return twmpBsPersonCriminalEfList;
    }

    @Override
    public boolean criminalExitsFlag(String token, Long criminalId) {
        boolean existFlag = false;
        Example example = new Example(TwmpBsPersonCriminalEf.class);
        example.createCriteria()
                .andEqualTo("criminalId",criminalId);
        List<TwmpBsPersonCriminalEf> exitEntity = twmpBsPersonCriminalEfMapper.selectByExample(example);
        if (exitEntity.size() > 0) {
            existFlag = true;
        }
        return existFlag;
    }


}
