package com.ceiec.twmp.tmp.vo.user.result;

import com.ceiec.twmp.tmp.ResponseType;

import java.io.Serializable;

/**
 * CreateDate：2019/1/9 <br/>
 * Author：wenliang <br/>
 * Description: The type User save result vo.
 **/
public class UserAddResultVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = 7985325381484635638L;

    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;
    /** user ID */
    private Long userId;

    /**
     * Gets response type.
     *
     * @return the response type
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Sets response type.
     *
     * @param responseType the response type
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
