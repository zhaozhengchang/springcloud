package com.ceiec.twmp.tmp.vo.monitor.delete;

import java.util.Date;

/**
 * @version V1.0
 * @title: MonitorGuardianDeleteVO </br>
 * @createDate：2019/3/14 11:24 </br>
 * @author：shihsh </br>
 * @description: 删除的监护成员VO </br>
 **/


public class MonitorGuardianDeleteVO {

    private Long teamId;

    private String updater;

    private Long updaterId;

    private Date updateTime;



    /**
     * 删除标志位
     */
    private Byte deleted;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
