package com.ceiec.twmp.tmp.authorize_interface.vo;

import java.io.Serializable;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: store datasource information for login
 **/
public class DatasourceInfoVO implements Serializable {

    /** serializable number */
    private static final long serialVersionUID = -1874596440912486486L;

    /** driver class name */
    private String driverClassName;

    /** datasource url */
    private String dsUrl;

    /** datasource username */
    private String dsUserName;

    /** datasource password */
    private String dsPassword;

    /** allow fail times */
    private Integer allowFailTimes;

    /** allow retry interval(minutes) after fail too many times */
    private Integer allowRetryInterval;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDsUrl() {
        return dsUrl;
    }

    public void setDsUrl(String dsUrl) {
        this.dsUrl = dsUrl;
    }

    public String getDsUserName() {
        return dsUserName;
    }

    public void setDsUserName(String dsUserName) {
        this.dsUserName = dsUserName;
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword;
    }

    public Integer getAllowFailTimes() {
        return allowFailTimes;
    }

    public void setAllowFailTimes(Integer allowFailTimes) {
        this.allowFailTimes = allowFailTimes;
    }

    public Integer getAllowRetryInterval() {
        return allowRetryInterval;
    }

    public void setAllowRetryInterval(Integer allowRetryInterval) {
        this.allowRetryInterval = allowRetryInterval;
    }
}
