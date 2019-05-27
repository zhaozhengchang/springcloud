package com.ceiec.twmp.tmp.vo.department.delete;

import java.util.Date;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-11 15:45
 * Description:
 **/

public class DepartmentDleteVO {

    /** serializable ID */
    private static final long serialVersionUID = -2535337449614671637L;

    private Long departmentId;
    private Long updaterId;
    private String updater;
    private Date updateTime;
    private Byte deleted;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}
