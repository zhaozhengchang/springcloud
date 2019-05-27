//package com.ceiec.twmp.tmp.authorize.api;
//
//import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
//import com.ceiec.twmp.tmp.utils.response.ResponseContent;
//import org.springframework.stereotype.Component;
//
//
///**
// * The type Test call back.
// */
//@Component
//public class TestCallBack implements IMyFeignClient {
//
//    @Override
//    public ResponseContent sendmail(EmailSendRequest emailSendRequest){
//        ResponseContent temp = new ResponseContent();
//        temp.setCode("10000");
//        temp.setError("调用出错啦，Hystrix返回");
//        return  temp;
//    }
//
//}
