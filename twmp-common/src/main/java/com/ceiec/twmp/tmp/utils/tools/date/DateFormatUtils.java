package com.ceiec.twmp.tmp.utils.tools.date;

import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: the utils format date
 * @create 2019-03-01 9:56
 **/
public class DateFormatUtils {
    /**the format always used in system, include converting gps time, interface method **/
    public static final String DATETIME_FORMAT_SYSTEM = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_SYSTEM = "yyyy-MM-dd";
    public static final String DATE_FORMAT_ABB = "yyMMdd";
    /**the format for fence time only**/
    public static final String FENCE_TIME = "HH:mm";
    /** generate number date **/
    public static String NUMBER_DATE_FORMATE = "yyMMdd";



    /** default format**/
    public static String DATETIME_FORMAT ="yyyy-MM-dd HH:mm:ss";
    /** default format**/
    public static String DATE_FORMAT = "yyyy-MM-dd";

    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);


    /*************************************************************************************************************************************
     ** @Description set dateFormat config
     ** @param: dateFormatConfig
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/1 10:38
     **
     ************************************************************************************************************************************/
    @Autowired(required = true)
    public void setDateFormatConfig(DateFormatConfig dateFormatConfig) {
        DATETIME_FORMAT = dateFormatConfig.datetimeFormat;
    }



    /*************************************************************************************************************************************
     ** @Description convert string to date in system
     ** @param: dateStr
     ** @Return java.util.Date
     ** @Author Ding
     ** @Date 2019/3/15 16:08
     **
     ************************************************************************************************************************************/
//    public static Date stringToDateSystem(String dateStr){
//        if(StringUtils.isNullOrEmpty(dateStr)){
//            return null;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_SYSTEM);
//        Date date = null;
//        try {
//            date = sdf.parse(dateStr);
//        } catch (ParseException e) {
//            logger.warn("convert date is failed, the reason is {}", e.getMessage());
//        }
//
//        return date;
//    }


    public static Date stringToDate(String dateStr){
        if(StringUtils.isNullOrEmpty(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.warn("convert date is failed, the reason is {}", e);
        }

        return date;
    }


    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String dateStr = null;
        if (date == null) {
            return dateStr;
        }
        dateStr = sdf.format(date);
        return dateStr;
    }



    /*************************************************************************************************************************************
     ** @Description convert string to  date in default format
     ** @param: dateStr
     ** @Return java.util.Date
     ** @Author Ding
     ** @Date 2019/3/1 10:10
     **
     ************************************************************************************************************************************/
    public static Date stringToDateTime(String dateStr){
        if(StringUtils.isNullOrEmpty(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.warn("convert date is failed, the reason is {}", e);
        }

        return date;
    }

    /*************************************************************************************************************************************
     ** @Description convert date to string in default format
     ** @param: date
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/1 10:11
     **
     ************************************************************************************************************************************/
    public static String dateTimeToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        String dateStr = null;
        if (date == null) {
            return dateStr;
        }
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @description: 指定格式化格式
     * @Param: date
     * @Param: format
     * @return: java.lang.String
     * @author: zhaozhengchang
     * @date: 2019/3/26 15:05
     */
    public static String dateToString(Date date , String format){
        if(format == null){
            format = DATE_FORMAT_SYSTEM ;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = null;
        if (date == null) {
            return dateStr;
        }
        dateStr = sdf.format(date);
        return dateStr;
    }


    public static Date stringToDate(String dateStr , String format){
        if(StringUtils.isNullOrEmpty(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.warn("convert date is failed, the reason is {}", e);
        }

        return date;
    }

}
