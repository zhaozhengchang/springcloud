package com.ceiec.twmp.tmp.authorize;

import com.ceiec.twmp.tmp.utils.response.IResponseType;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: store response type information for current project
 **/
public enum ResponseType implements IResponseType {

    /** request success */
    SUCCESS(IResponseType.SUCCESS, "request success"),
    /** account not exist */
    NOT_EXIST("1001", "account not exist"),
    /** account is unusable */
    UNUSABLE("1002", "account is unusable"),
    /** account is deleted */
    DELETED("1003", "account is deleted"),
    /** wrong password */
    PASSWORD_UNMATCHED("1004", "user name & password unmatched(wrong password)"),
    /** exceed max wrong times */
    EXCEED_WRONG_TIMES("1005", "exceed max wrong times"),
    /** account is already been logged in */
    REPEAT_LOGIN("1006", "account is already been logged in"),
    /** unknown internal server error */
    UNKNOWN(IResponseType.UNKNOWN, "unknown internal server error");

    /** response code */
    private String code;

    /** code description */
    private String desc;

    /**
     * private construction function
     *
     * @param code response code
     * @param desc code description
     */
    ResponseType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
