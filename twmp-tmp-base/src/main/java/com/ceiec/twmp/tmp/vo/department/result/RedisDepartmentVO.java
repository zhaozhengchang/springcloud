package com.ceiec.twmp.tmp.vo.department.result;

import javax.validation.constraints.NotNull;

/**
 * @author Ding
 * @version V1.0
 * @Description: department redis info
 * @create 2019-04-10 10:28
 **/
public class RedisDepartmentVO {

    private Long departmentId;

    private String departmentName;

    private Long parentId;

    /** contact */
    private String contacts;

    /** phone */
    private String phone;

    /** fax */
    private String fax;

    /** address */
    private String address;

    /** mapCenter */
    private String mapCenter;

    /**
     * 组织机构等级，不可超过5级
     */
    private Byte level;
    /**
     * 组织机构编码
     */
    private String departmentCode;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
