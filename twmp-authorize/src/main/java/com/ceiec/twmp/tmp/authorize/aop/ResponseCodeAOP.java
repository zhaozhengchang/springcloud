package com.ceiec.twmp.tmp.authorize.aop;

import com.ceiec.twmp.tmp.utils.response.IResponseType;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * CreateDate: 2018/6/28 <br/>
 * Author：wenliang <br/>
 * Description: aop of handle response type
 **/
@Aspect
@Component
public class ResponseCodeAOP {

    /**
     * handle response code, save module code before origin response code
     *
     * @param joinPoint joinPoint
     * @return response content
     * @throws Throwable exception
     */
    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public Object handleResponseCode(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof ResponseContent) {
            ResponseContent content = (ResponseContent) proceed;
            int code = content.getCode();
            if (!ArrayUtils.contains(new String[]{IResponseType.SUCCESS, IResponseType.UNKNOWN, IResponseType.ILLEGAL_PARAMETER,
                    IResponseType.ROUTE_PROJECT_ERROR, IResponseType.ZUUL_ERROR}, code)) {
                // guide module, save "1"
                //TODO
//                content.setCode(EModuleCode.MODULE_LOGIN.getModuleCode() + code);
            }
            return content;
        }
        return proceed;
    }
}