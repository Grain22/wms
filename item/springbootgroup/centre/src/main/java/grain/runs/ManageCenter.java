package grain.runs;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;
import grain.configs.GlobalYmlConfigParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wulifu
 */
@Slf4j
@Component
public class ManageCenter {
    private static Map<String, NodeInfo> nodes = new ConcurrentHashMap<>(5);
    protected final GlobalYmlConfigParams params;

    public ManageCenter(GlobalYmlConfigParams params) {
        this.params = params;
    }


    public static void addNode(String nodeAddress) {
        if (nodes.containsKey(nodeAddress)) {
            throw new RuntimeException("节点已存在");
        }
        nodes.put(nodeAddress, new NodeInfo());
        log.info("节点已注册 {}", nodeAddress);
    }

    public static void checkAvailable(String host) {

    }

    public void nodeCheck() {
        nodes.forEach((k, v) -> {
            checkAvailable(k);
        });
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    static class NodeInfo {
        Integer running = 0;
        Map<Integer, Object> runningMap = new HashMap<>();
        Integer complete = 0;
        Map<Integer, Object> completeMap = new HashMap<>();
        Integer wait = 0;
        Map<Integer, Object> waitMap = new HashMap<>();
        Integer error = 0;
        Map<Integer, Object> errorMap = new HashMap<>();
        Integer cancel = 0;
        Map<Integer, Object> cancelMap = new HashMap<>();
        long updateTime;
    }
}


