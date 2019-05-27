package com.ceiec.twmp.tmp.vo.chart.query;

import java.io.Serializable;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 16:34
 * Description:
 **/

public class QueryDaysVO implements Serializable {

    private static final long serialVersionUID = -2535334888614671637L;

    private Integer days;
    private String today;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public QueryDaysVO(Integer days, String today) {
        this.days = days;
        this.today = today;
    }
}
