package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpSysPaperwork;
import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.mapper.TwmpSysPaperworkMapper;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IPaperworkService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.paperwork.add.PaperworkAddVO;
import com.ceiec.twmp.tmp.vo.paperwork.query.PaperworkQueryVO;
import com.ceiec.twmp.tmp.vo.paperwork.result.PaperworkResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-06 19:56
 * Description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PaperworkServiceImpl implements IPaperworkService {

    @Autowired
    TwmpSysPaperworkMapper twmpSysPaperworkMapper;

    @Autowired
    private ILogService logService;

    @Override
    public PaperworkResultVO addOrEditPaperwork(String token, PaperworkAddVO paperworkAddVO) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        if (null == paperworkAddVO.getPaperworkId()) {
            //校验文书名是否已存在
            if (existrPaperworkName(paperworkAddVO.getPaperworkName())) {
                PaperworkResultVO paperworkResultVO = new PaperworkResultVO();
                paperworkResultVO.setResponseType(ResponseType.PAPERWORK_NAME_EXISTED);
                return paperworkResultVO;
            }
            //新增用户
            addPaperwork(token, paperworkAddVO);
            //记录操作日志
            logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.paperwork.value,
                    paperworkAddVO.getPaperworkName(),null);
        } else {
            editPaperwork(token, paperworkAddVO);
            //记录操作日志
            logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.paperwork.value,
                    paperworkAddVO.getPaperworkName(),null);
        }

        return new PaperworkResultVO();
    }

    @Override
    public void deletePaperwork(String token, Long paperworkId) {
        TwmpSysPaperwork twmpSysPaperwork1 = twmpSysPaperworkMapper.selectByPrimaryKey(paperworkId);
        TwmpSysPaperwork twmpSysPaperwork = new TwmpSysPaperwork();
        ObjectUtils.copy(twmpSysPaperwork1, twmpSysPaperwork);

        twmpSysPaperwork.setPaperworkId(paperworkId);
        twmpSysPaperwork.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpSysPaperwork.setUpdater(TokenUtils.getUserName(token));
        twmpSysPaperwork.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysPaperwork.setUpdateTime(new Date());
        twmpSysPaperworkMapper.updateByPrimaryKey(twmpSysPaperwork);
        //记录操作日志
        logService.saveOperateLog(token, OperateType.delete.value, OperateObjectType.paperwork.value,
                twmpSysPaperwork1.getPaperworkName(),null);

    }

    @Override
    public PagedItemsVO<TwmpSysPaperwork> listPaperwork(String token, PaperworkQueryVO paperworkQueryVO) {

        List<TwmpSysPaperwork> list = twmpSysPaperworkMapper.listPaperwork(paperworkQueryVO);
        return new PagedItemsVO<>(list.size() , list);
    }


    /**
     * 校验文书名
     * true : 此文书名已存在
     * false：此文书名不存在
     *
     * @param paperworkName
     * @return
     */
    private boolean existrPaperworkName(String paperworkName) {
        TwmpSysPaperwork twmpSysPaperwork = new TwmpSysPaperwork();
        twmpSysPaperwork.setPaperworkName(paperworkName);
        int count = twmpSysPaperworkMapper.selectCount(twmpSysPaperwork);
        return count >= 1 ? true : false;
    }

    /**
     * 新增文书
     *
     * @param token
     * @param paperworkAddVO
     * @return
     */
    public void addPaperwork(String token, PaperworkAddVO paperworkAddVO) {
        BigInteger id = SnowflakeIdWorkerUtil.generateId();
        TwmpSysPaperwork twmpSysPaperwork = new TwmpSysPaperwork();
        ObjectUtils.copy(paperworkAddVO, twmpSysPaperwork);

        twmpSysPaperwork.setPaperworkId(id.longValue());
        twmpSysPaperwork.setCreator(TokenUtils.getUserName(token));
        twmpSysPaperwork.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysPaperwork.setCreateTime(new Date());
        twmpSysPaperwork.setDeleted(TmpBaseConstants.FLAG_DELETE_FALSE);
        twmpSysPaperworkMapper.insertSelective(twmpSysPaperwork);
    }

    /**
     * 编辑文书
     *
     * @param * @param paperworkAddVO the user update vo the user update vo
     */
    public void editPaperwork(String token, PaperworkAddVO paperworkAddVO) {
        TwmpSysPaperwork twmpSysPaperwork = new TwmpSysPaperwork();
        ObjectUtils.copy(paperworkAddVO, twmpSysPaperwork);
        twmpSysPaperworkMapper.updateByPrimaryKeySelective(twmpSysPaperwork);
    }
}
