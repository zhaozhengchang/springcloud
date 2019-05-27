package com.ceiec.twmp.tmp.vo;

import java.io.Serializable;

/**
 * CreateDate：2018/9/7 <br/>
 * Author：wenliang <br/>
 * Description: vo for system parameter information
 **/
public class SystemParamVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -8735338789614671656L;

    /** parameter ID */
    private int parameterID;

    /** parameter name */
    private String parameterName;

    /** parameter type */
    private int parameterType;

    /** parameter value */
    private double parameterValue;

    /** unit of parameter value */
    private String valueUnit;

    /** parameter description */
    private String parameterDesc;

    public int getParameterID() {
        return parameterID;
    }

    public void setParameterID(int parameterID) {
        this.parameterID = parameterID;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public int getParameterType() {
        return parameterType;
    }

    public void setParameterType(int parameterType) {
        this.parameterType = parameterType;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }
}
