package com.ceiec.twmp.tmp.zuul.enums;

/**
 * CreateDate：2018/5/17 <br/>
 * Author：wenliang <br/>
 * Description: enums for custom post filter order in zuul module
 **/
public enum EPostFilterOrder {

    /** order for LoginPostFilter */
    ORDER_LOGIN_POST(1, "post filter order for LoginPostFilter"),
    /** order for LogoutPostFilter */
    ORDER_LOGOUT_POST(2, "post filter order for LogoutPostFilter"),
    /** order for ExceptionHandleFilter */
    ORDER_EXCEPTION_POST(100, "post filter order for ExceptionHandleFilter");

    /** order value */
    private int orderValue;

    /** order description */
    private String orderDesc;

    /**
     * construction function
     *
     * @param orderValue order value
     * @param orderDesc order description
     */
    private EPostFilterOrder(int orderValue, String orderDesc) {
        this.orderValue = orderValue;
        this.orderDesc = orderDesc;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public String getOrderDesc() {
        return orderDesc;
    }
}
