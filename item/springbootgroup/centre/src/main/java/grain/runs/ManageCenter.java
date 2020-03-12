package grain.runs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wulifu
 */
@Slf4j
public class ManageCenter {
    private static Map<String, NodeInfo> nodes = new ConcurrentHashMap<>(5);

    public static void addNode(String nodeAddress) {
        if (nodes.containsKey(nodeAddress)) {
            throw new RuntimeException("节点已存在");
        }
        nodes.put(nodeAddress, new NodeInfo(0, 0, 0, 0, 0, 0, System.currentTimeMillis()));
        log.info("节点已注册 {}", nodeAddress);
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    static class NodeInfo {
        Integer cont;
        Integer running;
        Integer complete;
        Integer wait;
        Integer error;
        Integer cancel;
        long updateTime;
    }
}
