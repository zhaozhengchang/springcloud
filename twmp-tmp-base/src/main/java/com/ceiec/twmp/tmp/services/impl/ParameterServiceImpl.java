
package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.entity.TwmpSysParameter;
import com.ceiec.twmp.tmp.mapper.TwmpSysParameterMapper;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IParameterService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.vo.parameter.SysParameterSetVO;
import com.ceiec.twmp.tmp.vo.parameter.edit.SysParameterEditParaVO;
import com.ceiec.twmp.tmp.vo.parameter.edit.SysParameterEditVO;
import org.bouncycastle.asn1.icao.CscaMasterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: zhaozhengchang
 * @date: 2019/3/22 10:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParameterServiceImpl implements IParameterService {


     /**
     * system parameter mapper
     */
     @Autowired
     private ILogService logService;

    @Autowired
    private TwmpSysParameterMapper twmpSysParameterMapper;
    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @Override
    public List<TwmpSysParameter> listParameters(String token) {

        TwmpSysParameter twmpSysParameter = new TwmpSysParameter();
        twmpSysParameter.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        List<TwmpSysParameter> list = twmpSysParameterMapper.select(twmpSysParameter);
        //国际化设置
        for(TwmpSysParameter o : list){
            o.setParameterName(localeMessageSourceService.getMessageLocal(o.getParameterName(),null));
            o.setParameterUnit(localeMessageSourceService.getMessageLocal(o.getParameterUnit(),null));
        }

        return list;
    }

    @Override
    public void editParameters(String token, SysParameterEditVO sysParameterEditVO) {
        for(SysParameterEditParaVO sysParameterEditParaVO : sysParameterEditVO.getParameters()){
            TwmpSysParameter twmpSysParameterTemp = new TwmpSysParameter();
            twmpSysParameterTemp.setParameterId(sysParameterEditParaVO.getParameterId());
            TwmpSysParameter twmpSysParameter = twmpSysParameterMapper.selectByPrimaryKey(twmpSysParameterTemp);
            //为空说明此参数不存在，则跳过
            if(twmpSysParameter == null){
                continue;
            }
            twmpSysParameter.setParameterValue(sysParameterEditParaVO.getParameterValue());
            twmpSysParameter.setUpdater(TokenUtils.getUserName(token));
            twmpSysParameter.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
            twmpSysParameter.setUpdateTime(new Date());
            twmpSysParameterMapper.updateByPrimaryKey(twmpSysParameter);
            //记录操作日志
            logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.parameter.value,
                    twmpSysParameter.getParameterName() ,null);
        }
    }

    @Override
    public TwmpSysParameter queryByParameterName(String parameterName) {

        return  twmpSysParameterMapper.queryByParameterName(parameterName);
    }

}

