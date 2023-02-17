package redis_study.chapter.four.luascripting;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

/**
 * @author zhangzhongkun
 * @since 2019-07-22 19:03
 **/
public class LuaScriptTest {

    @Test
    public void testLock() {
        DistributeLock distributeLock = new DistributeLock();
        Jedis jedis = ConnectionManager.get();
        String testKey = "key_for_lock_test";
        String testUuid = "aaaa";
        Assert.assertFalse(distributeLock.releaseLock(testKey, testUuid) );

        Assert.assertTrue(distributeLock.tryGetDistributedLock(testKey, testUuid, 3000));

        Assert.assertEquals(testUuid, jedis.get(testKey));

        System.out.println("expire time: "+ jedis.ttl(testKey) );

        Assert.assertTrue(distributeLock.releaseLock(testKey, testUuid) );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertNull(jedis.get(testKey));
    }

}
