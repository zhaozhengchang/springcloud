package com.ceiec.twmp.tmp.utils.tools.redis;

import com.ceiec.twmp.tmp.common.exception.JedisException;
import com.ceiec.twmp.tmp.utils.tools.ServerIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.*;

/**
 * CreateDate：2019/1/14
 * Author：wenliang
 * Description: Redis工具类
 **/

@Component
public class RedisUtils {

    private final static String REDIS_LOCK = "redisLock";

    private final static String SPLIT = ":";

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";
    /**
     * redis lock time
     */
    private final static Integer REDIS_LOCK_TIME = 5*60*1000;
    /**
     * redis配置对象类
     */
    private static RedisConfig redisConfig;
    /**
     * 哨兵模式redis连接池
     */
    private static JedisSentinelPool jedisSentinelPool = null;
    /**
     * 普通redis连接池
     */
    private static JedisPool jedisPool = null;
    /**
     * 哨兵模式
     */
    private final static String SENTINEL_MODE = "sentinel";
    /**
     * 普通模式
     */
    private final static String SINGLE_MODE = "single";

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired(required = true)
    public  void setRedisConfig(RedisConfig redisConfig) {
        RedisUtils.redisConfig = redisConfig;
    }


    /*************************************************************************************************************************************
     ** @Description  init redis connection
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/1 9:31
     **
     ************************************************************************************************************************************/
    private synchronized static void initRedisConnection() {
        if (jedisSentinelPool == null && jedisPool == null) {
            try {
                if (SENTINEL_MODE.equals(redisConfig.getMode())) {
                    Set<String> sentinels = new HashSet<String>();
                    String ips = redisConfig.getHost();
                    String ports = redisConfig.getPort();
                    String[] ipArray = ips.split(",");
                    String[] portArray = ports.split(",");
                    for (int i = 0; i < ipArray.length; i++) {
                        HostAndPort hostAndPort = new HostAndPort(ipArray[i], Integer.parseInt(portArray[i]));
                        sentinels.add(hostAndPort.toString());
                    }
                    // 创建JedisSentinelPool对象
                    jedisSentinelPool = new JedisSentinelPool(redisConfig.getMasterName(), sentinels);
                } else {
                    // 创建jedis池配置实例
                    JedisPoolConfig config = new JedisPoolConfig();
                    //连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
                    config.setBlockWhenExhausted(true);
                    //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
                    config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
                    //是否启用pool的jmx管理功能, 默认true
                    config.setJmxEnabled(true);
                    //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
                    config.setJmxNamePrefix("pool");
                    //是否启用后进先出, 默认true
                    config.setLifo(true);
                    //最大空闲连接数, 默认8个
                    config.setMaxIdle(redisConfig.getMaxIdle());
                    //最大连接数, 默认8个
                    config.setMaxTotal(redisConfig.getMaxActive());
                    //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
                    config.setMaxWaitMillis(30 * 1000);
                    //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
                    config.setMinEvictableIdleTimeMillis(1800000);
                    //最小空闲连接数, 默认0
                    config.setMinIdle(0);
                    //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
                    config.setNumTestsPerEvictionRun(3);
                    //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
                    config.setSoftMinEvictableIdleTimeMillis(3 * 60 * 1000);
                    //在获取连接的时候检查有效性, 默认false
                    config.setTestOnBorrow(false);
                    //在空闲时检查有效性, 默认false
                    config.setTestWhileIdle(false);
                    //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
                    config.setTimeBetweenEvictionRunsMillis(-1);
                    //根据配置实例化jedis池
                    jedisPool = new JedisPool(config, redisConfig.getHost(), Integer.parseInt(redisConfig.getPort()), 100000);
                }
            } catch (Exception e) {
                logger.warn("connect redis failed.", e);
                throw new JedisException(e.getMessage());
            }
        }
    }


   /*************************************************************************************************************************************
    ** @Description  get jedis
    ** @param:
    ** @Return redis.clients.jedis.Jedis
    ** @Author Ding
    ** @Date 2019/3/1 9:31
    **
    ************************************************************************************************************************************/
    public static Jedis getJedis() {
        Jedis jedis = null;
        try {
            initRedisConnection();
            if (SINGLE_MODE.equals(redisConfig.getMode())) {
                jedis = jedisPool.getResource();
            } else {
                jedis = jedisSentinelPool.getResource();
            }
        } catch (Exception e) {
            logger.warn("get jedis is failed.", e);
            throw new JedisException(e.getMessage());
        }
        return jedis;
    }

/*************************************************************************************************************************************
 ** @Description close jedis
 ** @param: jedis
 ** @Return void
 ** @Author Ding
 ** @Date 2019/3/1 9:36
 **
 ************************************************************************************************************************************/
    private static void close(final Jedis jedis) {

        if (jedis != null) {
            jedis.close();
        }
    }


