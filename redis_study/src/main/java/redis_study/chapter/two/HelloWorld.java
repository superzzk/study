package redis_study.chapter.two;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class HelloWorld {
    private JedisPool pool = new JedisPool(new JedisPoolConfig(), "172.28.104.25");

    private void test() {
        try {
            Jedis jedis = pool.getResource();
            jedis.auth("cmcc1234@");
            jedis.set("MSG", "Hello World");
            String result = jedis.get("MSG");
            System.out.println(" MSG : " + result);
            jedis.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            pool.destroy();
        }
    }

    public static void main(String args[]) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.test();
    }
}