package com.ceiec.twmp.tmp.vo;

import java.io.Serializable;

/**
 * CreateDate：2018/5/8 <br/>
 * Author：wenliang <br/>
 * Description: store system log info
 **/
public class SystemLogVO implements Serializable {

    /** serialize number */
    private static final long serialVersionUID = -6842399476428281999L;

    /** operator id */
    private String operatorID;

    /** log content */
    private String logContent;

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }
}
