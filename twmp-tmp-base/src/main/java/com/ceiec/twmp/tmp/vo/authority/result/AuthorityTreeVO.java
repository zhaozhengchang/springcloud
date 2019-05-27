package com.ceiec.twmp.tmp.vo.authority.result;

import com.ceiec.twmp.tmp.entity.TwmpSysAuthority;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: result of authority
 * @create 2019-03-05 17:15
 **/
public class AuthorityTreeVO extends TwmpSysAuthority {

    List<AuthorityTreeVO> childrenList;

    public List<AuthorityTreeVO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<AuthorityTreeVO> childrenList) {
        this.childrenList = childrenList;
    }
}
