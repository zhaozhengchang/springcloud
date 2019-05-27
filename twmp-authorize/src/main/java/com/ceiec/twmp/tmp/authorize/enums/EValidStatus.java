package com.ceiec.twmp.tmp.authorize.enums;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: enums for user's valid status
 **/
public enum EValidStatus {
    /** valid user */
    STATUS_VALID(1, "valid user"),
    /** invalid user */
    STATUS_INVALID(2, "invalid user");

    /** valid status value */
    private int statusValue;

    /** valid status description */
    private String statusDesc;

    /**
     * private construction function
     *
     * @param statusValue valid status value
     * @param statusDesc valid status description
     */
    EValidStatus(int statusValue, String statusDesc) {
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
