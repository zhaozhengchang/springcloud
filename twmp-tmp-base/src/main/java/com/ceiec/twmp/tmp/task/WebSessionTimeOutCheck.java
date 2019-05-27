package com.ceiec.twmp.tmp.task;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IAuthorizeService;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: check user session time out
 * @create 2019-03-12 16:15
 **/
@Component
@Order(value=100)
public class WebSessionTimeOutCheck implements CommandLineRunner {
    private static final String LOCK_KEY = "webSessionTimeOutCheck";
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(WebSessionTimeOutCheck.class);

    /**
     * authorize service interface
     */
    @Autowired
    private IAuthorizeService authorizeService;


    @Override
    public void run(String... args) {
        SessionCheck sessionCheck = new SessionCheck();
        sessionCheck.start();
    }



    class SessionCheck extends Thread{
        @Override
        public void run() {

            while (true) {
                try {
                    if(!RedisUtils.checkRedisKey(LOCK_KEY)){
                        Map<String, RedisUserOnlineVO> userOnlineMap = UserInfoRedis.getUserOnline();
                        if (userOnlineMap != null && userOnlineMap.size() > 0) {
                            for (String token: userOnlineMap.keySet()) {
                                if(UserInfoRedis.getUser(token)==null){

                                    authorizeService.processLogout(token);
                                }
                            }
                        }
                        RedisUtils.delRedisKey(LOCK_KEY);
                    }
                    sleep(1000);
                } catch (Exception e) {
                    logger.warn("user session time out check is failed", e);
                }
            }
        }
    }





}
