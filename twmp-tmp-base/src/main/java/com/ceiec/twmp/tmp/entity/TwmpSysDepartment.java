package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "twmp_sys_department")
public class TwmpSysDepartment {
    /**
     * id
     */
    @Id
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 组织名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 父级（上级）组织机构
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户地图中心区域格式：（经度，纬度，地图层级）  
     */
    @Column(name = "map_center")
    private String mapCenter;

    /**
     * 删除标志(1未删除2删除)
     */
    private Byte deleted;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人id
     */
    private Long creatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updater;

    private Long updaterId;

    private Date updateTime;
    /**
     * 组织机构等级
     */
    private Byte level;

    /**
     * 组织机构编码
     */
    @Column(name = "department_code")
    private String departmentCode;

    /**累计的监控任务数量**/
    @Column(name = "task_num")
    private int taskNum;

    /**累计的被监控人数量**/
    @Column(name = "person_num")
    private int personNum;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * 获取id
     *
     * @return department_id - id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置id
     *
     * @param departmentId id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取组织名称
     *
     * @return department_name - 组织名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置组织名称
     *
     * @param departmentName 组织名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 获取父级（上级）组织机构
     *
     * @return parent_id - 父级（上级）组织机构
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级（上级）组织机构
     *
     * @param parentId 父级（上级）组织机构
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取用户地图中心区域格式：（经度，纬度，地图层级）  
     *
     * @return map_center - 用户地图中心区域格式：（经度，纬度，地图层级）  
     */
    public String getMapCenter() {
        return mapCenter;
    }

    /**
     * 设置用户地图中心区域格式：（经度，纬度，地图层级）  
     *
     * @param mapCenter 用户地图中心区域格式：（经度，纬度，地图层级）  
     */
    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }

    /**
     * 获取删除标志(1未删除2删除)
     *
     * @return deleted - 删除标志(1未删除2删除)
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志(1未删除2删除)
     *
     * @param deleted 删除标志(1未删除2删除)
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }
}