package com.ceiec.twmp.tmp.vo.department.result;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: tree of department
 * @create 2019-03-05 14:45
 **/
public class DepartmentTreeVO extends DepartmentListResultVO {

    private List<DepartmentTreeVO> childrenList;

    public List<DepartmentTreeVO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<DepartmentTreeVO> childrenList) {
        this.childrenList = childrenList;
    }
}
