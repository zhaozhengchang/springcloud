package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysDict;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.dict.save.DictSaveVo;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component(value = "twmpSysDictMapper")
public interface TwmpSysDictMapper extends Mapper<TwmpSysDict> {

    List<DictResultVo> getAllDict();

    Long getDictNum(DictSaveVo dictSaveVo);

}