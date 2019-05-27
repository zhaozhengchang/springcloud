package com.ceiec.twmp.tmp.utils.tools.date;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Ding
 * @version V1.0
 * @Description: date config
 * @create 2019-03-01 10:34
 **/
public class DateFormatConfig {

    @Value("${date.format.datetime}")
    public String datetimeFormat;

    @Value("${date.format.date}")
    public String dateFormat;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDatetimeFormat() {
        return datetimeFormat;
    }

    public void setDatetimeFormat(String datetimeFormat) {
        this.datetimeFormat = datetimeFormat;
    }
}
