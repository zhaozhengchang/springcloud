package com.ceiec.twmp.tmp.vo.chart.result;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-26 9:27
 * Description:
 **/

public class PersonAlarmTop10ResultVO implements Serializable {

    private static final long serialVersionUID = -2535994589614671637L;

    private Long num;
    private String personName;
    private String personNumber;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
}
