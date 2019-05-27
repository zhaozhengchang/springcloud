package com.ceiec.twmp.tmp.aop;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * CreateDate：2018/5/21 <br/>
 * Author：wenliang <br/>
 * Description: verify if the request to web-wtmp contains legal token,if not,reject the request
 **/
@Aspect
@Component
@Order(2)
public class TokenVerifyAOP {

    /** logger */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * verify token's existence
     *
     * @param joinPoint joint point
     * @return verify response
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController) && !within(com.ceiec.twmp.tmp.controller.AuthorizeController)&& !within(com.ceiec.twmp.tmp.controller.FileDownloadController)")
    public Object verifyToken(ProceedingJoinPoint joinPoint) throws Throwable {
        //get request information
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestMethod = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();

        //charge if request have "token" header
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            logger.warn("illegal access from request {} to {}, please save token header", requestMethod, request.getRequestURL().toString());
            return new ResponseContent(ResponseType.ILLEGAL_TOKEN);
        }

        //charge if request token is legal
        if (!TokenUtils.isLegalToken(token)) {
            logger.warn("request token from request {} to {} is illegal", requestMethod, request.getRequestURL().toString());
            return new ResponseContent(ResponseType.ILLEGAL_TOKEN);
        }

        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        if(userInfo == null){
            logger.warn("from request {} to {} is illegal, for user token is not in redis", requestMethod, request.getRequestURL().toString());
            return new ResponseContent(ResponseType.ILLEGAL_TOKEN);
        }

        UserInfoRedis.saveUser(userInfo);

        //token verify success,proceed
        return joinPoint.proceed();
    }
}
