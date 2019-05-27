package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSysPaperwork;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.paperwork.add.PaperworkAddVO;
import com.ceiec.twmp.tmp.vo.paperwork.query.PaperworkQueryVO;
import com.ceiec.twmp.tmp.vo.paperwork.result.PaperworkResultVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;

import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-06 19:56
 * Description:
 **/
public interface IPaperworkService {
    /**
     * 新加 、 编辑 文书
     * paperworkAddVO.paperworkId 为空 ：新增
     * paperworkAddVO.paperworkId 不为空 ：编辑
     * @param token
     * @param paperworkAddVO
     * @return
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    PaperworkResultVO addOrEditPaperwork(String token, PaperworkAddVO paperworkAddVO);

    /**
     * 删除文书
     * @param token
     * @param paperworkId
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    void deletePaperwork(String token, Long paperworkId);

    /**
     * 查找文书
     * @param token
     * @param paperworkQueryVO
     * @return
     * @author: zhaozhengchang
     * @date: 2019/3/22 17:17
     */
    PagedItemsVO<TwmpSysPaperwork> listPaperwork(String token, PaperworkQueryVO paperworkQueryVO);
}
