package com.ceiec.twmp.tmp.vo.user.update;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * CreateDate：2019/1/14 <br/>
 * Author：wenliang <br/>
 * Description:Edit User Password vo.
 */
public class UserPassEditVO implements Serializable{
    /** serializable ID */
    private static final long serialVersionUID = 8535591581614671392L;

    /** user ID */
    private Long userId;

    /** 原密碼 password */
    @NotNull(message = "message.common.message.notnull")
    private String password;

    /** 新密码 */
    @NotNull(message = "message.common.message.notnull")
    private String newPassword;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets new password.
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets new password.
     *
     * @param newPassword the new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
