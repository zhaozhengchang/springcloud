package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.entity.TwmpSysRoleAuthority;
import com.ceiec.twmp.tmp.mapper.TwmpSysRoleAuthorityMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysRoleMapper;
import com.ceiec.twmp.tmp.services.IRoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-05 17:12
 * Description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleAuthorityServiceImpl implements IRoleAuthorityService {

    @Autowired
    private TwmpSysRoleAuthorityMapper twmpSysRoleAuthorityMapper;

    @Autowired
    private TwmpSysRoleMapper twmpSysRoleMapper;

    @Override
    public Map queryRoleAuthority(String token, Long roleId) {
        Map map = new HashMap(5);
        TwmpSysRoleAuthority twmpSysRoleAuthority = new TwmpSysRoleAuthority();
        twmpSysRoleAuthority.setRoleId(roleId);
        List<TwmpSysRoleAuthority> list = twmpSysRoleAuthorityMapper.select(twmpSysRoleAuthority);
        StringBuffer str = new StringBuffer();
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i == (list.size() - 1)) {
                    str.append(list.get(i).getAuthorityId());
                } else {
                    str.append(list.get(i).getAuthorityId() + ",");
                }
            }
        }
        map.put("rolePermission", str.toString());
        map.put("acceptMessageSubType",twmpSysRoleMapper.selectByPrimaryKey(roleId).getAcceptMessageSubType());
        return map;
    }
}
