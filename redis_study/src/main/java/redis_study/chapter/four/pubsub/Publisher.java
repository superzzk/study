package redis_study.chapter.four.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class Publisher {
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

    private void publisher() {
        Jedis jedis = this.getResource();
        jedis.publish("news", "Houstan calling texas... message published !!");

        pool.close();
    }
    public static void main(String[] args) {
        Publisher test = new Publisher();
        test.publisher();
    }
}