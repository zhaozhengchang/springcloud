package com.ceiec.twmp.tmp.vo.person.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * CreateDate：2019/3/6 22:53 </br>
 * Author：shihsh  </br>
 * Description: 被监控人员查询VO </br>
 **/


public class PersonQueryVO extends PageParentVO {

    private static final long serialVersionUID = 556130559452604638L;

    /** personId **/
    private Long personId;

    /** person name **/
    private String personName;

    /** 被监控人编号 **/
    private String personNumber;

    /** 设备编号 **/
    private String deviceNumber;

    /** 组织机构ID  **/
    private Long departmentId;

    /** 当前用户拥有权限的组织机构 */
    private List<Long> departmentIds;

    /** 被监控人状态 **/
    private Byte personStatus;

    /**
     * 监控任务状态，与personStatus相对应，需要转换为数据库中的状态值
     */
    private List<Byte> taskStatusList = new ArrayList<>();

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Byte getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(Byte personStatus) {
        this.personStatus = personStatus;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<Byte> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<Byte> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }
}
