package redis_study.chapter.four.luascripting;

import redis.clients.jedis.Jedis;
import redis_study.chapter.four.pipeline.ConnectionManager;

import java.util.Arrays;

public class TestLuaScript {
    String luaScript = "local data= redis.call('GET',KEYS[1]) " +
            "if data==ARGV[1] then " +
            "redis.call('SET',KEYS[1],ARGV[2]) " +
            "return \"The value that got sent is = \"..ARGV[2] " +
            "else " +
            "redis.call('SET',KEYS[1],ARGV[3]) " +
            "return \"The value that got sent is = \"..ARGV[3] end";
    //public String luaScript = Reader.read("F:\\temp\\LuaScript.txt");

    public static void main(String[] args) {
        TestLuaScript test = new TestLuaScript();
        test.luaScript();
    }
    private void luaScript() {
        Jedis jedis = ConnectionManager.get();

        jedis.set("msg", "Learning Redis");
        String r1 = (String) jedis.eval(luaScript, Arrays. asList("msg"),
                Arrays.asList("Learning Redis",
                        "Now I am learning Lua for Redis",
                        "prepare for the test again"));
        System.out.println(r1);

        String r2 = (String) jedis.eval(luaScript, Arrays. asList("msg"),
                Arrays.asList("Learning Redis",
                        "Now I am learning Lua for Redis",
                        "prepare for the test again"));
        System.out.println(r2);
        ConnectionManager.set(jedis);
        ConnectionManager.close();
    }
}