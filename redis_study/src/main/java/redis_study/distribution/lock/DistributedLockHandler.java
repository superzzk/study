package redis_study.distribution.lock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * redis分布式锁, 使用时具体根据业务来设置超时时间和锁的持有时间!!!
 * 注意: 锁的key尽可能使用业务变量, 保证锁的细粒度, 避免串行化
 */
@SuppressWarnings("Duplicates")
public class DistributedLockHandler {
    private static final Logger log = LoggerFactory.getLogger(DistributedLockHandler.class);

    private static final long LOCK_TRY_INTERVAL = 50L;// 默认多久尝试获取一次锁, 需考虑redis服务器压力

    private static final long LOCK_TRY_TIMEOUT = 200L;// 默认尝试多久, 需考虑并发压力

    private static final int DEFAULT_EXPIRE_TIME = 3000;   // 默认key过期时间, 需考虑业务执行时长

    private static final String LOCK_SUCCESS = "OK";    // set方法执行成功后的返回值

    private static final String DEFAULT_VALUE = "v"; // set方法的value字段, 这里默认设置v

    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 尝试获取全局锁
     *
     * @param key 锁名
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(String key) {
        return getLock(key, DEFAULT_VALUE, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 尝试获取全局锁
     *
     * @param key     锁名
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(String key, long timeout) {
        return getLock(key, DEFAULT_VALUE, timeout, LOCK_TRY_INTERVAL, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 尝试获取全局锁
     *
     * @param key         锁名
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(String key, long timeout, long tryInterval) {
        return getLock(key, DEFAULT_VALUE, timeout, tryInterval, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 尝试获取全局锁
     *
     * @param key            锁名
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(String key, long timeout, long tryInterval, int lockExpireTime) {
        return getLock(key, DEFAULT_VALUE, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 尝试获取全局锁, 只尝试一次
     *
     * @param key 锁名
     * @return true 获取成功，false获取失败
     */
    public boolean onceTryLock(String key) {
        return getLock(key, DEFAULT_VALUE, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 尝试获取全局锁, 只尝试一次
     *
     * @param key            锁名
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean onceTryLock(String key, int lockExpireTime) {
        return getLock(key, DEFAULT_VALUE, lockExpireTime);
    }

    /**
     * 获取全局锁
     *
     * @param key         锁名
     * @param value       锁value, 如果要保证加锁和解锁是同一个客户端的话, 这个参数用来指定特定客户端
     * @param expireTime  锁的超时时间
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少ms尝试一次
     */
    public boolean getLock(String key, String value, long timeout, long tryInterval, int expireTime) {
        try (Jedis jedis = getJedis()) {
            // 锁如果为空, 获取锁失败
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                return false;
            }
            long startTime = System.currentTimeMillis();  // 开始时间戳
            do {
                SetParams setParams = new SetParams();
                setParams.ex(expireTime);
                setParams.nx();
                String result = jedis.set(key, value,setParams);
                if (LOCK_SUCCESS.equals(result)) {  // 返回成功，表示加锁成功
                    return true;
                }
                if (System.currentTimeMillis() - startTime > timeout) { // 尝试超过了设定超时时间后直接跳出循环，获取锁失败
                    log.info("获取锁超时: {}", System.currentTimeMillis() - startTime);
                    return false;
                }
                Thread.sleep(tryInterval);  // 循环时设置时间差
            }
            while (true);   // 只要锁存在，循环
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取全局锁(无超时后循环重试机制，拿不到直接返回false)
     *
     * @param key        锁名
     * @param value      锁value, 如果要保证加锁和解锁是同一个客户端的话, 这个参数用来指定特定客户端
     * @param expireTime 超时时间
     */
    public boolean getLock(String key, String value, int expireTime) {
        try (Jedis jedis = getJedis()) {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                return false;
            }
            // 参数: key, value, key不存在set操作存在就不做任何操作, 可设置超时时间, 具体超时时间
            SetParams setParams = new SetParams();
            setParams.ex(expireTime);
            setParams.nx();
            String result = jedis.set(key, value, setParams);
            if (LOCK_SUCCESS.equals(result)) {  // 返回成功，表示加锁成功
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param key 锁名
     */
    public void releaseLock(String key) {
        try (Jedis jedis = getJedis()) {
            if (!StringUtils.isEmpty(key)) {
                Long del = jedis.del(key);
                log.info("锁名：{}，是否释放成功：{}", key, del);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}