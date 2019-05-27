package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysRole;
import com.ceiec.twmp.tmp.entity.TwmpSysRoleAuthority;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component(value = "twmpSysRoleMapper")
public interface TwmpSysRoleMapper extends Mapper<TwmpSysRole> {

    /**
     * 角色分页列表
     * @param roleName
     * @return
     */
    List<TwmpSysRole> queryRoleByPage(String roleName);

    /**
     * 根据roleName查询角色数量
     * @param roleName
     * @return
     */
    Integer getRoleCountByRoleName(String roleName);

}