package com.ceiec.twmp.tmp.authorize.enums;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: enums for user's delete status
 **/
public enum EDeleteStatus {

    /** user is not deleted */
    NOT_DELETED(1, "user is not deleted"),
    /** user is deleted */
    DELETED(2, "user is deleted");

    /** delete status value */
    private int statusValue;

    /** delete status description */
    private String statusDesc;

    /**
     * private construction function
     *
     * @param statusValue delete status value
     * @param statusDesc delete status description
     */
    EDeleteStatus(int statusValue, String statusDesc) {
        this.statusValue = statusValue;
        this.statusDesc = statusDesc;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
