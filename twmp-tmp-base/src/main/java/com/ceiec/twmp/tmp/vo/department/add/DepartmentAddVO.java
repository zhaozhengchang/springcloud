package com.ceiec.twmp.tmp.vo.department.add;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: The type departmen save  vo.
 **/
public class DepartmentAddVO implements Serializable {
    /** serializable ID */
    private static final long serialVersionUID = -2535338389614671637L;

    /** departmentName */
    @NotNull(message = "departmentName can not be null or empty string.")
    private String departmentName;

    /** parentDepartmentId */
    @NotNull(message = "parentId can not be null or empty string.")
    private Long parentId;

    private Long departmentId;

    /** contact */
    private String contacts;

    /** phone */
    private String phone;

    /** fax */
    private String fax;

    /** address */
    private String address;

    /** mapCenter */
    @NotNull(message = "mapCenter can not be null or empty string.")
    private String mapCenter;

    /**
     * 组织机构等级，不可超过5级
     */
    @NotNull(message = "level can not be null or empty string.")
    private Byte level;
    /**
     * 组织机构编码
     */
    @NotNull(message = "departmentCode can not be null or empty string.")
    private String departmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}
