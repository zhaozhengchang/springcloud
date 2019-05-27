package com.ceiec.twmp.tmp.vo;

import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/10/12 <br/>
 * Author：wenliang <br/>
 * Description: vo for system user(guide) information
 **/
public class SystemUserVO implements Serializable {

    /** serializable ID */
    private static final long serialVersionUID = -3910757795373048978L;

    /** user ID */
    private String userId;

    /** user avatar */
    private String avatar;

    /** user login username */
    private String username;

    /** user login password */
    private String password;

    /** user nickname */
    private String nickname;

    /** user role */
    private int role;

    /** user description */
    private String description;

    /** user creation time */
    private Date creationTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationTime() {
        return DateUtils.format(creationTime);
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
