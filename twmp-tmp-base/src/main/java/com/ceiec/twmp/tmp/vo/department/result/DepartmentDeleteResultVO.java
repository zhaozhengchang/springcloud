package com.ceiec.twmp.tmp.vo.department.result;

import com.ceiec.twmp.tmp.ResponseType;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-11 14:28
 * Description:
 **/

public class DepartmentDeleteResultVO {
    /** serializable ID */
    private static final long serialVersionUID = 7985322981484631777L;

    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
