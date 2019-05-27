//package com.ceiec.twmp.tmp.authorize.api;
//
//import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
//import com.ceiec.twmp.tmp.utils.response.ResponseContent;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * The interface My feign client.
// */
////@FeignClient(value="email-service", fallback=TestCallBack.class)
//public interface IMyFeignClient {
//
//    /**
//     * Sendmail response content.
//     *
//     * @param emailSendRequest the email send request
//     * @return the response content
//     */
//    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
//    public ResponseContent sendmail(@RequestBody EmailSendRequest emailSendRequest);
//
//}
