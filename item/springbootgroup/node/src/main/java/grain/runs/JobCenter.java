package grain.runs;

import org.apache.catalina.util.ServerInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wulifu
 */
public class JobCenter {

    private static Map<Integer, Object> taskList = new ConcurrentHashMap<>();
    private  static  ServerInfo serverInfo = new ServerInfo();

    public static synchronized ServerInfo getServerInfo() {
        return serverInfo;
    }




}
