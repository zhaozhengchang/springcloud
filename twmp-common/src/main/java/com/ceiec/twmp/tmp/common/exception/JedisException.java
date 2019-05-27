package com.ceiec.twmp.tmp.common.exception;

/**
 * @author Ding
 * @version V1.0
 * @Description: exception of redis
 * @create 2019-03-01 9:22
 **/
public class JedisException extends CEIECException{

    private static final int CODE = 200;

    public JedisException(String msg) {
        super(CODE, msg);
    }


    public JedisException(String msg, Throwable cause) {
        super(CODE, msg, cause);
    }


}
