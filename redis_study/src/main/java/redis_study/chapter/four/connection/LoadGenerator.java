package redis_study.chapter.four.connection;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

public class LoadGenerator implements Runnable{
    private List<Thread> clients = new ArrayList<>();
    private final static Integer threadCount = 5;
    public LoadGenerator() {
        for(int i=0;i< threadCount ;i++){
            clients.add(new Thread(new Sample()));
        }
    }
    @Override
    public void run() {
        for(int i=0;i< threadCount ;i++){
            clients.get(i).start();
        }
    }
    public class Sample implements Runnable{
        Jedis jedis = ConnectionManager.get();
        @Override
        public void run() {
            int x=0;
            while(!Thread.currentThread().isInterrupted()){
                jedis.sadd(Thread.currentThread().getName(), "Some text"+ x);
                x++;
            }
            ConnectionManager.get();
        }
    }
}