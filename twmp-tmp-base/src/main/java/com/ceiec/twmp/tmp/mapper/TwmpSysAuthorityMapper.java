package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysAuthority;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpSysAuthorityMapper")
public interface TwmpSysAuthorityMapper extends Mapper<TwmpSysAuthority> {

    List<TwmpSysAuthority> getAuthorityByRoleId(Long roleId);
}