package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpOpeDismantle;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishDetailVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishResultVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeToResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpOpeDismantleMapper")
public interface TwmpOpeDismantleMapper extends Mapper<TwmpOpeDismantle> {

    List<OpeToResultVO> queryToDismantleByPage(OpeToQueryVO opeQueryVO);

    List<OpeFinishResultVO> queryDismantledByPage(OpeFinishQueryVO opeFinishQueryVO);

    OpeFinishDetailVO queryDismantledDetail(OpeFinishDetailQueryVO opeFinishDetailQueryVO);
}