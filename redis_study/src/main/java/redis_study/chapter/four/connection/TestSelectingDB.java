package redis_study.chapter.four.connection;

import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

public class TestSelectingDB {
    public static void main(String[] args) {
        TestSelectingDB test = new TestSelectingDB();
        test.commandSelect();
    }
    private void commandSelect() {
        Jedis jedis = ConnectionManager.get();
        jedis.select(1);
        jedis.set("msg", "Hello world");
        System.out.println(jedis.get("msg"));
        jedis.select(2);
        System.out.println(jedis.get("msg"));
    }
}
