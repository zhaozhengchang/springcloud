package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpOpeInstall;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishDetailVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishResultVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeToResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TwmpOpeInstallMapper extends Mapper<TwmpOpeInstall> {

    List<OpeToResultVO> queryToInstallByPage(OpeToQueryVO opeQueryVO);

    List<OpeFinishResultVO> queryInstalledByPage(OpeFinishQueryVO opeFinishQueryVO);

    OpeFinishDetailVO queryInstalledDetail(OpeFinishDetailQueryVO opeFinishDetailQueryVO);
}