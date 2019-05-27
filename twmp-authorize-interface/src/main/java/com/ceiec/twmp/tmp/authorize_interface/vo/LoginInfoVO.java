package com.ceiec.twmp.tmp.authorize_interface.vo;

import java.io.Serializable;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: store user's login information
 **/
public class LoginInfoVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -3506996171788213464L;

    /** system code, necessary */
    private Integer systemCode;

    /** login username, necessary */
    private String userName;

    /** login password, necessary */
    private String password;

    /** request ip */
    private String requestIP;

    /** datasource information */
    private DatasourceInfoVO datasourceInfo;

    /** user table information */
    private UserTableInfoVO userTableInfo;

    public Integer getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(Integer systemCode) {
        this.systemCode = systemCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public DatasourceInfoVO getDatasourceInfo() {
        return datasourceInfo;
    }

    public void setDatasourceInfo(DatasourceInfoVO datasourceInfo) {
        this.datasourceInfo = datasourceInfo;
    }

    public UserTableInfoVO getUserTableInfo() {
        return userTableInfo;
    }

    public void setUserTableInfo(UserTableInfoVO userTableInfo) {
        this.userTableInfo = userTableInfo;
    }
}
