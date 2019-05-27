package com.ceiec.twmp.tmp.utils.tools;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author Ding
 * @version V1.0
 * @Description: convert template   the template like:  {creator} create device {deviceNumber} at {creatTime}
 * @create 2019-04-03 17:02
 **/
public class TemplateConvertUtils {

    public static String template2Result(String template, String valueJson){
        while(template.indexOf("{")>-1){
            String column = template.substring(template.indexOf("{")+1, template.indexOf("}"));
            template = template.replace("{"+column+"}", JSON.parseObject(valueJson).getString(column));
        }

        return template;
    }



    /*************************************************************************************************************************************
     ** @Description
     ** @param: template  analysis template , get the column value into a valueJson
     ** @param: dest
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/4/3 17:08
     **
     ************************************************************************************************************************************/
    public static String getValueJson(String template, Object dest) {
        Map<String, String> eventMap = new HashMap<>();
        try{
            Set<String> columns = getColumn(template);
            if(columns!=null&&columns.size()>0){
                BeanInfo beanInfo = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
                PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
                for(int i=0; i<properties.length; i++){
                    for(String column: columns){
                        if(properties[i].getName().equals(column)){
                            if(properties[i].getPropertyType() ==  java.util.Date.class){
                                eventMap.put(column, DateFormatUtils.dateToString((Date)properties[i].getReadMethod().invoke(dest)));
                            }else{
                                eventMap.put(column, (String)properties[i].getReadMethod().invoke(dest));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new BusinessException("analysis lifecycle event is error ");
        }

        return JSON.toJSONString(eventMap);
    }



    public static String getValueJson(String template, Map<String, String> map) {
        Map<String, String> eventMap = new HashMap<>();
        try{
            Set<String> columns = getColumn(template);
            if(columns!=null && columns.size()>0 && map!=null && map.size()>0){
                for(String column: columns){
                   for(String key: map.keySet()){
                       if(column.equals(key)){
                           eventMap.put(column, map.get(key));
                           break;
                       }
                   }
                }
            }
        }catch (Exception e){
            throw new BusinessException("analysis lifecycle event is error ");
        }

        return JSON.toJSONString(eventMap);
    }



    /*************************************************************************************************************************************
     ** @Description get column from template
     ** @param: value
     ** @param: template
     ** @Return java.util.Set<java.lang.String>
     ** @Author Ding
     ** @Date 2019/4/3 17:07
     **
     ************************************************************************************************************************************/
    private static Set<String> getColumn(String template){
        Set<String> columns = new HashSet<>();

        while (template.indexOf("{")>-1){
            String column = template.substring(template.indexOf("{")+1, template.indexOf("}"));
            columns.add(column);
            template = template.substring(template.indexOf("}")+1);
        }
        return columns;
    }
}
