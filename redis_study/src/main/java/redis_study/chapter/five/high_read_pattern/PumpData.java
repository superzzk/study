package redis_study.chapter.five.high_read_pattern;

import redis.clients.jedis.Jedis;

/**
 * This is the class responsible for pushing the data into the main node
 * ° The data pushing is single threaded
 **/
public class PumpData implements Runnable {
    @Override
    public void run() {
        Jedis jedis = new Jedis("localhost",6379);
        for(int index=1;index<1000000;index++){
            jedis.append("mesasge-"+index, "my dumb value "+ index);
        }
    }
}