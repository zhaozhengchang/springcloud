package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IMessageService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.message.query.MessageListQueryVO;
import com.ceiec.twmp.tmp.vo.message.read.MessageReadVO;
import com.ceiec.twmp.tmp.vo.message.result.MessageListResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: MessageController </br>
 * @createDate：2019/3/11 14:40 </br>
 * @author：shihsh </br>
 * @description: 消息管理 </br>
 * @version V1.0
 **/

@RestController
@RequestMapping("/user")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
    *
    * @description: 消息查询
    * @param: token
    * @param: messageListQueryVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/12
    */
    @PostMapping("/messageQuery")
    public ResponseContent listMessageByPage(@RequestHeader String token, @RequestBody MessageListQueryVO messageListQueryVO) {
        PagedItemsVO<MessageListResultVO> messageList = messageService.listMessageByPage(token, messageListQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, messageList);
    }

    /**
    *
    * @description: 查询指定数量的消息
    * @param: token
    * @param: num
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/12
    */
    @PostMapping("/message/{num}")
    public ResponseContent listMessageByNum(@RequestHeader String token, @PathVariable Integer num) {
        List<MessageListResultVO> messageList = messageService.listMessageByNum(token, num);
        Map<String, Object> map = new HashMap<>();
        map.put("total", messageList.size());
        map.put("messageList", messageList);
        return new ResponseContent(ResponseType.SUCCESS, map);

    }


    /**
    *
    * @description: 标记消息为已读
    * @param: token
    * @param: messageReadVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/12
    */
    @PostMapping("/messageRead")
    public ResponseContent readMessage(@RequestHeader String token, @RequestBody MessageReadVO messageReadVO) {
        messageService.readMessage(token, messageReadVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
