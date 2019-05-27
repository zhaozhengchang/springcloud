package com.ceiec.twmp.tmp.vo.paperwork.result;

import com.ceiec.twmp.tmp.ResponseType;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-11 17:32
 * Description:
 **/

public class PaperworkResultVO {

    /** serializable ID */
    private static final long serialVersionUID = 7985322981499931338L;

    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
