//package com.ceiec.twmp.zuul.filters.pre;
//
//import GeneralAuInfo;
//import ZuulConstants;
//import EFilterType;
//import EPreFilterOrder;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * CreateDate：2018/4/24 <br/>
// * Author：wenliang <br/>
// * Description: filter to make sure the request is from proper client(forbid illegal simulation)
// **/
//@Component
//public class AccessFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return EFilterType.FILTER_PRE.getFilterType();
//    }
//
//    @Override
//    public int filterOrder() {
//        return EPreFilterOrder.ORDER_ACCESS.getOrderValue();
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    /**
//     * charge whether the request contains proper key header
//     *
//     * @return charge result
//     */
//    @Override
//    public Object run() {
//        //get request from client
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        //charge request header
//        String accessHeader = request.getHeader(ZuulConstants.ACCESS_KEY);
//        if (!(ZuulConstants.ACCESS_VALUE.equals(accessHeader))) {
//            throw new RuntimeException("illegal access from " + request.getMethod() + " request to " + request.getRequestURL().toString());
//        }
//
//        //legal request, save a zuul label to forbid request bypass zuul module(request project directly)
//        ctx.addZuulRequestHeader(GeneralAuInfo.ZUUL_PROCESS_LABEL_KEY, GeneralAuInfo.ZUUL_PROCESS_LABEL_VALUE);
//        return null;
//    }
//}
