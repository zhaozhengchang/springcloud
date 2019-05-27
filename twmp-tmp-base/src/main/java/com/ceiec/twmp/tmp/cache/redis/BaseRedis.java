package com.ceiec.twmp.tmp.cache.redis;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreateDate：2019/2/28
 * Author：Ding
 * Description: base redis
 **/
public class BaseRedis extends RedisUtils {


    /**
     * the split key for redis key
     */
    protected final static String SPLIT = ":";
    /**
     * the key for user redis
     */
    protected final static String USER_INFO = "loginUser";
    /**
     * the key for user list who is online
     */
    protected final static String USER_ONLINE = "userOnline";
    /**
     * the key for dict redis
     */
    protected final static String DICT_KEY = "dictKey";
    /**
     * the key for device redis
     */
    protected final static String DEVICE_INFO = "deviceInfo";
    /**
     *  the key for user subscription redis
     */
    protected final static String USER_SUBSCRIPTION = "userSubscription";
    /**
     * the key for paa redis
     */
    protected final static String ALARM_PENDING_ALLOCATION = "alarmPendingAllocation";
    /**
     * the key for system message
     */
    protected final static String SYSTEM_MESSAGE = "systemMessage";
    /**
     * the key for gps file
     */
    protected final static String GPS_FILE = "gpsFile";
    /**
     * the key for gps file job
     */
    protected final static String GPS_FILE_JOB = "gpsFileJob";
    //the key for department
    protected final static String DEPARTMENT_INFO = "departmentInfo";

    protected final static String ALARM_NUMBER = "alarmNumber";


    /**
     * Get object.
     *
     * @param key    the key
     * @param id     the id
     * @param object the object
     * @return the object
     */
    protected static Object get(String key, String id, Class<?> object){
        if(object == String.class){
            return RedisUtils.get(key+ SPLIT +id);
        }else{
            String str =  RedisUtils.get(key+ SPLIT +id);
            if(!StringUtils.isNullOrEmpty(str)){
            	return JSON.parseObject(str,object);
            }else {
                return null;
            }
        }
    }

    /**
     * Get object.
     *
     * @param key    the key
     * @param object the object
     * @return the object
     */
    protected static Object get(String key, Class<?> object){
        if(object == String.class){
            return RedisUtils.get(key);
        }else{
            String str =  RedisUtils.get(key);
            if(!StringUtils.isNullOrEmpty(str)){
            	return JSON.parseObject(str,object);
            }else {
                return null;
            }
        }
    }


    /**
     * Save.
     *
     * @param key    the key
     * @param id     the id
     * @param object the object
     */
    protected static void save(String key, String id, Object object){
        if(object instanceof String){
            RedisUtils.save(key+ SPLIT +id, (String)object);
        }else{
            RedisUtils.save(key+ SPLIT +id, JSON.toJSONString(object));
        }
    }


    protected static void save(String key,Object object){
        if(object instanceof String){
            RedisUtils.save(key, (String)object);
        }else{
            RedisUtils.save(key, JSON.toJSONString(object));
        }
    }

    /**
     * Save ex.
     *
     * @param key     the key
     * @param id      the id
     * @param seconds the seconds
     * @param object  the object
     */
    public static void saveEx(String key, String id, int seconds, Object object){
        if(seconds == 0){
            save(key, id, object);
            return;
        }

        if(object instanceof String){
            RedisUtils.saveEx(key+ SPLIT +id, seconds,  (String)object);
        }else{
            RedisUtils.saveEx(key+ SPLIT +id, seconds,  JSON.toJSONString(object));
        }
    }

    /**
     * Save.
     *
     * @param key    the key
     * @param map the map
     */
    protected static void saveMap(String key, Map map){
       RedisUtils.hashSet(key, map);
    }

    /*************************************************************************************************************************************
     ** @Description save
     ** @param: key
     ** @param: field
     ** @param: object
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/2 14:23
     **
     ************************************************************************************************************************************/
    protected static void saveByMap(String key, String field, Object object){
        if(object == String.class){
            RedisUtils.hashSet(key, field, (String)object);
        }else {
            RedisUtils.hashSet(key, field, JSON.toJSONString(object));
        }
    }


    /*************************************************************************************************************************************
     ** @Description get
     ** @param: key
     ** @Return java.util.Map
     ** @Author Ding
     ** @Date 2019/4/2 14:27
     **
     ************************************************************************************************************************************/
    protected static Map getMap(String key, Class<?> value){
        Map<String, String> map = RedisUtils.hashGet(key);
        if(map!=null && map.size()>0){
            Map<String, Object> map2 = new HashMap<>();
            for(String mapKey: map.keySet()){
                map2.put(mapKey, JSON.parseObject(map.get(mapKey), value));
            }
            return map2;
        }

        return null;
    }

    /*************************************************************************************************************************************
     ** @Description get
     ** @param: key
     ** @param: field
     ** @param: object
     ** @Return java.lang.Object
     ** @Author Ding
     ** @Date 2019/4/2 14:28
     **
     ************************************************************************************************************************************/
    protected static Object getByMap(String key, String field, Object object){
        if(object instanceof String){
            return RedisUtils.hashGet(key);
        }else{
            String str =  RedisUtils.hashGet(key, field);
            if(!StringUtils.isNullOrEmpty(str)){
                return JSON.parseObject(str,object.getClass());
            }else {
                return null;
            }
        }
    }



    /**
     * Delete.
     *
     * @param key the key
     * @param id  the id
     */
    protected static void delete(String key, String id){
        RedisUtils.del(key+ SPLIT +id);
    }




    /*************************************************************************************************************************************
     ** @Description delete
     ** @param: key
     ** @param: field
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/2 14:39
     **
     ************************************************************************************************************************************/
    protected static void deleteByMap(String key, String field){
        RedisUtils.hashDel(key, field);
    }


}
