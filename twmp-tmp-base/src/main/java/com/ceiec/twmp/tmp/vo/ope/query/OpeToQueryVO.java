package com.ceiec.twmp.tmp.vo.ope.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: query for ope info
 * @create 2019-04-10 15:02
 **/
public class OpeToQueryVO extends PageParentVO {

    private String personName;

    private String taskNumber;

    private Byte opeType;

    private List<Long> ownDepartmentId;

    public List<Long> getOwnDepartmentId() {
        return ownDepartmentId;
    }

    public void setOwnDepartmentId(List<Long> ownDepartmentId) {
        this.ownDepartmentId = ownDepartmentId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Byte getOpeType() {
        return opeType;
    }

    public void setOpeType(Byte opeType) {
        this.opeType = opeType;
    }
}
