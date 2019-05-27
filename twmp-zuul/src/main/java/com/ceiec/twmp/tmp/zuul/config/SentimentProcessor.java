//package com.ceiec.twmp.zuul.config;
//
//import com.netflix.zuul.FilterProcessor;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//
///**
// * CreateDate：2018/4/24 <br/>
// * Author：wenliang <br/>
// * Description: overload filter processor class to mark exceptions from post filter
// **/
//public class SentimentProcessor extends FilterProcessor {
//
//    /** label for post filter exception */
//    private final String label = "failed.filter";
//
//    /**
//     * save label to post filter exception
//     *
//     * @param filter filter object
//     * @return Object process result
//     * @throws ZuulException zuul exception
//     */
//    @Override
//    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
//        try {
//            return super.processZuulFilter(filter);
//        } catch (ZuulException e) {
//            RequestContext ctx = RequestContext.getCurrentContext();
//            ctx.set(label, filter);
//            throw e;
//        }
//    }
//
//    /**
//     * get label for post filter exception
//     *
//     * @return String the label
//     */
//    public String getLabel() {
//        return label;
//    }
//}
