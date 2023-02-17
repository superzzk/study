package redis_study.chapter.four.connection;

import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

public class TestEchoAndPing {

    public static void main(String[] args) throws InterruptedException {
        TestEchoAndPing echoAndPing = new TestEchoAndPing();
        Thread thread = new Thread(new LoadGenerator());
        thread.start();
        while(true){
            Thread.sleep(1000);
            echoAndPing.testPing();
            echoAndPing.testEcho();
        }
    }
    private void testPing() {
        long start = System.currentTimeMillis();
        Jedis jedis = ConnectionManager.get();
        System.out.println(jedis.ping() + " in " + (System.currentTimeMillis()-start) + " milliseconds");
        ConnectionManager.set(jedis);
    }
    private void testEcho() {
        long start = System.currentTimeMillis();
        Jedis jedis = ConnectionManager.get();
        System.out.println(jedis.echo("hi Redis ") + " in " + (System.currentTimeMillis()-start) + " milliseconds");
        ConnectionManager.set(jedis);
    }
}