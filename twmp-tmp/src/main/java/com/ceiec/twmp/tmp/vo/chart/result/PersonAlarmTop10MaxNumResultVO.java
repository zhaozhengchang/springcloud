package com.ceiec.twmp.tmp.vo.chart.result;

import java.util.List;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-28 16:12
 * Description:
 **/

public class PersonAlarmTop10MaxNumResultVO {

    private static final long serialVersionUID = -2535994456614671637L;

    private long maxNum;
    private List<PersonAlarmTop10ResultVO> items;

    public long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(long maxNum) {
        this.maxNum = maxNum;
    }

    public List<PersonAlarmTop10ResultVO> getItems() {
        return items;
    }

    public void setItems(List<PersonAlarmTop10ResultVO> items) {
        this.items = items;
    }

    public PersonAlarmTop10MaxNumResultVO(long maxNum, List<PersonAlarmTop10ResultVO> personAlarmTop10ResultVOList) {
        this.maxNum = maxNum;
        this.items = personAlarmTop10ResultVOList;
    }
}
