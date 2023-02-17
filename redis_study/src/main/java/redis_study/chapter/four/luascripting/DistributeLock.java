package redis_study.chapter.four.luascripting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import redis_study.chapter.four.pipeline.ConnectionManager;

import java.util.Collections;

/**
 * @author zhangzhongkun
 * @since 2019-07-22 18:56
 **/
public class DistributeLock {
    private static final Logger logger = LoggerFactory.getLogger(DistributeLock.class);

    private static final String RELEASE_LOCK_LUA_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    //释放锁成功返回值
    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;

    /**
     * 尝试获取分布式锁
     *
     * @param key    锁
     * @param uuid  请求标识
     * @param expireTime 超期时间，毫秒
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String key, String uuid, int expireTime) {
        logger.info("try lock: key:{},uuid:{}", key, uuid);
        Jedis jedis = ConnectionManager.get();
        SetParams setParams = new SetParams();
        setParams.ex(expireTime);
        setParams.nx();
        String result = jedis.set(key, uuid, setParams);

        logger.info("lock result:  key:{},uuid:{},status:{}", key, uuid, result);
        return "OK".equals(result);
    }

    /**
     * 释放锁
     */
    public boolean releaseLock(String key, String uuid) {
        logger.info("release lock: key:{},uuid:{}", key, uuid);
        Jedis jedis = ConnectionManager.get();
        Object result = jedis.eval(RELEASE_LOCK_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(uuid));

        logger.info("release result:  key:{},uuid:{},status:{}", key, uuid, result);
        return  result.equals(RELEASE_LOCK_SUCCESS_RESULT);
    }
}
