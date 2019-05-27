package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: message sub type
 * @create 2019-03-01 14:26
 **/
public enum  MessageSubType {
    /**未及时汇报通知**/
    failureToReportInTime((byte)1, "未及时汇报通知", "dict.messageSubType.failureToReportInTime",
            "messageType", (byte)2,"dict.messageSubType.failureToReportInTime.comment"),


    /**审批通知**/
    approval((byte)2, "审批通知", "dict.messageSubType.approval",
            "messageType", (byte)2,"dict.messageSubType.approval.comment"),


    /**审批通过通知**/
    approvalPass((byte)3, "审批通过通知", "dict.messageSubType.approvalPass",
            "messageType", (byte)2,"dict.messageSubType.approvalPass.comment"),


    /**审批未通过通知**/
    approvalNotPass((byte)4, "审批未通过通知", "dict.messageSubType.approvalNotPass",
            "messageType", (byte)2,"dict.messageSubType.approvalNotPass.comment"),


    /**安装通知**/
    install((byte)5, "安装通知", "dict.messageSubType.install",
            "messageType", (byte)2,"dict.messageSubType.install.comment"),


    /**安装完成通知**/
    installCompleted((byte)6, "安装完成通知", "dict.messageSubType.installCompleted",
            "messageType", (byte)2,"dict.messageSubType.installCompleted.comment"),


    /**安装延期通知**/
    installDelay((byte)7, "安装延期通知", "dict.messageSubType.installDelay",
            "messageType", (byte)2,"dict.messageSubType.installDelay.comment"),


    /**拆机通知**/
    dismantle((byte)8, "拆机通知", "dict.messageSubType.dismantle",
            "messageType", (byte)2,"dict.messageSubType.dismantle.comment"),


    /**拆机完成通知**/
    dismantleCompleted((byte)9, "拆机完成通知", "dict.messageSubType.dismantleCompleted",
            "messageType", (byte)2,"dict.messageSubType.dismantleCompleted.comment"),


    /**更换设备通知**/
    changeDevice((byte)10, "更换设备通知", "dict.messageSubType.changeDevice",
            "messageType", (byte)2,"dict.messageSubType.changeDevice.comment"),


    /**更换设备完成通知**/
    changeDeviceComplete((byte)11, "更换设备完成通知", "dict.messageSubType.changeDeviceComplete",
            "messageType", (byte)2,"dict.messageSubType.changeDeviceComplete.comment"),


    /**监控任务开始通知**/
    taskStart((byte)12, "监控任务开始通知", "dict.messageSubType.taskStart",
            "messageType", (byte)2,"dict.messageSubType.taskStart.comment"),


    /**监控任务完成通知**/
    taskOver((byte)13, "监控任务完成通知", "dict.messageSubType.taskOver",
            "messageType", (byte)2,"dict.messageSubType.taskOver.comment"),


    /**监控任务终止通知**/
    taskStop((byte)14, "监控任务终止通知", "dict.messageSubType.taskStop",
            "messageType", (byte)2,"dict.messageSubType.taskStop.comment"),




    /**暴力拆卸告警**/
    violentDismantling((byte)15, "暴力拆卸告警", "dict.messageSubType.violentDismantling",
            "messageType", (byte)1, "dict.messageSubType.violentDismantling.comment"),


    /**低电量告警**/
    lowPower((byte)16, "低电量告警", "dict.messageSubType.lowPower",
            "messageType", (byte)1, "dict.messageSubType.lowPower.comment"),


    /**越界告警**/
    overFence((byte)17, "越界告警", "dict.messageSubType.overFence",
            "messageType", (byte)1, "dict.messageSubType.overFence.comment"),


    /**设备离线告警**/
    offline((byte)18, "设备离线告警", "dict.messageSubType.offline",
            "messageType", (byte)1, "dict.messageSubType.offline.comment"),


    /**手动创建告警**/
    manuallyCreate((byte)19, "手动创建告警", "dict.messageSubType.manuallyCreate",
            "messageType", (byte)1, "dict.messageSubType.manuallyCreate.comment"),


    /**一键求助告警**/
    sos((byte)20, "一键求助告警", "dict.messageSubType.sos",
            "messageType", (byte)1, "dict.messageSubType.sos.comment");



    /** type of dict **/
    public String type = "messageSubType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;
    /**parent type**/
    public String parentType;
    /**parent value**/
    public byte parentValue;


    MessageSubType(byte value, String name, String nameCode, String parentType, byte parentValue ) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.parentType = parentType;
        this.parentValue = parentValue;
    }

    MessageSubType(byte value, String name, String nameCode, String parentType, byte parentValue, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.parentType = parentType;
        this.parentValue = parentValue;
        this.comment = comment;
    }

    public static MessageSubType get(byte value){
        for(MessageSubType dic:MessageSubType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

 }
