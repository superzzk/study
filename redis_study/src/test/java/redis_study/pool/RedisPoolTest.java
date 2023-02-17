package redis_study.pool;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisExhaustedPoolException;
import redis_study.chapter.four.pipeline.ConnectionManager;

public class RedisPoolTest {

    @Test(timeout = 5000)
    public void testGet() {
        for(int i=0; i<30; i++) {
            Jedis jedis = RedisPool.get();
            System.out.println("get "+i);
            RedisPool.returnResource(jedis);
        }
    }

    @Test(expected = JedisExhaustedPoolException.class)
    public void testGetNotReturn() {
        for(int i=0; i<30; i++) {
            Jedis jedis = RedisPool.get();
            System.out.println("get "+i);
        }
    }
}