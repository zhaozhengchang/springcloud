package com.ceiec.twmp.tmp.vo.person.result;

import com.ceiec.twmp.tmp.ResponseType;

import java.io.Serializable;

/**
 * CreateDate：2019/3/1 16:34 </br>
 * Author：shihsh  </br>
 * Description: TODO </br>
 **/


public class PersonDataResultVO implements Serializable {

    private static final long serialVersionUID = 1005595898334017391L;

    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;

    private Long personId;

    private String personName;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
