package com.ceiec.twmp.tmp.gateway.filters.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * CreateDate：2019/3/11 <br/>
 * Author：Ding <br/>
 * Description:  filter for login request  or logout request
 **/
@Component
public class LoginLogoutFilter implements GatewayFilter {

    private Logger logger = LoggerFactory.getLogger(LoginLogoutFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //pre post
//        String ip = getRequestIp(exchange.getRequest());
//        exchange.getAttributes().put("requestIP", ip);



        return chain.filter(exchange).then(
                //post
                Mono.fromRunnable(() -> {

                })
        );





    }


    /*************************************************************************************************************************************
     ** @Description get request ip
     ** @param: request
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/7 17:27
     **
     ************************************************************************************************************************************/
    private String getRequestIp(ServerHttpRequest request){
        String ipAddress = "";

        try{
            if (request == null) {
                return null;
            }
            ipAddress = request.getHeaders().getFirst("x-forwarded-for");
            if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
                ipAddress = request.getHeaders().getFirst("Proxy-Client-IP");
            }
            if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
                ipAddress = request.getHeaders().getFirst(("WL-Proxy-Client-IP"));
            }
            if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
                ipAddress = request.getHeaders().getFirst(("WL-Proxy-Client-IP"));
                if ((ipAddress == null) || ("127.0.0.1".equals(ipAddress)) || (ipAddress.endsWith("0:0:0:0:0:0:1"))) { //request host is server host
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                }
            }
            //multi proxy host request
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }catch (UnknownHostException e){
            logger.warn("can not know host ",e);
        }

        return ipAddress;
    }
}
