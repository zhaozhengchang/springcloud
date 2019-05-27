package com.ceiec.twmp.tmp.zuul.enums;

/**
 * CreateDate：2018/4/24 <br/>
 * Author：wenliang <br/>
 * Description: enums for filter type in zuul module
 **/
public enum EFilterType {

    /** call filter before forward */
    FILTER_PRE("pre", "call filter before forward"),
    /** call filter after forward or error */
    FILTER_POST("post", "call filter after forward or error"),
    /** call filter when error happens */
    FILTER_ERROR("error", "call filter when error happens");

    /** filter type value */
    private String filterType;

    /** description for filter type value */
    private String description;

    /**
     * construction function
     *
     * @param filterType filter type value
     * @param description decription for filter type value
     */
    private EFilterType(String filterType, String description) {
        this.filterType = filterType;
        this.description = description;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getDescription() {
        return description;
    }
}
