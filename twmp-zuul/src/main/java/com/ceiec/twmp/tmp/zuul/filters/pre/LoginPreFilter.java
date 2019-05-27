package com.ceiec.twmp.tmp.zuul.filters.pre;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.common.enums.EOperateSystem;
import com.ceiec.twmp.tmp.zuul.enums.EFilterType;
import com.ceiec.twmp.tmp.zuul.enums.EPreFilterOrder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * CreateDate：2018/5/11 <br/>
 * Author：wenliang <br/>
 * Description: pre filter for login request
 **/
@Component
public class LoginPreFilter extends ZuulFilter {

    /**
     * request template
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String filterType() {
        return EFilterType.FILTER_PRE.getFilterType();
    }

    @Override
    public int filterOrder() {
        return EPreFilterOrder.ORDER_LOGIN_PRE.getOrderValue();
    }

    /**
     * only filter request to method "login" or "loginAndKick" in project twmp-authorize
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String route = ctx.getRequest().getRequestURI();
        return route.endsWith("/authorize/login") || route.endsWith("/authorize/loginAndKick");
    }

    /**
     * save datasource information to request body before forward
     *
     * @return null
     */
    @Override
    public Object run() {


        //get request information
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //get request body and save datasource information & request IP
        try {
            String requestBody = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
            LoginInfoVO loginInfo = JSON.parseObject(requestBody, LoginInfoVO.class);
            Map<String, String> map = context.getZuulRequestHeaders();
            context.getZuulRequestHeaders().get("X-Span-Name");
            loginInfo.setSystemCode(1);//always use system 1
            String applicationName = EOperateSystem.getSystemEnum(loginInfo.getSystemCode()).getAppName(); //request system's micro service name
            HttpHeaders httpHeaders = new HttpHeaders();
            Map<String, String> zuulRequestHeaders = context.getZuulRequestHeaders();
            Set<String> keys = zuulRequestHeaders.keySet();
            for (String key : keys) {
                httpHeaders.add(key, zuulRequestHeaders.get(key));
            }
            HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
            ResponseEntity<DatasourceInfoVO> datasourceResponse = restTemplate.exchange("http://" + applicationName + "/getDatasourceInfo", HttpMethod.GET, httpEntity, DatasourceInfoVO.class);
            loginInfo.setDatasourceInfo(datasourceResponse.getBody());
            ResponseEntity<UserTableInfoVO> userTableResponse = restTemplate.exchange("http://" + applicationName + "/getUserTableInfo", HttpMethod.GET, httpEntity, UserTableInfoVO.class);
            loginInfo.setUserTableInfo(userTableResponse.getBody());
            loginInfo.setRequestIP(getRequestIp(request));
            final byte[] requestBodyBytes = JSON.toJSONString(loginInfo).getBytes();
            context.setRequest(new HttpServletRequestWrapper(request) {
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    return new ServletInputStreamWrapper(requestBodyBytes);
                }

                @Override
                public int getContentLength() {
                    return requestBodyBytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return requestBodyBytes.length;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("unable to get datasource information", e);
        }

        return null;
    }

    /**
     * get request ip
     *
     * @param request request object
     * @return request ip
     * @throws UnknownHostException unknown host exception
     */
    private String getRequestIp(HttpServletRequest request) throws UnknownHostException {
        if (request == null) {
            return null;
        }
        String ipAddress = request.getHeader("x-forwarded-for");
        if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ipAddress == null) || (ipAddress.length() == 0) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getRemoteAddr();
            if (("127.0.0.1".equals(ipAddress)) || (ipAddress.endsWith("0:0:0:0:0:0:1"))) { //request host is server host
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
        return ipAddress;
    }
}
