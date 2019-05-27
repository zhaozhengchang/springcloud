package com.ceiec.twmp.tmp.utils.tools.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ding
 * @version V1.0
 * @Description: mongodb config
 * @create 2019-03-19 15:44
 **/
@Configuration
public class MongodbConfig {
    @Value("${mongodb.mode}")
    private String mode;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private String port;
    @Value("${mongodb.connect.timeout}")
    private String timeout;
    @Value("${mongodb.wait.time}")
    private String waitTime;
    @Value("${mongodb.connect.count}")
    private String connectCount;


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(String connectCount) {
        this.connectCount = connectCount;
    }
}
