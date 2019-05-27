package com.ceiec.twmp.tmp.utils.response;

import com.alibaba.fastjson.JSONObject;

/**
 * CreateDate：2018/4/26 <br/>
 * Author：wenliang <br/>
 * Description: store response data from each project
 **/
public class ResponseContent {

    /** return code */
    private int code;

    /** error(if have) description */
    private String error;

    /** return data */
    private Object data;

    /** some tips **/
    private String message;

    /**
     * default construction
     */
    public ResponseContent() {

    }

    /**
     * override construction
     *
     * @param responseType response type
     */
    public ResponseContent(IResponseType responseType) {
        this.code = Integer.parseInt(responseType.getCode());
        if (!(IResponseType.SUCCESS.equals(responseType.getCode()))) {
            this.error = responseType.getDesc();
        }
    }

    /**
     * override construction
     *
     * @param responseType response type
     * @param responseData response data
     */
    public ResponseContent(IResponseType responseType, Object responseData) {
        this.code = Integer.parseInt(responseType.getCode());
        if (!(IResponseType.SUCCESS.equals(responseType.getCode()))) {
            this.error = responseType.getDesc();
        }
        this.data = responseData;
    }

    /**
     * override construction
     *
     * @param responseType response type
     * @param responseData response json data
     * @param jsonDataName key of response json data
     */
    public ResponseContent(IResponseType responseType, Object responseData, String jsonDataName) {
        this.code = Integer.parseInt(responseType.getCode());
        if (!(IResponseType.SUCCESS.equals(responseType.getCode()))) {
            this.error = responseType.getDesc();
        }
        JSONObject json = new JSONObject();
        json.put(jsonDataName, responseData);
        this.data = json;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
