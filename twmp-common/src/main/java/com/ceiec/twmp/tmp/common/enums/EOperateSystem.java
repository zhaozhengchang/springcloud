package com.ceiec.twmp.tmp.common.enums;

import java.util.Objects;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: enums for operate system(need login system)
 **/
public enum EOperateSystem {

    /** web insight system */
    WEB_TWMP(1, "twmp-tmp", "twmp system"),
    /** web guide system */
    WEB_TRUNK(2, "twmp-trunk", "trunk system");

    /** system code */
    private int systemCode;

    /** application name for micro service */
    private String appName;

    /** system description */
    private String systemDesc;

    /**
     * private construction function
     *
     * @param systemCode system code
     * @param appName application name for micro service
     * @param systemDesc system description
     */
    EOperateSystem(int systemCode, String appName, String systemDesc) {
        this.systemCode = systemCode;
        this.appName = appName;
        this.systemDesc = systemDesc;
    }

    public int getSystemCode() {
        return systemCode;
    }

    public String getAppName() {
        return appName;
    }

    public String getSystemDesc() {
        return systemDesc;
    }

    /**
     * transfer system code value to system enum
     *
     * @param systemCode system code value
     * @return system enum
     */
    public static EOperateSystem getSystemEnum(Integer systemCode) {
        if (Objects.isNull(systemCode)) {
            throw new IllegalArgumentException("system code is necessary");
        }
        for (EOperateSystem system : EOperateSystem.values()) {
            if (system.systemCode == systemCode) {
                return system;
            }
        }
        throw new IllegalArgumentException("illegal system code param: " + systemCode);
    }
}
