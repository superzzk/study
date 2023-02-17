package redis_study.chapter.four.luascripting;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis_study.chapter.four.pipeline.ConnectionManager;

import java.util.Arrays;

public class SimpleMsgSubscriber {
    static Thread lostMsgWorker;
    static Thread msgWorker;

    public static void main(String[] args) {
        SimpleMsgSubscriber source = new SimpleMsgSubscriber();
        msgWorker = new Thread(source.new MsgProcessor());
        lostMsgWorker = new Thread(source.new LostMsgProcessor());
        msgWorker.start();
        lostMsgWorker.start();
    }

    public class MsgProcessor extends JedisPubSub implements Runnable {
        Jedis jedis = ConnectionManager.get();

        @Override
        public void run() {
            jedis.subscribe(this, "client1");
        }

        @Override
        public void onMessage(String arg0, String arg1) {
            System.out.println("processing the msg = " + arg1);
        }

        @Override
        public void onPMessage(String arg0, String arg1, String arg2) {
        }

        @Override
        public void onPSubscribe(String arg0, int arg1) {
        }

        @Override
        public void onPUnsubscribe(String arg0, int arg1) {
        }

        @Override
        public void onSubscribe(String arg0, int arg1) {
        }

        @Override
        public void onUnsubscribe(String arg0, int arg1) {
        }
    }

    public class LostMsgProcessor implements Runnable {
        @Override
        public void run() {
            Jedis jedis = ConnectionManager.get();
            String msg;
            while ((msg = jedis.spop("MSGBOX")) != null) {
                MessageHandler.push(msg);
            }
        }
    }

    public static class MessageHandler {
        static Jedis jedis = ConnectionManager.get();

        public static void push(String msg) {
            jedis.eval(Publisher.luaScript, Arrays.
                    asList(""), Arrays.asList("{type='channel',publishto='client1',msg='"
                    + msg + "'}"));
        }
    }
}