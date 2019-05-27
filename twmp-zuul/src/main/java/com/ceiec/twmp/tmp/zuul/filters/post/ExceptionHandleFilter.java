package com.ceiec.twmp.tmp.zuul.filters.post;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.utils.response.IResponseType;
import com.ceiec.twmp.tmp.zuul.enums.EFilterType;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.zuul.enums.EPostFilterOrder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * CreateDate：2018/6/13 <br/>
 * Author：wenliang <br/>
 * Description: process exceptions when routed projects have unhandled exceptions
 **/
@Component
public class ExceptionHandleFilter extends ZuulFilter {

    /** logger */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return EFilterType.FILTER_POST.getFilterType();
    }

    @Override
    public int filterOrder() {
        return EPostFilterOrder.ORDER_EXCEPTION_POST.getOrderValue();
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse response = context.getResponse();
        return response.getStatus() != HttpStatus.OK.value();
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        int status = context.getResponse().getStatus();
        logger.error("unknown server error when filter to {}, response code is {}", context.getRequest().getRequestURI(), status, context.getThrowable());
        ResponseContent response = new ResponseContent();
        response.setCode(Integer.parseInt(IResponseType.ROUTE_PROJECT_ERROR));
        if (status == 0) {
            response.setError("Request Timeout");
        } else {
            response.setError("Unknown Server Error");
        }
        context.setResponseBody(JSON.toJSONString(response));
        return null;
    }
}
