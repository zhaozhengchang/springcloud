package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: task status
 * @create 2019-03-01 15:56
 **/
public enum  TaskStatus {

    /*****以下的任务状态是初始化状态******/
    /**初始化**/
    initial((byte)10, "初始化", "dict.taskStatus.initial"),
    /**审批中**/
    initial_approval((byte)11, "审批中",""),
    /**待安装**/
    initial_wait_to_install((byte)12, "待安装",""),
    /**安装完成**/
    initial_installed((byte)13, "安装完成",""),


    /*****以下的任务状态是监控中的任务状态******/
    /**监控中**/
    monitoring((byte)20, "监控中", "dict.taskStatus.start"),


    /******以下的任务状态是监控完成的任务状态*****/
    /**已完成**/
    completed((byte)30, "已完成", "dict.taskStatus.completed"),
    /**待拆机**/
    completed_wait_to_dismantle((byte)31, "待拆机",""),


    /******以下的任务状态是监控结束的任务状态*****/
    /**已结束**/
    over((byte)40, "已结束", "dict.taskStatus.over");





    /** type of dict **/
    public String type = "taskStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    TaskStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    TaskStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static TaskStatus get(byte value){
        for(TaskStatus dic:TaskStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }


}
