package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.entity.TwmpGisAddress;
import com.ceiec.twmp.tmp.mapper.TwmpGisAddressMapper;
import com.ceiec.twmp.tmp.services.IGisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: gis service impl
 * @create 2019-04-01 10:03
 **/
@Service
public class GisServiceImpl implements IGisService {

    @Autowired
    private TwmpGisAddressMapper twmpGisAddressMapper;

    @Override
    public List<TwmpGisAddress> queryAddressByName(String addressName) {
        return twmpGisAddressMapper.queryAddressByName(addressName, 10);
    }
}
