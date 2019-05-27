package com.ceiec.twmp.tmp.zuul.filters.post;

import com.ceiec.twmp.tmp.common.enums.EOperateSystem;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.zuul.enums.EFilterType;
import com.ceiec.twmp.tmp.zuul.enums.EPostFilterOrder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * CreateDate：2018/5/21 <br/>
 * Author：wenliang <br/>
 * Description: post filter for logout request
 **/
@Component
public class LogoutPostFilter extends ZuulFilter {

    /** request template */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String filterType() {
        return EFilterType.FILTER_POST.getFilterType();
    }

    @Override
    public int filterOrder() {
        return EPostFilterOrder.ORDER_LOGOUT_POST.getOrderValue();
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String route = ctx.getRequest().getRequestURI();
        return route.endsWith("/authorize/logout");
    }

    /**
     * inform request project to process logout
     */
    @Override
    public Object run() {
        //get request token header
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("token");

        //callback request project to process logout
        String applicationName = EOperateSystem.getSystemEnum(TokenUtils.getSystemCode(token)).getAppName(); //request system's micro service name

        HttpHeaders requestHeaders = new HttpHeaders();
        Map<String, String> zuulRequestHeaders = context.getZuulRequestHeaders();
        Set<String> keys = zuulRequestHeaders.keySet();
        for (String key : keys) {
            requestHeaders.add(key, zuulRequestHeaders.get(key));
        }
        requestHeaders.add("token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        restTemplate.postForEntity("http://" + applicationName + "/processLogout", requestEntity, String.class);
        return null;
    }
}
