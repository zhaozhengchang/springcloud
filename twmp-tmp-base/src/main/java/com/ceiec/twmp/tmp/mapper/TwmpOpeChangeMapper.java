package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpOpeChange;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishDetailVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishResultVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeToResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpOpeChangeMapper")
public interface TwmpOpeChangeMapper extends Mapper<TwmpOpeChange> {

    List<OpeToResultVO> queryToChangeByPage(OpeToQueryVO opeQueryVO);

    List<OpeFinishResultVO> queryChangedByPage(OpeFinishQueryVO opeFinishQueryVO);

    OpeFinishDetailVO queryChangedDetail(OpeFinishDetailQueryVO opeFinishDetailQueryVO);
}