package com.ceiec.twmp.tmp.vo.user.result;

/**
 * @author Ding
 * @version V1.0
 * @Description: user online vo
 * @create 2019-03-12 13:50
 **/
public class RedisUserOnlineVO {

    private Long userId;

    private String userName;

    private String token;

    private String serverId;
    /** login log id **/
    private Long loginId;
    /** the num of alarm automatic allocation which user own , default is 0 **/
    private Integer alarmAutomaticAllocationNum = 0;

    private String ownDepartmentId;

    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getOwnDepartmentId() {
        return ownDepartmentId;
    }

    public void setOwnDepartmentId(String ownDepartmentId) {
        this.ownDepartmentId = ownDepartmentId;
    }

    public Integer getAlarmAutomaticAllocationNum() {
        return alarmAutomaticAllocationNum;
    }

    public void setAlarmAutomaticAllocationNum(Integer alarmAutomaticAllocationNum) {
        this.alarmAutomaticAllocationNum = alarmAutomaticAllocationNum;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
