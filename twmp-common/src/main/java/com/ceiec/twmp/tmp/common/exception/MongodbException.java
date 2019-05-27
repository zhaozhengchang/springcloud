package com.ceiec.twmp.tmp.common.exception;

/**
 * @author Ding
 * @version V1.0
 * @Description: mongodb exception
 * @create 2019-03-28 9:43
 **/
public class MongodbException extends CEIECException {

    private static final int CODE = 300;

    public MongodbException(String msg) {
        super(CODE, msg);
    }

    public MongodbException(String msg, Throwable cause) {
        super(CODE, msg, cause);
    }
}
