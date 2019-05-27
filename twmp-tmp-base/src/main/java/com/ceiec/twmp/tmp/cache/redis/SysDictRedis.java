package com.ceiec.twmp.tmp.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.mysql.jdbc.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: system dict redis
 * @create 2019-03-04 11:07
 **/
public class SysDictRedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description save dict in redis
     ** @param: map
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/4 11:10
     **
     ************************************************************************************************************************************/
    public static void saveSysDict(Map<String , List<DictResultVo>> map){
        save(DICT_KEY, map);
    }


    /*************************************************************************************************************************************
     ** @Description get all dict from redis
     ** @param:
     ** @Return java.util.Map<java.lang.String,java.util.List<com.ceiec.twmp.tmp.vo.dict.result.DictResultVo>>
     ** @Author Ding
     ** @Date 2019/3/4 11:11
     **
     ************************************************************************************************************************************/
    public static Map<String, List<DictResultVo>> getAllSysDict(){

        String str = get(DICT_KEY);
        if(!StringUtils.isNullOrEmpty(str)){
            Map<String, JSONArray> map = JSON.parseObject(str, Map.class);

            Map<String, List<DictResultVo>> map2 = new HashMap<>();
            for (Map.Entry<String, JSONArray> entry : map.entrySet()) {
                List<DictResultVo> list = JSON.parseArray(map.get(entry.getKey()).toString(), DictResultVo.class);
                map2.put(entry.getKey(), list);
            }

            return map2;
        }

        return null;
    }
}
