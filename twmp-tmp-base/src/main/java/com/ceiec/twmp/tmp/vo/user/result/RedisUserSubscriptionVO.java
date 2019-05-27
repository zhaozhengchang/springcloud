package com.ceiec.twmp.tmp.vo.user.result;

import java.util.Set;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-03-14 14:47
 **/
public class RedisUserSubscriptionVO {

    private Long userId;

    private Set<String> deviceNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Set<String> deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}
