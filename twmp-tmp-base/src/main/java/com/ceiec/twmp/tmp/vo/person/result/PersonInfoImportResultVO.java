package com.ceiec.twmp.tmp.vo.person.result;

import java.io.Serializable;

/**
 * @title: PersonInfoImportResultVO </br>
 * @createDate: 2019/3/29 17:36 </br>
 * @author: shihsh  </br>
 * @description: 通过Excel导入被监控人员信息返回结果VO </br>
 * @version: V1.0
 **/


public class PersonInfoImportResultVO implements Serializable {
    private static final long serialVersionUID = -4685085916662689223L;

    private String success;

    private String failed;

    private String necessaryMissing;

    private String wrongDepartment;

    private String  typeError;

    private String idCardExist;

    private String idRepeated;

    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getNecessaryMissing() {
        return necessaryMissing;
    }

    public void setNecessaryMissing(String necessaryMissing) {
        this.necessaryMissing = necessaryMissing;
    }

    public String getWrongDepartment() {
        return wrongDepartment;
    }

    public void setWrongDepartment(String wrongDepartment) {
        this.wrongDepartment = wrongDepartment;
    }

    public String getTypeError() {
        return typeError;
    }

    public void setTypeError(String typeError) {
        this.typeError = typeError;
    }

    public String getIdCardExist() {
        return idCardExist;
    }

    public void setIdCardExist(String idCardExist) {
        this.idCardExist = idCardExist;
    }

    public String getIdRepeated() {
        return idRepeated;
    }

    public void setIdRepeated(String idRepeated) {
        this.idRepeated = idRepeated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
