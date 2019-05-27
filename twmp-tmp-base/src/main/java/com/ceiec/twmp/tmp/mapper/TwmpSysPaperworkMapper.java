package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysPaperwork;
import com.ceiec.twmp.tmp.vo.paperwork.query.PaperworkQueryVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-12 10:36
 * Description:
 **/
@Component(value = "twmpSysPaperworkMapper")
public interface TwmpSysPaperworkMapper  extends Mapper<TwmpSysPaperwork> {
    /**
     * 查找文书
     * @param paperworkQueryVO
     * @return
     */
    List<TwmpSysPaperwork> listPaperwork(PaperworkQueryVO paperworkQueryVO);
}
