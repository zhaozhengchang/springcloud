package com.ceiec.twmp.tmp.utils.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * CreateDate：2019/1/3 <br/>
 * Author：wenliang <br/>
 * Description: 国际化配置类
 **/

@Configuration
public class InitialConfig {

    /** 国际化文件路径 */
    @Value("${spring.messages.basename}")
    public String basename;

    /**
     * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(basename);
        return messageSource;
    }

}
