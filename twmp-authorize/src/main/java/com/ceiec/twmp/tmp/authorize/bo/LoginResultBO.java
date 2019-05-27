package com.ceiec.twmp.tmp.authorize.bo;

import com.ceiec.twmp.tmp.authorize.ResponseType;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;

import java.io.Serializable;

/**
 * CreateDate：2018/5/17 <br/>
 * Author：wenliang <br/>
 * Description: store login result information
 **/
public class LoginResultBO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = 6669524039504559319L;

    /** response type */
    private ResponseType responseType;

    /** result information */
    private LoginResultVO resultInfo = new LoginResultVO();

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public LoginResultVO getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(LoginResultVO resultInfo) {
        this.resultInfo = resultInfo;
    }
}