    /*************************************************************************************************************************************
 ** @Description get data from redis
 ** @param: key
 ** @Return java.lang.String
 ** @Author Ding
 ** @Date 2019/3/1 9:33
 **
 ************************************************************************************************************************************/
    public static String get(String key) {
        Jedis jedis = getJedis();
        String str = null;
        try {
            str = jedis.get(key);
        } catch (Exception e) {
            logger.warn("get data from redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return str;

    }


/*************************************************************************************************************************************
 ** @Description del data in redis
 ** @param: key
 ** @Return boolean
 ** @Author Ding
 ** @Date 2019/3/1 9:33
 **
 ************************************************************************************************************************************/
    public static boolean del(String key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } catch (Exception e) {
            logger.warn("del data in redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }


   /*************************************************************************************************************************************
    ** @Description save data into redis
    ** @param: key
    * @param: str
    ** @Return boolean
    ** @Author Ding
    ** @Date 2019/3/1 9:34
    **
    ************************************************************************************************************************************/
    public static boolean save(String key, String str) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, str);
        } catch (Exception e) {
           logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }


    /*************************************************************************************************************************************
     ** @Description hash save
     ** @param: key
     ** @param: map
     ** @Return boolean
     ** @Author Ding
     ** @Date 2019/4/2 14:26
     **
     ************************************************************************************************************************************/
    public static boolean hashSet(String key, Map map){
        Jedis jedis = getJedis();
        try {
            jedis.hmset(key, map);
        } catch (Exception e) {
            logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }


    public static boolean hashSet(String key, String field, String value){
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }


    public static boolean hashDel(String key, String field){
        Jedis jedis = getJedis();
        try {
            jedis.hdel(key, field);
        } catch (Exception e) {
            logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }

    /*************************************************************************************************************************************
     ** @Description hash get
     ** @param: key
     ** @Return java.util.Map
     ** @Author Ding
     ** @Date 2019/4/2 14:26
     **
     ************************************************************************************************************************************/
    public static Map hashGet(String key){
        Jedis jedis = getJedis();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
    }



    public static String hashGet(String key, String field){
        Jedis jedis = getJedis();
        try {
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.warn("save data into redis is failed, the reason is {}", e);
            throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
    }


 /*************************************************************************************************************************************
  ** @Description save data into redis and the data is available in xx seconds
  ** @param: key
  * @param: seconds  the available time
  * @param: str
  ** @Return boolean
  ** @Author Ding
  ** @Date 2019/3/1 9:34
  **
  ************************************************************************************************************************************/
    public static boolean saveEx(String key, int seconds, String str) {
        Jedis jedis = getJedis();
        try {
            jedis.setex(key, seconds, str);
        } catch (Exception e) {
           logger.warn("save data into redis in available time is failed, the reason is {}", e);
           throw new JedisException(e.getMessage());
        }finally {
            close(jedis);
        }
        return true;
    }


///*************************************************************************************************************************************
// ** @Description get time from redis
// ** @param:
// ** @Return java.util.Date
// ** @Author Ding
// ** @Date 2019/3/1 9:40
// **
// ************************************************************************************************************************************/
//    public static Date getNowTime() {
//        Jedis jedis = RedisUtils.getJedis();
//        Date date = null;
//        try {
//            date = new Date(Long.parseLong(jedis.time().get(0)) * 1000);
//        } catch (Exception e) {
//            logger.warn("get time from jedis is failed, the reason is {}", e.getMessage());
//            throw new JedisException(e.getMessage());
//        }finally {
//            close(jedis);
//        }
//        return date;
//
//    }



    /*************************************************************************************************************************************
     ** @Description  check redis key, if not exists then set the redis key
     ** @param: key    the redis key name
     ** @param: seconds   the key available time
     ** @Return boolean    if true, means the key exists
     ** @Author Ding
     ** @Date 2019/3/26 9:22
     **
     ************************************************************************************************************************************/
    public static boolean checkRedisKey(String key, int seconds){

        key = REDIS_LOCK+SPLIT+key;

        Jedis jedis = getJedis();
        try{
            String result = jedis.set(key, ServerIdUtils.getServiceId(), SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds);

            if (LOCK_SUCCESS.equals(result)) {
                return false;
            }
        }catch (Exception e){
            logger.error("check redis lock key is failed", e);
        }finally {
            close(jedis);
        }
        return true;

    }


    /*************************************************************************************************************************************
     ** @Description check redis key, if not exists then set the redis key, the default lock time is 5 minutes
     ** @param: key the redis key name
     ** @Return boolean if true, means the key exists
     ** @Author Ding
     ** @Date 2019/3/26 9:30
     **
     ************************************************************************************************************************************/
    public static boolean checkRedisKey(String key){
        key = REDIS_LOCK+SPLIT+key;

        Jedis jedis = getJedis();
        try{
            String result = jedis.set(key, ServerIdUtils.getServiceId(), SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, REDIS_LOCK_TIME);

            if (LOCK_SUCCESS.equals(result)) {
                return false;
            }
        }catch (Exception e){
            logger.error("check redis lock key is failed", e);
        }finally {
            close(jedis);
        }
        return true;
    }





    /*************************************************************************************************************************************
     ** @Description delete redis key
     ** @param: key
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 11:29
     **
     ************************************************************************************************************************************/
    public static void delRedisKey(String key){
        key = REDIS_LOCK+SPLIT+key;

        Jedis jedis = getJedis();
        try{
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            jedis.eval(script, Collections.singletonList(key), Collections.singletonList(ServerIdUtils.getServiceId()));
        }catch (Exception e){
            logger.error("del redis lock key is failed", e);
        }finally {
            close(jedis);
        }

    }



}