package com.ceiec.twmp.tmp.utils.tools.ftp;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * FTPClient连接池的实现类
 * @author zks
 */
public class FTPClientPool {
    private GenericObjectPool<FTPClient> ftpClientPool;
    private FTPConfig config;

    public FTPClientPool(FTPConfig ftpConfig){
        // 初始化对象池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxTotal(50);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestWhileIdle(false);
        poolConfig.setLifo(false);
        config = ftpConfig;

        // 初始化对象池
        ftpClientPool = new GenericObjectPool<FTPClient>(new FTPClientFactory(ftpConfig), poolConfig);
    }
    public FTPClient borrowObject() throws Exception {
        return new FTPClientFactory(config).create();
    }

    public void returnObject(FTPClient ftpClient){
        disConnect(ftpClient);
    }

    /**
     * 断开连接
     * @param ftpClient
     */
    private static void disConnect(FTPClient ftpClient) {
        if (ftpClient != null) {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
