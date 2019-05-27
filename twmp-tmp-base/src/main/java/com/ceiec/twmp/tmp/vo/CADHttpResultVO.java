package com.ceiec.twmp.tmp.vo;

/**
 * @title: CADHttpResultVO </br>
 * @createDate: 2019/4/19 16:40 </br>
 * @author: shihsh  </br>
 * @description: 接处警返回结果VO </br>
 * @version: V1.0
 **/


public class CADHttpResultVO {
    private String code;

    private String error;

    private Object mess;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMess() {
        return mess;
    }

    public void setMess(Object mess) {
        this.mess = mess;
    }
}
