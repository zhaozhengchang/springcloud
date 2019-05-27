package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.message.query.MessageListQueryVO;
import com.ceiec.twmp.tmp.vo.message.read.MessageReadVO;
import com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO;

import java.util.List;

/**
 * @version V1.0
 * @title: IMessageService </br>
 * @createDate：2019/3/11 15:00 </br>
 * @author：shihsh </br>
 * @description: 消息中心 </br>
 **/

public interface IMessageService {

    /**
    *
    * @description: 获取信息列表
    * @param: token
    * @param: messageListQueryVO
    * @return: java.util.List<com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO>
    * @author: shihsh
    * @date: 2019/3/11
    */
    PagedItemsVO<MessageListResultVO> listMessageByPage(String token, MessageListQueryVO messageListQueryVO);

    List<MessageListResultVO> listMessageByNum(String token, Integer num);

    void readMessage(String token, MessageReadVO messageReadVO);

    void saveMessage(TwmpSysMessage twmpSysMessage);

}
