package com.ceiec.twmp.tmp.utils.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: get unique server id
 * @create 2019-03-12 16:01
 **/
public class ServerIdUtils {

    private static Logger logger = LoggerFactory.getLogger(ServerIdUtils.class);
    private static String serviceId = null;

    public static String getServiceId(){
        if(StringUtils.isNullOrEmpty(serviceId)){
            String path = ServerIdUtils.class.getResource("").getPath();
            serviceId = path + ServerIdUtils.class.getSimpleName()+"/"+ getMacId();
        }
        return serviceId;
    }



    /*************************************************************************************************************************************
     ** @Description get mac id
     ** @param:
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/12 16:03
     **
     ************************************************************************************************************************************/
    private static String getMacId(){
        InetAddress ip = null;
        NetworkInterface ni = null;
        List<String> macList = new ArrayList<String>();

        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                ni = netInterfaces
                        .nextElement();
                // ----------特定情况，可以考虑用ni.getName判断
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress() // 非127.0.0.1
                            && ip.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        macList.add(getMacFromBytes(ni.getHardwareAddress())+":"+ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("get the mac address of server is failed", e);
        }

        if(macList!=null && macList.size()>0){
            return macList.get(0);
        }
        return null;
    }


    private static String getMacFromBytes(byte[] bytes) {
        StringBuffer mac = new StringBuffer();
        byte currentByte;
        boolean first = false;
        for (byte b : bytes) {
            if (first) {
                mac.append("-");
            }
            currentByte = (byte) ((b & 240) >> 4);
            mac.append(Integer.toHexString(currentByte));
            currentByte = (byte) (b & 15);
            mac.append(Integer.toHexString(currentByte));
            first = true;
        }
        return mac.toString().toUpperCase();
    }
}
