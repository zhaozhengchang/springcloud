package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: task level
 * @create 2019-03-01 15:21
 **/
public enum  TaskLevel {
    /**高**/
    high((byte)1, "高", "dict.taskLevel.high"),
    /**中**/
    medium((byte)2, "中", "dict.taskLevel.medium"),
    /**低**/
    low((byte)3, "低", "dict.taskLevel.low");

    /** type of dict **/
    public String type = "taskLevel";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    TaskLevel(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    TaskLevel(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static TaskLevel get(byte value){
        for(TaskLevel dic:TaskLevel.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
