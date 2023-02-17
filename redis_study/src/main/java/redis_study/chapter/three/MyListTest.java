package redis_study.chapter.three;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ListPosition;
public class MyListTest {
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

    public static void main(String[] args) throws InterruptedException {
        MyListTest myListTest = new MyListTest();
        myListTest.test();
    }
    private void test() {
        Jedis jedis = this.getResource();
        System.out.println(jedis.del("mykey4list"));
        String commonkey="mykey4list";
        String commonkey1="mykey4list1";
        for(int index=0;index<3;index++){
            jedis.lpush(commonkey, "Message - " + index);
        }
        System.out.println("lrange:"+jedis.lrange(commonkey, 0, -1));

        for(int index=3;index<6;index++){
            jedis.rpush(commonkey, "Message - " + index);
        }
        System.out.println("lrange:"+jedis.lrange(commonkey, 0, -1));

        System.out.println("lindex:"+jedis.lindex(commonkey, 0));
        System.out.println("linsert:"+jedis.linsert(commonkey,ListPosition.AFTER,"Message - 5", "Message - 7"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println(jedis.linsert(commonkey,ListPosition.BEFORE,"Message - 7", "Message - 6"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println("llen:"+jedis.llen(commonkey));
        System.out.println(jedis.lpop(commonkey));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println(jedis.lpush(commonkey,"Message - 2","Message -1.9"));
        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println("lpushx:"+jedis.lpushx(commonkey,"Message - 1.8"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println("lrem:"+jedis.lrem(commonkey,0,"Message - 1.8"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println("lrem:"+jedis.lrem(commonkey,-1,"Message - 7"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println("lset:"+jedis.lset(commonkey,7,"Message - 7"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println("ltrim:"+jedis.ltrim(commonkey,2,-4));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        jedis.rpoplpush(commonkey, commonkey1);
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println(jedis.lrange(commonkey1, 0, -1));
        
        this.setResource(jedis);
    }
}