package redis_study.chapter.three;

import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class MyHashesTest {
    private JedisPool pool = new JedisPool(new JedisPoolConfig(),"172.28.104.25");
    Jedis jedis = null;

    public Jedis getResource() {
        jedis = pool.getResource();
        jedis.auth("cmcc1234@");
        return jedis;
    }

    public void setResource(Jedis jedis) {
        jedis.close();
    }

    public static void main(String[] args)throws InterruptedException {
        MyHashesTest myHashesTest = new MyHashesTest();
        myHashesTest.test();
    }
    private void test() {
        Jedis jedis = this.getResource();
        String commonkey = "learning redis";
        jedis.hset(commonkey, "publisher", "Packt Publisher");
        jedis.hset(commonkey, "author", "Vinoo Das");
        System.out.println(jedis.hgetAll(commonkey));
        Map<String,String> attributes = new HashMap<>();
        attributes.put("ISBN", "XX-XX-XX-XX");
        attributes.put("tags", "Redis,NoSQL");
        attributes.put("pages", "250");
        attributes.put("weight", "200.56");
        jedis.hmset(commonkey, attributes);
        System.out.println(jedis.hgetAll(commonkey));
        System.out.println(jedis.hget(commonkey,"publisher"));
        System.out.println(jedis.hmget(commonkey,"publisher","author"));
        System.out.println("hvals:"+jedis.hvals(commonkey));

        System.out.println("hkeys:"+jedis.hkeys(commonkey));
        System.out.println("hexists:"+jedis.hexists(commonkey, "cost"));
        System.out.println(jedis.hlen(commonkey));
        System.out.println(jedis.hincrBy(commonkey,"pages",10));
        System.out.println(jedis.hincrByFloat(commonkey,"weight",1.1) + "gms");

        System.out.println("hdel:"+jedis.hdel(commonkey,"weight-in-gms"));
        System.out.println(jedis.hgetAll(commonkey));
        this.setResource(jedis);
    }
}