package com.ceiec.twmp.tmp.common.dict;

/**
 * @title: PersonStatus </br>
 * @createDate: 2019/3/27 14:38 </br>
 * @author: shihsh  </br>
 * @description: 被监控人员的监控状态 </br>
 * @version: V1.0
 **/


public enum  PersonStatus {
    /*****前端显示的被监控人员状态******/
    /**全部**/
    total((byte)0, "全部", "dict.personStatus.total"),

    /**未监控**/
    unmonitored((byte)1, "未监控", "dict.personStatus.unMonitored"),

    /**监控中**/
    monitoring((byte)2, "监控中", "dict.personStatus.monitoring"),

    /**已结束**/
    over((byte)3, "已结束", "dict.personStatus.over");


    /** type of dict **/
    public String type = "personStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;

    PersonStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    PersonStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static PersonStatus get(byte value){
        for(PersonStatus dic:PersonStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
