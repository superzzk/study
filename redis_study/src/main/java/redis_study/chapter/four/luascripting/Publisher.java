package redis_study.chapter.four.luascripting;

import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

import java.util.Arrays;

/**
 * reliable messaging
 */
public class Publisher {
    //public String luaScript = Reader.read("D:\\pathtoscript \\RELIABLEMSGING.txt");
    public static final String luaScript = "local payload = loadstring(\"return\"..ARGV[1])() " +
            "local result = redis.call(\"PUBLISH\",payload.publishto, payload.msg) " +
            "if result==0 then " +
            "redis.call('SADD','MSGBOX',payload.msg) " +
            "return 'stored messages: '..ARGV[1] " +
            "else " +
            "return 'consumed messages: '..ARGV[1] " +
            "end";

    public static void main(String[] args) {
        Publisher test = new Publisher();
        test.sendingAreliableMessages();
    }
    private void sendingAreliableMessages() {
        Jedis jedis = ConnectionManager.get();
        String result = (String) jedis.eval(luaScript, Arrays.asList(""),
                Arrays.asList("{type='channel',publishto='client1',msg='"+ System.currentTimeMillis()+"'}"));
        System.out.println(result);
        ConnectionManager.set(jedis);
    }
}