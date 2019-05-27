package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: record alarm number
 * @create 2019-04-22 11:42
 **/
public class AlarmNumberRedis extends BaseRedis {

    public static final String ALARM_REMARK = "W";

    public static String generateAlarmNumber(){
        Date date = new Date();

        String dateStr = DateFormatUtils.dateToString(date, DateFormatUtils.DATE_FORMAT_ABB);

        Integer number;

        String alarmNumber = (String)get(ALARM_NUMBER, dateStr, String.class);
        if(!StringUtils.isNullOrEmpty(alarmNumber)){
            number = Integer.parseInt(alarmNumber)+1;

        }else{
            number = 1;
        }

        if(number >= 1000000){
            throw new BusinessException("the alarm number is too bigger");
        }else if(number >= 100000  && number <1000000){
            alarmNumber = number.toString();
        }else if(number >= 10000  && number <100000){
            alarmNumber = "0"+number.toString();
        }else if(number >= 1000  && number <10000){
            alarmNumber = "00"+number.toString();
        }else if(number >= 100  && number <1000){
            alarmNumber = "000"+number.toString();
        }else if(number >= 10  && number <100){
            alarmNumber = "0000"+number.toString();
        }else if(number >= 1  && number <10){
            alarmNumber = "00000"+number.toString();
        }

        save(ALARM_NUMBER, dateStr, number);
        return ALARM_REMARK+dateStr+alarmNumber;


    }
}
