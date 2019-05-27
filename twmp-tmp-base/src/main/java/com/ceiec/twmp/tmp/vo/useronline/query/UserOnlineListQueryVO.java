package com.ceiec.twmp.tmp.vo.useronline.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.io.Serializable;

/**
 * CreateDate：2019/1/16 <br/>
 * Author：wenliang <br/>
 * Description: The type User Online list query vo.
 */
public class UserOnlineListQueryVO extends PageParentVO implements Serializable {
    /**
     * serializable ID
     */
    private static final long serialVersionUID = 1635323781424621631L;
    /**
     * userName
     */
    private String userName;

    /**
     * status 0-全部，1-在线，2-下线
     */
    private int status;

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
