package com.ceiec.twmp.tmp.email;

import com.ceiec.twmp.tmp.utils.response.IResponseType;

/**
 * CreateDate：2018/4/26 <br/>
 * Author：wenliang <br/>
 * Description: store response type information for current project
 **/
public enum ResponseType implements IResponseType {

    /** request success */
    SUCCESS(IResponseType.SUCCESS, "request success"),
    /** request fails */
    FAILS(IResponseType.FAILS, "request falis"),
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
