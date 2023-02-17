package redis_study.chapter.five.high_write_pattern;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class MyShards {
    List<JedisShardInfo> shards = new ArrayList<>();

    public static void main(String[] args) {
        MyShards test = new MyShards();
        test.setup();
        test.putdata();
    }

    private void setup() {
        JedisShardInfo master0 = new JedisShardInfo("localhost", 6379);
        JedisShardInfo master1 = new JedisShardInfo("localhost", 6369);
        shards.add(master0);
        shards.add(master1);
    }

    private void putdata() {
        //ShardedJedisPool pool = new ShardedJedisPool(new Config(),shards);
        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

        for (int index = 0; index < 10; index++) {
            ShardedJedis jedis = pool.getResource();
            jedis.set("mykey" + index, "my value is " + index);
            jedis.close();
        }
        for (int index = 0; index < 10; index++) {
            ShardedJedis jedis = pool.getResource();
            System.out.println("The value for the key is " + jedis.get("mykey" + index));
            System.out.println("The following information is from master running on port : "
                    + jedis.getShardInfo("mykey" + index).getPort());
            jedis.close();
        }
    }
}