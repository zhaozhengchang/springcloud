package com.ceiec.twmp.tmp.vo.department.result;

import com.ceiec.twmp.tmp.ResponseType;

import java.io.Serializable;

/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: The type department save result vo.
 */
public class DepartmentAddResultVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = 7985322981484631338L;

    /** save result */
    private ResponseType responseType = ResponseType.SUCCESS;
    /** departmentID */
    private Long departmentId;

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
     * Gets department id.
     *
     * @return the department id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets department id.
     *
     * @param departmentId the department id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentAddResultVO(ResponseType responseType) {
        this.responseType = responseType;
    }
    public DepartmentAddResultVO() {

    }
}
