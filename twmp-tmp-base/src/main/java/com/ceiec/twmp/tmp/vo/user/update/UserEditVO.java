package com.ceiec.twmp.tmp.vo.user.update;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * CreateDate：2019/1/14 <br/>
 * Author：wenliang <br/>
 * Description: The type User Edit vo.
 */
public class UserEditVO implements Serializable{
    /** serializable ID */
    private static final long serialVersionUID = 3535324281614671632L;

    /** user ID */
    private Long userId;

    /** email */
    private String email;

    /** phone */
    private String phone;

    /** fax */
    private String fax;

    /** photoId */
    private Long photoId;

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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets fax.
     *
     * @param fax the fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
}
