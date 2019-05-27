package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: login status
 * @create 2019-03-01 16:25
 **/
public enum  LoginStatus {
    /**登录**/
    login((byte)1, "登录", "dict.loginStatus.login"),
    /**退出**/
    logout((byte)2, "退出", "dict.loginStatus.logout");

    /** type of dict **/
    public String type = "loginStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    LoginStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    LoginStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static LoginStatus get(byte value){
        for(LoginStatus dic:LoginStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
