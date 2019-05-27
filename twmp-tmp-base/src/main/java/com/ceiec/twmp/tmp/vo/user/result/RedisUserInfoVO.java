package com.ceiec.twmp.tmp.vo.user.result;

import com.ceiec.twmp.tmp.common.dict.MessageSubType;
import com.ceiec.twmp.tmp.vo.authority.result.AuthorityTreeVO;
import com.ceiec.twmp.tmp.vo.department.result.DepartmentTreeVO;

import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: the cache info after user login
 * @create 2019-02-28 16:16
 **/
public class RedisUserInfoVO {

    private Long userId;

    private String token;

    private String userName;

    private Long roleId;

    private String roleName;

    private Long departmentId;

    private String departmentName;

    private String phone;

    private String language;

    private String topic;

    private Long photoId;

    private String fax;

    private String email;

    private String mapCenter;
    /** the departmentId which user has authority **/
    private String ownDepartmentId;

    /** user login time **/
    private Date loginTime;

    private List<AuthorityTreeVO> authorityTree;

    private DepartmentTreeVO departmentTree;

    //id for login log
    private Long loginId;

    private Integer sessionTime;

    private List<MessageSubType> messageSubTypes;

    public List<MessageSubType> getMessageSubTypes() {
        return messageSubTypes;
    }

    public void setMessageSubTypes(List<MessageSubType> messageSubTypes) {
        this.messageSubTypes = messageSubTypes;
    }

    public Integer getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Integer sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AuthorityTreeVO> getAuthorityTree() {
        return authorityTree;
    }

    public void setAuthorityTree(List<AuthorityTreeVO> authorityTree) {
        this.authorityTree = authorityTree;
    }

    public DepartmentTreeVO getDepartmentTree() {
        return departmentTree;
    }

    public void setDepartmentTree(DepartmentTreeVO departmentTree) {
        this.departmentTree = departmentTree;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(String mapCenter) {
        this.mapCenter = mapCenter;
    }

    public String getOwnDepartmentId() {
        return ownDepartmentId;
    }

    public void setOwnDepartmentId(String ownDepartmentId) {
        this.ownDepartmentId = ownDepartmentId;
    }

}
