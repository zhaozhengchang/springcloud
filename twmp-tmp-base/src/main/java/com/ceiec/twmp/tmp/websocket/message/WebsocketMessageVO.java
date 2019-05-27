package com.ceiec.twmp.tmp.websocket.message;

/**
 * @author Ding
 * @version V1.0
 * @Description: vo for websocket message
 * @create 2019-03-12 10:04
 **/
public class WebsocketMessageVO {

     private String message;
     private Byte messageType;
     private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
