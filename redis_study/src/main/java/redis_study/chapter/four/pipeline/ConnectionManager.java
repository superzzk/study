package redis_study.chapter.four.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
public class ConnectionManager {
    private static JedisPool jedisPool = new JedisPool("172.28.104.25");
    public static Jedis get(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth("cmcc1234@");
        return jedis;
    }
    public static void set(Jedis jedis){
        jedis.close();
    }
    public static void close(){
        jedisPool.destroy();
    }
}