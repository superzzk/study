package redis_study.chapter.eight;

import java.util.Arrays;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.evaluateShard();
    }
    private void evaluateShard() {
        // Configure Jedis sharded connection pool.
        JedisShardInfo shard_1 = new JedisShardInfo("localhost", 6379);
        JedisShardInfo shard_2 = new JedisShardInfo("localhost", 6380);
        ShardedJedis shardedJedis = new ShardedJedis(Arrays.asList(shard_1, shard_2));
        // Looping to set values in the shard we have created..
        for (int i = 0; i < 10; i++) {
            shardedJedis.set("KEY-" + i, "myvalue-" + i);
        }
        // Lets try to read all the values from SHARD -1
        for (int i = 0; i < 10; i++) {
            Jedis jedis = new Jedis("localhost", 6379);
            if (jedis.get("KEY-" + i) != null) {
                System.out.println(jedis.get("KEY-" + i) + " : this is stored in SHARD-1");
            }
        }
        // Lets try to read all the values from SHARD -2
        for (int i = 0; i < 10; i++) {
            Jedis jedis = new Jedis("localhost", 6380);
            if (jedis.get("KEY-" + i) != null) {
                System.out.println(jedis.get("KEY-" + i) + " : this is stored in SHARD-2");
            }
        }
        // Lets try to read data from the sharded jedis.
        for (int i = 0; i < 10; i++) {
            if (shardedJedis.get("KEY-" + i) != null) {
                System.out.println(shardedJedis.get("KEY-" + i));
            }
        }
    }
}