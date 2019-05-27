package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysParameter;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
@Component(value = "twmpSysParameterMapper")
public interface TwmpSysParameterMapper extends Mapper<TwmpSysParameter> {

    TwmpSysParameter queryByParameterName(String parameterName);

}