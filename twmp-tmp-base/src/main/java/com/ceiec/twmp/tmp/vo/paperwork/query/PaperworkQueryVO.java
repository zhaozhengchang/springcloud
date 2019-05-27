package com.ceiec.twmp.tmp.vo.paperwork.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-13 19:54
 * Description:
 **/

public class PaperworkQueryVO extends PageParentVO {

    private static final long serialVersionUID = 556130588452604638L;

    private String paperworkName;
    private String creator;
    private Date startTime;
    private Date endTime;

    public String getPaperworkName() {
        return paperworkName;
    }

    public void setPaperworkName(String paperworkName) {
        this.paperworkName = paperworkName;
    }

    public String getUploadPerson() {
        return creator;
    }

    public void setUploadPerson(String uploadPerson) {
        this.creator = uploadPerson;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
