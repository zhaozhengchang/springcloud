package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.vo.message.query.MessageListQueryVO;
import com.ceiec.twmp.tmp.vo.message.read.MessageReadVO;
import com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpSysMessageMapper")
public interface TwmpSysMessageMapper extends Mapper<TwmpSysMessage> {

    /**
    *
    * @description: 分页获取消息
    * @param: messageListQueryVO
    * @return: java.util.List<com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO>
    * @author: shihsh
    * @date: 2019/3/12
    */
    List<MessageListResultVO> getMessageByPage(MessageListQueryVO messageListQueryVO);


    /**
    *
    * @description:  查询消息总数
    * @param: messageListQueryVO
    * @return: java.lang.Long
    * @author: shihsh
    * @date: 2019/3/25
    */
    Long countMessage(MessageListQueryVO messageListQueryVO);

    /**
    *
    * @description: 获取指定数量的消息
    * @param: messageListQueryVO
    * @return: java.util.List<com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO>
    * @author: shihsh
    * @date: 2019/3/12
    */
    List<MessageListResultVO> getMessageByNum(MessageListQueryVO messageListQueryVO);
    
   /** 
   * 
   * @description: 标记消息为已读
   * @param: messageIdArr  
   * @return: void
   * @author: shihsh
   * @date: 2019/3/12 
   */  
    void setMessageRead(MessageReadVO messageRead);
}