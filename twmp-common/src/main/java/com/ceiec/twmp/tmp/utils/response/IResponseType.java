package com.ceiec.twmp.tmp.utils.response;

/**
 * CreateDate：2018/4/26 <br/>
 * Author：wenliang <br/>
 * Description: common return code for each project
 **/
public interface IResponseType {

    /** request success */
    String SUCCESS = "10000";

    /** request fails */
    String FAILS = "11111";

    /** error in gateway */
    String ZUUL_ERROR = EModuleCode.MODULE_COMMON.getModuleCode() + "0001";

    /** error in routed project */
    String ROUTE_PROJECT_ERROR = EModuleCode.MODULE_COMMON.getModuleCode() + "0002";

    /** illegal request param */
    String ILLEGAL_PARAMETER = "99991";

    /** unknown internal server error */
    String UNKNOWN = "99999";

    /**
     * get return code
     *
     * @return return code
     */
    public String getCode();

    /**
     * get code description
     *
     * @return return code description
     */
    public String getDesc();
}
