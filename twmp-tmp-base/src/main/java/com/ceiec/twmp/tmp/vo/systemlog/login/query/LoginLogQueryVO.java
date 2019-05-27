package com.ceiec.twmp.tmp.vo.systemlog.login.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @title: LoginLogQueryVO </br>
 * @createDate: 2019/3/18 21:48 </br>
 * @author: shihsh </br>
 * @description: 登陆日志查询VO </br>
 **/


public class LoginLogQueryVO extends PageParentVO {

    private static final long serialVersionUID = 8815887546378882002L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 开始时间字符串格式
     */
    private String startTime;

    /**
     * 开始时间
     */
    private Date startTimeDate;

    /**
     * 结束时间字符串格式
     */
    private String endTime;

    /**
     * 结束时间
     */
    private Date endTimeDate;

    /**
     * 组织机构权限管理
     */
    private List<Long> departmentIds;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
