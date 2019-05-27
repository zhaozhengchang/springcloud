package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpLogLogin;
import com.ceiec.twmp.tmp.entity.TwmpLogOperate;
import com.ceiec.twmp.tmp.vo.systemlog.login.query.LoginLogQueryVO;
import com.ceiec.twmp.tmp.vo.systemlog.login.result.LoginLogResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpLogLoginMapper")
public interface TwmpLogLoginMapper extends Mapper<TwmpLogLogin> {

    List<LoginLogResultVO> queryLoginLogByPage(LoginLogQueryVO loginLogVO);

    Long countLoginLog(LoginLogQueryVO loginLogQueryVO);

    void insertLogList(List<TwmpLogOperate> list);

    List<LoginLogResultVO> getLoginLogList(LoginLogQueryVO loginLogQueryVO);
}