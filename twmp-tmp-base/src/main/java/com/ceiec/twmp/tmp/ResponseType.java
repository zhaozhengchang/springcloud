package com.ceiec.twmp.tmp;

import com.ceiec.twmp.tmp.utils.response.EModuleCode;
import com.ceiec.twmp.tmp.utils.response.IResponseType;

/**
 * CreateDate：2018/8/29 <br/>
 * Author：wenliang <br/>
 * Description: store response type information of insight guide project
 **/
public enum ResponseType implements IResponseType {

    /** request success */
    SUCCESS(IResponseType.SUCCESS, "request success"),
    /** illegal parameter */
    ILLEGAL_PARAMETER(IResponseType.ILLEGAL_PARAMETER, "illegal parameter"),
    /** unknown internal server error */
    UNKNOWN(IResponseType.UNKNOWN, "unknown internal server error"),
    /** illegal token */
    ILLEGAL_TOKEN(EModuleCode.MODULE_GUIDE.getModuleCode() + "1201", "account status error, please login again"),
    /** current user is kicked by others */
    KICKED_USER_TOKEN(EModuleCode.MODULE_GUIDE.getModuleCode() + "1200", "current user is kicked by others"),
    /** selected materials number don't match selected accounts number */
    MATERIAL_NUMBER_UNMATCH("1300", "selected materials number don't match selected accounts number"),
    /** user name already exist */
    USERNAME_EXIST("1104", "user name already exist"),
    /** user password not exist */
    OLD_PASSWORD_ERROR("1105", "old password error"),
    /** departmen name already exist */
    DEPARTMENTNAME_EXIST("1106", "departmen name already exist"),

    FAIL("2000", "operation failed"),
    USER_NAME_EXISTED("2001", "userName existed"),
    ROLE_NAME_EXISTED("2002", "roleName existed"),
    PAPERWORK_NAME_EXISTED("2003", "paperwork name existed"),
    DEPARTMENT_NAME_EXISTED("2004", "department name existed"),
    DEPARTMENT_CODE_EXISTED("2005", "department code existed"),
    DELETE_DEPARTMENT_FAILED("2010", "this department Bound user or device");


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