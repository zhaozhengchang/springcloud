package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysFile;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
@Component(value = "twmpSysFileMapper")
public interface TwmpSysFileMapper extends Mapper<TwmpSysFile> {
}