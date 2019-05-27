package com.ceiec.twmp.tmp.utils;

import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

/**
 * CreateDate：2018/12/8
 * Author：wenliang
 * Description: Object Utils
 **/
public class ObjectUtils {

    /**
     * 两个对象中相同的属性值复制
     * @param source
     * @param dest
     * @throws Exception
     */
    public static void copy(Object source, Object dest)throws ParameterException {
        String sourcePropertyName = "";
        String destPropertyName = "";
        try{
            //获取属性
            BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
            PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

            BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
            PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();


            for(int i=0;i<sourceProperty.length;i++){

                for(int j=0;j<destProperty.length;j++){
                    sourcePropertyName = sourceProperty[i].getName();
                    destPropertyName = destProperty[j].getName();
                    if(sourceProperty[i].getName().equals(destProperty[j].getName())){

                        //调用source的getter方法和dest的setter方法
                        if(sourceProperty[i].getPropertyType() == java.lang.String.class && destProperty[j].getPropertyType() == java.util.Date.class){
                            destProperty[j].getWriteMethod().invoke(dest, DateFormatUtils.stringToDateTime((String)sourceProperty[i].getReadMethod().invoke(source)));
                        } else if(sourceProperty[i].getPropertyType() == java.util.Date.class && destProperty[j].getPropertyType() == java.lang.String.class ) {
                            destProperty[j].getWriteMethod().invoke(dest, DateFormatUtils.dateTimeToString((Date)sourceProperty[i].getReadMethod().invoke(source)));
                        } else if(sourceProperty[i].getPropertyType() == destProperty[j].getPropertyType() ) {
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                        }
                        break;
                    }
                }
            }
        }catch(Exception e){
            // 添加具体的错误信息，那个两个属性没有对上，方便调试
            throw new ParameterException("属性复制失败:" + source.getClass().getSimpleName() + "." + sourcePropertyName
                        + "的类型和" + dest.getClass().getSimpleName() + "." + destPropertyName + "的类型不一致\n" + e.getMessage());
        }
    }
}
