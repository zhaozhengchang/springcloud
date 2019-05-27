package com.ceiec.twmp.tmp.vo.parameter.edit;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-18 15:39
 * Description:
 **/

public class SysParameterEditParaVO implements Serializable {

    private static final long serialVersionUID = -3244807277999243853L;

    @NotNull(message = "parameterId is not null ")
    private Long parameterId;
    @NotNull(message = "parameterValue is not null ")
    private String parameterValue;
    private String parameterName;
    private String parameterRange;
    private String parameterUnit;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterRange() {
        return parameterRange;
    }

    public void setParameterRange(String parameterRange) {
        this.parameterRange = parameterRange;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }
}
