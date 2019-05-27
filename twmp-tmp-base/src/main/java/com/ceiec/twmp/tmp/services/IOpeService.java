package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishDetailQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeFinishQueryVO;
import com.ceiec.twmp.tmp.vo.ope.query.OpeToQueryVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishDetailVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeFinishResultVO;
import com.ceiec.twmp.tmp.vo.ope.result.OpeToResultVO;
import com.ceiec.twmp.tmp.vo.ope.OpeUpdateVO;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: ope service interface
 * @create 2019-04-11 9:30
 **/
public interface IOpeService {

    List<OpeToResultVO> queryToOpeList(String token, OpeToQueryVO opeQueryVO);

    void installFinish(String token, OpeUpdateVO opeUpdateVO);

    void dismantleFinish(String token, OpeUpdateVO opeUpdateVO);

    void changeFinish(String token, OpeUpdateVO opeUpdateVO);

    List<OpeFinishResultVO> queryFinishOpeList(String token, OpeFinishQueryVO opeFinishQueryVO);

    OpeFinishDetailVO queryFinishDetail(OpeFinishDetailQueryVO opeFinishDetailQueryVO);
}
