package com.ceiec.twmp.tmp.utils.tools.snowflake;

import java.math.BigInteger;

/**
 * <p>Description:SnowflakeId生成工具类 </p>
 *
 * @author Hexiao
 * @version 1.0 创建时间:2017-11-29 18:12
 */
public class SnowflakeIdWorkerUtil {

    private static final SnowflakeIdWorker WORKER = new SnowflakeIdWorker(0, 0);

    public static BigInteger generateId(){
        return WORKER.nextId();
    }

    public static Long generateLongId(){
        return WORKER.nextId().longValue();
    }
}
