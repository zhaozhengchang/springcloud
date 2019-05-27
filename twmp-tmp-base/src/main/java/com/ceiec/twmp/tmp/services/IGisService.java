package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpGisAddress;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: gis service
 * @create 2019-04-01 10:02
 **/
public interface IGisService {

    List<TwmpGisAddress> queryAddressByName(String addressName);

}
