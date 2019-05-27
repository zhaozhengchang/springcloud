package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.MessageSubType;
import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.mapper.TwmpSysMessageMapper;
import com.ceiec.twmp.tmp.services.IMessageService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.message.query.MessageListQueryVO;
import com.ceiec.twmp.tmp.vo.message.read.MessageReadVO;
import com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @title: MessageService </br>
 * @createDate：2019/3/11 15:02 </br>
 * @author：shihsh </br>
 * @description: 消息中心管理 </br>
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private TwmpSysMessageMapper twmpSysMessageMapper;


    /**
    *
    * @description: 根据参数获取消息，分页显示
    * @param: token
    * @param: messageListQueryVO
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO>
    * @author: shihsh
    * @date: 2019/3/12
    */
    @Override
    public PagedItemsVO<MessageListResultVO> listMessageByPage(String token, MessageListQueryVO messageListQueryVO) {

        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        messageListQueryVO.setUserId(user.getUserId());
        List<MessageSubType> messageSubTypes = user.getMessageSubTypes();
        List<Byte> messageSubTypeList = new ArrayList<>();
        messageSubTypes.forEach(messageSubType -> {
            messageSubTypeList.add(messageSubType.value);
        });
        List<Long> ownDepartmentIds = DepartmentStrToList.getDepartmentIdList(token);
        messageListQueryVO.setOwnDepartmentIds(ownDepartmentIds);
        messageListQueryVO.setMessageSubTypes(messageSubTypeList);
        String startTime = messageListQueryVO.getStartTime();
        if (startTime != null && !"".equals(startTime.trim())) {
            messageListQueryVO.setStartDate(DateFormatUtils.stringToDateTime(startTime));
        }
        String endTime = messageListQueryVO.getEndTime();
        if (endTime != null && !"".equals(endTime.trim())) {
            messageListQueryVO.setEndDate(DateFormatUtils.stringToDateTime(endTime));
        }
        Long total = twmpSysMessageMapper.countMessage(messageListQueryVO);
        PageHelper.startPage(messageListQueryVO.getPageNo(), messageListQueryVO.getPageSize(), " message_time desc");
        List<MessageListResultVO> list = twmpSysMessageMapper.getMessageByPage(messageListQueryVO);

        return new PagedItemsVO<>(total, list);
    }

    /**
    *
    * @description: 获取指定数量的消息，返回列表
    * @param: token
    * @param: num
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO>
    * @author: shihsh
    * @date: 2019/3/12
    */
    @Override
    public List<MessageListResultVO> listMessageByNum(String token, Integer num) {
        MessageListQueryVO messageListQueryVO = new MessageListQueryVO();
        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        List<Long> ownDepartmentIds = DepartmentStrToList.getDepartmentIdList(token);
        List<MessageSubType> messageSubTypes = user.getMessageSubTypes();
        List<Byte> messageSubTypeList = new ArrayList<>();
        messageSubTypes.forEach(messageSubType -> {
            messageSubTypeList.add(messageSubType.value);
        });
        messageListQueryVO.setOwnDepartmentIds(ownDepartmentIds);
        messageListQueryVO.setMessageSubTypes(messageSubTypeList);
        messageListQueryVO.setNumber(num);
        messageListQueryVO.setUserId(user.getUserId());
        List<MessageListResultVO> list = twmpSysMessageMapper.getMessageByNum(messageListQueryVO);
        return list;
    }

    /**
    *
    * @description: 标记消息为已读
    * @param: token
    * @param: messageReadVO
    * @return: void
    * @author: shihsh
    * @date: 2019/3/12
    */
    @Override
    public void readMessage(String token, MessageReadVO messageReadVO) {
        String messageIds = messageReadVO.getMessageIds();
        if (messageIds == null) {
            return;
        }
        String[] messageIdArr = messageIds.trim().split(",");
        messageReadVO.setMessageIdArr(messageIdArr);
        RedisUserInfoVO user = UserInfoRedis.getUser(token);
        messageReadVO.setReader(user.getUserName());
        messageReadVO.setReaderId(user.getUserId());
        twmpSysMessageMapper.setMessageRead(messageReadVO);
    }

    @Override
    public void saveMessage(TwmpSysMessage twmpSysMessage) {
        twmpSysMessageMapper.insertSelective(twmpSysMessage);
    }
}
