package com.ceiec.twmp.tmp.common.exception;

/**
 * @author Ding
 * @version V1.0
 * @Description: parameter is wrong
 * @create 2019-03-01 11:53
 **/
public class ParameterException extends CEIECException {

    private static final int CODE = 101;

    public ParameterException(String msg) {
        super(CODE, msg);
    }

    public ParameterException(String msg, Throwable cause) {
        super(CODE, msg, cause);
    }
}
