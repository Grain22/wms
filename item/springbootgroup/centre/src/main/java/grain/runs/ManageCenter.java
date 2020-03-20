package grain.runs;

import grain.configs.GlobalYmlConfigParams;
import grain.constants.NodeInfo;
import grain.constants.TaskInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * @author wulifu
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class ManageCenter {
    public static final int min = 1000 * 60;


    private static Queue<TaskInfo> waitTaskList = new ConcurrentLinkedQueue<>();
    private static List<TaskStatus> taskStatus = new ArrayList<>();
    private static Map<String, NodeInfo> nodes = new ConcurrentHashMap<>(5);
    protected final GlobalYmlConfigParams params;

    public ManageCenter(GlobalYmlConfigParams params) {
        this.params = params;
    }

    /**
     * 更新节点信息
     *
     * @param nodeAddress 节点地址
     */
    public static void addNode(String nodeAddress) {
        if (nodes.containsKey(nodeAddress)) {
            log.info("节点已存在 {}", nodeAddress);
            return;
        }
        nodes.put(nodeAddress, new NodeInfo());
        log.info("节点已注册 {} 当前节点数量 {} 节点表 {}", nodeAddress, nodes.size(), nodes.keySet().stream().collect(Collectors.joining("|")));
    }

    public static void updateNodeStatus(String ipAddress, NodeInfo remote) {
        NodeInfo local = nodes.get(ipAddress);
        if (remote.equals(remote)) {
            local.setUpdateTime(System.currentTimeMillis());
        } else {

        }
    }

    /**
     * 任务收集
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void taskCollection() {
        int cont = new Random().nextInt(50);
        for (int i = 0; i < cont; i++) {
            waitTaskList.add(new TaskInfo(new Random().nextInt() * 1000));
        }
        /**
         * 任务在规定时间内未处理完
         */
        List<TaskStatus> taskStatuses = oldTaskHandle(min * 30);
        /**
         * todo 节点处理和重分派
         */
        for (TaskStatus status : taskStatuses) {
            String distributeNodeHost = status.getDistributeNodeHost();
            boolean nodeAvailable = NodeCommand.checkNodeAvailable(distributeNodeHost, params.getNodePort(), distributeNodeHost);
            try {
            } catch (Exception e) {
                nodes.remove(distributeNodeHost);
            }
        }

        distribute();
    }

    private List<TaskStatus> oldTaskHandle(int time) {
        return taskStatus.stream().filter(a -> (System.currentTimeMillis() - a.getDistributeTime()) > time).collect(Collectors.toList());
    }

    private void distribute() {

    }


   /* @Async
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void nodeCheck() {
        nodes.forEach((k, v) -> {
            NodeInfo nodeInfo = getNodeStatus(k);
            updateNodeStatus(k, nodeInfo);
        });
    }*/

    @Data
    static class TaskStatus {
        Integer taskId;
        long distributeTime;
        String distributeNodeHost;
        Integer status;
    }
}

