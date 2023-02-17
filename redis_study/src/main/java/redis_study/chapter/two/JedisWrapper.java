package redis_study.chapter.two;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class JedisWrapper {
    static JedisPool pool = new JedisPool(new JedisPoolConfig(), "172.28.104.25");
    public void set(String key,String value){
        Jedis jedis = pool.getResource();
        jedis.auth("cmcc1234@");
        jedis.set(key, value);
        jedis.close();
    }
    public String get(String key){
        Jedis jedis = pool.getResource();
        jedis.auth("cmcc1234@");
        String result = jedis.get("MSG");
        jedis.close();
        return result;
    }
}