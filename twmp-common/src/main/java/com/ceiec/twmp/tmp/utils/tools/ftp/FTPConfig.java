package com.ceiec.twmp.tmp.utils.tools.ftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * CreateDate：2019/1/14
 * Author：wenliang
 * Description: FTP属性相关的配置
 */
@Configuration
public class FTPConfig {
    /**
     * ftp service host
     */
    @Value("${ftp.server.ip}")
    public String serverIp;
    /**
     * ftp service port
     */
    @Value("${ftp.server.port}")
    public int serverPort;
    /**
     * ftp service username
     */
    @Value("${ftp.server.username}")
    public String ftpServerUsername;
    /**
     * ftp service password
     */
    @Value("${ftp.server.password}")
    public String ftpServerPassword;
    /**
     * ftp service FTP协议被动模式
     */
    @Value("${ftp.server.passiveMode}")
    public boolean passiveMode;
    /**
     * FTP协议bufferSize
     */
    @Value("${ftp.buffer.size}")
    public int bufferSize;
    /**
     * ftp service 顶层文件路径
     */
    @Value("${ftp.server.top.folder}")
    public String ftpTopFolder;

    /**
     * Gets server ip.
     *
     * @return the server ip
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * Sets server ip.
     *
     * @param serverIp the server ip
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * Gets server port.
     *
     * @return the server port
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Sets server port.
     *
     * @param serverPort the server port
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Gets ftp server username.
     *
     * @return the ftp server username
     */
    public String getFtpServerUsername() {
        return ftpServerUsername;
    }

    /**
     * Sets ftp server username.
     *
     * @param ftpServerUsername the ftp server username
     */
    public void setFtpServerUsername(String ftpServerUsername) {
        this.ftpServerUsername = ftpServerUsername;
    }

    /**
     * Gets ftp server password.
     *
     * @return the ftp server password
     */
    public String getFtpServerPassword() {
        return ftpServerPassword;
    }

    /**
     * Sets ftp server password.
     *
     * @param ftpServerPassword the ftp server password
     */
    public void setFtpServerPassword(String ftpServerPassword) {
        this.ftpServerPassword = ftpServerPassword;
    }

    /**
     * Is passive mode boolean.
     *
     * @return the boolean
     */
    public boolean isPassiveMode() {
        return passiveMode;
    }

    /**
     * Sets passive mode.
     *
     * @param passiveMode the passive mode
     */
    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    /**
     * Gets buffer size.
     *
     * @return the buffer size
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * Sets buffer size.
     *
     * @param bufferSize the buffer size
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Gets ftp top folder.
     *
     * @return the ftp top folder
     */
    public String getFtpTopFolder() {
        return ftpTopFolder;
    }

    /**
     * Sets ftp top folder.
     *
     * @param ftpTopFolder the ftp top folder
     */
    public void setFtpTopFolder(String ftpTopFolder) {
        this.ftpTopFolder = ftpTopFolder;
    }
}
