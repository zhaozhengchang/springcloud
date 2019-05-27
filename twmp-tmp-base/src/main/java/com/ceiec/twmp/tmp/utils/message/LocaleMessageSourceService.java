package com.ceiec.twmp.tmp.utils.message;

import com.ceiec.twmp.tmp.EConfig;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * CreateDate：2019/1/3 <br/>
 * Author：wenliang <br/>
 * Description: 国际化服务类
 **/

@Component
public class  LocaleMessageSourceService {


    @Resource
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    private String defaultLocale = null;



    /*************************************************************************************************************************************
     ** @Description get message by local string
     ** @param: code
     * @param: localeString
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/11 16:52
     **
     ************************************************************************************************************************************/
    public String getMessageLocal(String code, String localeString){
        if(StringUtils.isNullOrEmpty(defaultLocale)){
            defaultLocale = env.getProperty(EConfig.DEFAULT_LOCALE);
        }

        if(StringUtils.isNullOrEmpty(localeString)){
            localeString = defaultLocale;
        }

        String localName = localeString.split("_")[0];
        String country = localeString.split("_")[1];
        Locale locale = new Locale(localName, country);
        return getMessageLocale(code, null,locale);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param locale          : locale
     * @return
     */

    private String getMessageLocale(String code,Locale locale) {

        return getMessageLocale(code, null,locale);

    }


    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */

    private String getMessage(String code, Object[] args) {

        return getMessage(code, args, "");

    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param locale          : locale
     * @return
     */

    private String getMessageLocale(String code, Object[] args,Locale locale) {

        return getMessageLocale(code, args, "",locale);

    }


    /**
     * @param code           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */

    private String getMessage(String code, Object[] args, String defaultMessage) {

        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(code, args, defaultMessage, locale);

    }

    /**
     * @param code           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @param locale          : locale
     * @return
     */

    private String getMessageLocale(String code, Object[] args, String defaultMessage,Locale locale) {

        return messageSource.getMessage(code, args, defaultMessage, locale);

    }


}