package demo;

import redis.clients.jedis.Jedis;

import java.util.Objects;

public class Test {
    public static void run(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("localhost", 6379);
            out(jedis.ping());
            out(jedis.get("kv"));
            out(jedis.set("kv", "kv"));
            out(jedis.get("kv"));
            out(jedis.lpush("list", "l1"));
            out(jedis.lpush("list", "l2"));
            out(jedis.lpush("list", "l3"));
            jedis.lrange("list", 0, 2).forEach(a -> out(a));
            jedis.lrange("list", 1, jedis.llen("list")).forEach(a -> out(a));
            jedis.lrange("list", 0, 3).forEach(a -> out(a));
        } catch (Exception ignored) {

        } finally {
            if (!Objects.isNull(jedis)) {
                jedis.close();
            }
        }
    }

    private static void out(Object o) {
        System.out.println(o);
    }
}
