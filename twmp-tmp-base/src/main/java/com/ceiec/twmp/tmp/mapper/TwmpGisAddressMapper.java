package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpGisAddress;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TwmpGisAddressMapper extends Mapper<TwmpGisAddress> {

    List<TwmpGisAddress> queryAddressByName (String addressName, int num);
}