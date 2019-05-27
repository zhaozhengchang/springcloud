package com.ceiec.twmp.tmp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * CreateDate：2017/11/28 <br/>
 * Author：wenliang <br/>
 * Description: get bean by bean name from spring container
 **/
@Component
public class SpringUtil implements ApplicationContextAware {

    /** application context */
    private static ApplicationContext applicationContext = null;

    /**
     * initialize application context
     *
     * @param applicationContext application context
     * @throws BeansException bean exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * get bean from spring container
     *
     * @param name bean name
     * @param clazz bean type
     * @return bean object
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

}