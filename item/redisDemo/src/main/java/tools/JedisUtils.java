package tools;

import lombok.Data;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author laowu
 * @version demo
 * 2019/6/26 16:09
 */
@Data
public class JedisUtils {
    private String host = "localhost";
    private int port = 6379;
    private Jedis jedis;

    private JedisUtils(){
        if (Objects.isNull(jedis)) {
            this.jedis = new Jedis(this.host, this.port);
        }
    }

    public JedisUtils(Jedis jedis) {
        this.jedis = jedis;
    }
}
