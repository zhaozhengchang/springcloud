package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysRoleAuthority;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Component(value = "twmpSysRoleAuthorityMapper")
public interface TwmpSysRoleAuthorityMapper extends Mapper<TwmpSysRoleAuthority> {

    /**
     * 删除角色权限表此角色的所有权限
     * @param roleId
     */
    void deleteAllAuthByRoleId(Long roleId);

    /**
     * 批量插入角色权限表
     * @param authorityList
     */
    void insertRoleAuthorityBatch(List<TwmpSysRoleAuthority> authorityList);

    /**
     * 根据roleId获取角色权限信息
     * @param roleId
     * @return
     */
    Map queryRoleAuthority(Long roleId);
}