package redis_study.chapter.three;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class MySortedSetTest {
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

    public static void main(String[] args) {
        MySortedSetTest mySortedSetTest = new MySortedSetTest();
        mySortedSetTest.test();
    }
    private void test() {
        int i =0;
        Jedis jedis = this.getResource();
        jedis.zadd("purchase", 0, "learning-redis");
        jedis.zadd("purchase", 0, "cassandra");
        jedis.zadd("purchase", 0, "hadoop");
        System.out.println(jedis.zcard("purchase"));
        // purchase a 4 books on redis
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");
        // purchase a 2 books on cassandra
        jedis.zincrby("purchase", 1, "cassandra");
        jedis.zincrby("purchase", 1, "cassandra");
        // purchase a 1 book on hadoop
        jedis.zincrby("purchase", 1, "hadoop");
        System.out.println(jedis.zcount("purchase", 3, 4));
        System.out.println(++i + "------------------------------------------------");

        System.out.println(jedis.zrange("purchase", 0, 2));
        System.out.println(jedis.zrangeByScore("purchase", 3, 4));
        System.out.println(jedis.zrank("purchase", "learning-redis"));
        System.out.println(jedis.zrank("purchase", "cassandra"));
        System.out.println(jedis.zrank("purchase", "hadoop"));
        System.out.println(++i + "------------------------------------------------");

        System.out.println(jedis.zrevrank("purchase", "learning-redis"));
        System.out.println(jedis.zrevrank("purchase", "cassandra"));
        System.out.println(jedis.zrevrank("purchase", "hadoop"));
        System.out.println(jedis.zscore("purchase", "learning-redis"));
        System.out.println(jedis.zscore("purchase", "cassandra"));
        System.out.println(jedis.zscore("purchase", "hadoop"));
        System.out.println(++i + "------------------------------------------------");

        jedis.zunionstore("purchase:nosql", "purchase");
        System.out.println("-- " + jedis.zrange("purchase:nosql",0,-1));
        System.out.println("-- " + jedis.zrank("purchase:nosql","learningredis"));
        jedis.zrem("purchase:nosql", "hadoop");
        System.out.println("-- " + jedis.zrange("purchase:nosql",0,-1));
        System.out.println(++i + "------------------------------------------------");

        jedis.zremrangeByRank("purchase:nosql", 0,0);
        System.out.println("-- " + jedis.zrange("purchase:nosql",0,-1));
        jedis.zremrangeByScore("purchase:nosql", 3,4);
        System.out.println("-- " + jedis.zrange("purchase:nosql",0,-1));
        this.setResource(jedis);
    }
}