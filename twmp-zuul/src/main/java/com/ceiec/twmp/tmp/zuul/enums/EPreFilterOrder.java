package com.ceiec.twmp.tmp.zuul.enums;

/**
 * CreateDate：2018/5/17 <br/>
 * Author：wenliang <br/>
 * Description: enums for custom pre filter order in zuul module
 **/
public enum EPreFilterOrder {

    /** order for AccessFilter */
    ORDER_ACCESS(6, "pre filter order for AccessFilter"),
    /** order for LoginPreFilter */
    ORDER_LOGIN_PRE(7, "pre filter order for LoginPreFilter");

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
    private EPreFilterOrder(int orderValue, String orderDesc) {
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
