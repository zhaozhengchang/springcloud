package com.ceiec.twmp.tmp.authorize.dao;

import com.ceiec.twmp.tmp.authorize_interface.vo.DatasourceInfoVO;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: factory to generate datasource for each project
 **/
public class DatasourceFactory {

    /** datasource map */
    private static Map<String, DriverManagerDataSource> dataSourceMap = new HashMap<>();

    /**
     * get datasource by given information
     *
     * @param datasourceInfo given datasource information
     * @return proper datasource
     */
    public static DriverManagerDataSource getDatasource(DatasourceInfoVO datasourceInfo) {
        //generate datasource key for dataSourceMap
        String url = datasourceInfo.getDsUrl();
        String userName = datasourceInfo.getDsUserName();
        String password = datasourceInfo.getDsPassword();
        String datasourceKey = url + userName + password;

        //if datasource is generated, return it
        if (dataSourceMap.containsKey(datasourceKey)) {
            return dataSourceMap.get(datasourceKey);
        }

        //the datasource hasn't been generated yet, generate a new one
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(datasourceInfo.getDriverClassName());
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSourceMap.put(datasourceKey, dataSource);
        return dataSource;
    }
}
