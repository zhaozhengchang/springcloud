package com.ceiec.twmp.tmp.vo.kafka;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: message for kafka
 * @create 2019-03-13 9:57
 **/
public class KafkaMessageVO {

    private Long id;

    private String msg;

    private Date sendTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
