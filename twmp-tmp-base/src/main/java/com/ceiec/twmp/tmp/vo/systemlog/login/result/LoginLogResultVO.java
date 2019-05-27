package com.ceiec.twmp.tmp.vo.systemlog.login.result;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;

/**
 * @version V1.0
 * @title: LoginLogResultVO </br>
 * @createDate: 2019/3/18 21:50 </br>
 * @author: shihsh </br>
 * @description: 登陆日志查询结果 </br>
 **/


public class LoginLogResultVO extends PageParentVO {
    private static final long serialVersionUID = -6911820584404617143L;

    /**
     * 日志记录Id
     */
    private Long operateId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆时间日期格式
     */
    private Date loginTimeDate;

    /**
     * 登陆时间字符串格式
     */
    private String loginTime;

    /**
     * 退出时间日期格式
     */
    private Date logoutTimeDate;

    /**
     * 退出时间，字符串格式
     */
    private String logoutTime;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 登陆用户所属组织机构
     */
    private String departmentName;

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoginTimeDate() {
        return loginTimeDate;
    }

    public void setLoginTimeDate(Date loginTimeDate) {
        this.loginTimeDate = loginTimeDate;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTimeDate() {
        return logoutTimeDate;
    }

    public void setLogoutTimeDate(Date logoutTimeDate) {
        this.logoutTimeDate = logoutTimeDate;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
