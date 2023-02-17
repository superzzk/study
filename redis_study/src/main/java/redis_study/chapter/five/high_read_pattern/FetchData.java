package redis_study.chapter.five.high_read_pattern;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * This is the class responsible for fetching the data from the Redis nodes
 * This class is called in a multi-threaded mode
 * This class is passed at start time so the last result returned will
 * indicate the total time the execution has taken place
 **/
public class FetchData implements Runnable {
    private int endnumber = 0;
    private int startnumber = 0;
    private JedisPool jedisPool = null;
    private long starttime = 0;

    public FetchData(int number, long starttime, String localhost, int port) {
        endnumber = number * 100000;
        startnumber = endnumber - 100000;
        this.starttime = starttime;
        jedisPool = new JedisPool(localhost, port);
    }

    @Override
    public void run() {
        Jedis jedis = jedisPool.getResource();
        for (int index = startnumber; index < endnumber; index++) {
            System.out.println("printing values for index = message" + index + " = " + jedis.get("mesasge-" + index));
            long endtime = System.currentTimeMillis();
            System.out.println("TOTAL TIME" + (endtime - starttime));
        }
    }
}