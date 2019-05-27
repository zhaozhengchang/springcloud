package com.ceiec.twmp.tmp.common.dict;

/**
 * @title: MonitorTaskChangeType </br>
 * @createDate: 2019/3/22 11:41 </br>
 * @author: shihsh  </br>
 * @description: 监控任务参数变更类型,主要用于区分是参数变更前的参数，还是变更之后的参数： 1 当前的监控任务详情  2 参数变更之前的监控任务详情 </br>
 * @version: V1.0
 **/

public enum MonitorTaskChangeType {

    /**
     * 监控任务参数变更类型
     * current表示查询当前前的监控任务，即查询twmp_monitor_task_ef表中的监控任务数据
     * befor表示查询参数变更之前的监控任务，即查询twmp_monitor_task_change_ef表中的监控任务数据
     */
    current((byte)1, "查询当前监控任务详情", "dict.monitorTaskChangeType.current"),
    before((byte)2, "查询参数变更前的监控任务详情", "dict.monitorTaskChangeType.before");

    /** type of dict **/
    public String type = "monitorTaskChangeType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    MonitorTaskChangeType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    MonitorTaskChangeType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static MonitorTaskChangeType get(byte value){
        for(MonitorTaskChangeType dic:MonitorTaskChangeType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
