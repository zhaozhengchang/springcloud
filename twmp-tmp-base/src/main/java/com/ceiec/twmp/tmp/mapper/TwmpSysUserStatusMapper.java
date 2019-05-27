package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSysUserStatus;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
@Component(value = "twmpSysUserStatusMapper")
public interface TwmpSysUserStatusMapper extends Mapper<TwmpSysUserStatus> {

}