package com.ceiec.twmp.tmp.authorize.vo;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: store system user information
 **/
public class UserInfoVO {

    /** user ID */
    private Long userId;

    /** user login name */
    private String userName;

    /** user login password */
    private String password;

    /** user delete status */
    private Integer deleted;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    
}
