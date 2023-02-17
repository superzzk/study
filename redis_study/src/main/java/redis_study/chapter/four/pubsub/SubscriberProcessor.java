package redis_study.chapter.four.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SubscriberProcessor implements Runnable {
    private JedisPool pool = new JedisPool(new JedisPoolConfig(),"172.28.104.25");

    public Jedis getResource() {
        jedis = pool.getResource();
        jedis.auth("cmcc1234@");
        return jedis;
    }

    public void setResource(Jedis jedis) {
        jedis.close();
    }

    private Subscriber subscriber = new Subscriber();
    private Thread simpleThread;
    private Jedis jedis = getResource();

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        SubscriberProcessor test = new SubscriberProcessor();
        test.subscriberProcessor();
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.unsubscribe();

        test.pool.close();
    }

    private void unsubscribe() {
        simpleThread.interrupt();
        if (subscriber.isSubscribed()) {
            subscriber.unsubscribe();
        }
    }

    private void subscriberProcessor() {
        simpleThread = new Thread(this);
        simpleThread.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            jedis.subscribe(subscriber, "news");
            //jedis.psubscribe(subscriber, "news.*");
        }
    }
}