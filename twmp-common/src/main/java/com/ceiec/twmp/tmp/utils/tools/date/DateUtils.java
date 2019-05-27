/**
 *
 */
package com.ceiec.twmp.tmp.utils.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * CreateDate：2018/5/8<br/>
 * Author：wenliang<br/>
 * Description: tools related to java.utils.Date
 **/
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /** standard String format of date */
    private static final String DATE_STR_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * transfer Date to standard String format
     *
     * @param date date param
     * @return date in standard String format
     */
    public static String format(Date date) {
        String formatString = "";
        if (null != date) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_STR_FORMAT);
            formatString = format.format(date);
        }
        return formatString;
    }

    /**
     * transfer standard Date String to Date
     *
     * @param dateStr date in standard String format
     * @return transferred Date
     */
    public static Date getDate(String dateStr) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, DATE_STR_FORMAT);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    /**
     * modify given days to origin date and get the result
     *
     * @param date origin date in standard String format
     * @param modifyDay modify days, positive means save, negative means minus
     * @return result in format "yyyy-MM-dd"
     */
    public static String modify(String date, int modifyDay) {
        Date srcDate = getDate(date);
        Date destDate = new Date(srcDate.getTime() + (long) modifyDay * 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(destDate);
    }

    /**
     * get the start moment of the given day
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getStartOfDay(String dateStr) {
        String date = dateStr.split(" ")[0];
        return getDate(date + " 00:00:00");
    }

    /**
     * get the end moment of the given day
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getEndOfDay(String dateStr) {
        String date = dateStr.split(" ")[0];
        return getDate(date + " 23:59:59");
    }

    /**
     * get the start day of the week(Monday) that the given date belongs
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getFirstDayOfWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date time = sdf.parse(dateStr);
            cal.setTime(time);
        } catch (ParseException e) {
            // ignore
        }
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * get the end day of the week(Sunday) that the given date belongs
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getLastDayOfWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date time = sdf.parse(dateStr);
            cal.setTime(time);
        } catch (ParseException e) {
            // ignore
        }
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 6);
        cal.add(Calendar.HOUR_OF_DAY, 23);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * get the first day of the month that the given date belongs
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getFirstDayOfMonth(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date time = sdf.parse(dateStr);
            cal.setTime(time);
        } catch (ParseException e) {
            // ignore
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * get the last day of the month that the given date belongs
     *
     * @param dateStr date in standard String format
     * @return result Date
     */
    public static Date getLastDayOfMonth(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date time = sdf.parse(dateStr);
            cal.setTime(time);
        } catch (ParseException e) {
            // ignore
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.HOUR_OF_DAY, 23);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * compare two date and get their interval seconds
     *
     * @param startDate start date in standard String format
     * @param endDate end date in standard String format
     * @return interval seconds
     */
    public static Long getTwoDateDistance(String startDate, String endDate) {
        Long distance = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_STR_FORMAT);
            Calendar calendar = Calendar.getInstance();
            Date start = sdf.parse(startDate);
            calendar.setTime(start);
            Long sTime = calendar.getTimeInMillis();
            Date end = sdf.parse(endDate);
            calendar.setTime(end);
            Long eTime = calendar.getTimeInMillis();
            distance = (eTime - sTime) / (1000);
        } catch (Exception e) {
            // ignore
        }
        return distance;
    }


    /*************************************************************************************************************************************
     ** @Description  check the hour and minutes of compareTime is between beginTime and endTime or not
     ** @param: beginTime
     ** @param: endTime
     ** @param: compareTime
     ** @Return boolean  if true , it is , else not
     ** @Author Ding
     ** @Date 2019/3/22 11:19
     **
     ************************************************************************************************************************************/
    public static boolean compareHourMin(Date beginTime, Date endTime, Date compareTime){
        Calendar bCal = Calendar.getInstance();
        bCal.setTime(beginTime);
        int bh = bCal.get(Calendar.HOUR);
        int bm = bCal.get(Calendar.MINUTE);

        Calendar cCal = Calendar.getInstance();
        cCal.setTime(compareTime);
        int ch = cCal.get(Calendar.HOUR);
        int cm = cCal.get(Calendar.MINUTE);

        if(ch<bh){
            return false;
        }else if(ch == bh){
            if(cm < bm){
                return false;
            }
        }


        Calendar eCal = Calendar.getInstance();
        eCal.setTime(endTime);
        int eh = eCal.get(Calendar.HOUR);
        int em = eCal.get(Calendar.MINUTE);

        if(eh<ch){
            return false;
        }else if(eh == ch){
            if(em < cm){
                return false;
            }
        }


        return true;
    }

    /**
     * @description:获取当前日期前几天的日期
     * @Param: day
     * @return: java.util.Date
     * @author: zhaozhengchang
     * @date: 2019/3/26 15:54
     */
    public static Date getBeforeDay(int day) {
        //获取当前日期
        Date today = new Date();
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(Calendar.DATE, - day);
        Date time = theCa.getTime();
        return time;
    }
}
