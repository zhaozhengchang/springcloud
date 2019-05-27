package com.ceiec.twmp.tmp.zuul.filters.post;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.LoginResultVO;
import com.ceiec.twmp.tmp.common.enums.EOperateSystem;
import com.ceiec.twmp.tmp.utils.response.IResponseType;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.zuul.enums.EFilterType;
import com.ceiec.twmp.tmp.zuul.enums.EPostFilterOrder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * CreateDate：2018/5/17 <br/>
 * Author：wenliang <br/>
 * Description: post filter for login request
 **/
@Component
public class LoginPostFilter extends ZuulFilter {

    /** request template */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String filterType() {
        return EFilterType.FILTER_POST.getFilterType();
    }

    @Override
    public int filterOrder() {
        return EPostFilterOrder.ORDER_LOGIN_POST.getOrderValue();
    }

    /**
     * only filter request to method "login" or loginAndKick in project twmp-authorize
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String route = ctx.getRequest().getRequestURI();
        return route.endsWith("/authorize/login") || route.endsWith("/authorize/loginAndKick");
    }

    /**
     * inform request project to process the login result
     */
    @Override
    public Object run() {
        //get response information
        RequestContext context = RequestContext.getCurrentContext();
        String response;
        ResponseContent responseContent;
        try {
            response = StreamUtils.copyToString(context.getResponseDataStream(), Charset.forName("UTF-8"));
            responseContent = JSON.parseObject(response, ResponseContent.class);
        } catch (Exception e) {
            throw new RuntimeException("unable to get response information", e);
        }

        //if login success, inform request project to process logged in information
        if (responseContent.getCode()==Integer.parseInt(IResponseType.SUCCESS)) {
            //get request project ID
            LoginInfoVO loginInfo;
            try {
                HttpServletRequest request = context.getRequest();
                String requestBody = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
                loginInfo = JSON.parseObject(requestBody, LoginInfoVO.class);
            } catch (Exception e) {
                throw new RuntimeException("unable to get request information", e);
            }
            loginInfo.setSystemCode(1);//always use system 1
            String applicationName = EOperateSystem.getSystemEnum(loginInfo.getSystemCode()).getAppName(); //request system's micro service name
            HttpHeaders requestHeaders = new HttpHeaders();
            Map<String, String> zuulRequestHeaders = context.getZuulRequestHeaders();
            Set<String> keys = zuulRequestHeaders.keySet();
            for (String key : keys) {
                requestHeaders.add(key, zuulRequestHeaders.get(key));
            }
            //callback request project to process logged in information
            if (context.getRequest().getRequestURI().endsWith("login")) { //request is login
                LoginResultVO loginResult = JSON.parseObject(responseContent.getData().toString(), LoginResultVO.class);
                HttpEntity<LoginResultVO> requestEntity = new HttpEntity<>(loginResult, requestHeaders);
                restTemplate.postForEntity("http://" + applicationName + "/processLoginSuccess", requestEntity, String.class);
            }
        }

        //put response back and return
        context.setResponseBody(response);
        return null;
    }
}
