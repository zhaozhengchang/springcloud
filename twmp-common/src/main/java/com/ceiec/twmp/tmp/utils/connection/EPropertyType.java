package com.ceiec.twmp.tmp.utils.connection;

/**
 * CreateDate：2018/4/23 <br/>
 * Author：wenliang <br/>
 * Description: enums for external property file type
 **/
public enum EPropertyType {

    /** common property file */
    PROPERTY_COMMON(0, "common property file"),
    /** actual project property file */
    PROPERTY_PROJECT(1, "actual project property file");

    /** file type value */
    private int typeValue;

    /** file type description */
    private String typeDesc;

    /**
     * construction function
     *
     * @param typeValue type value param
     * @param typeDesc type description param
     */
    private EPropertyType(int typeValue, String typeDesc) {
        this.typeValue = typeValue;
        this.typeDesc = typeDesc;
    }

    /**
     * get file type value
     *
     * @return file type value
     */
    public int getTypeValue() {
        return typeValue;
    }

    /**
     * get file type description
     *
     * @return file type description
     */
    public String getTypeDesc() {
        return typeDesc;
    }
}
