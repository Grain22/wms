package grain.runs;

import com.alibaba.fastjson.JSON;
import grain.bean.TaskStatus;
import grain.bean.TaskStatusTable;
import grain.configs.GlobalYmlConfigParams;
import grain.constants.NodeInfo;
import grain.constants.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author wulifu
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class ManageCenter {
    public static final int MIN = 1000 * 60;

    private static int cont = 0;
    private static Map<String, NodeInfo> nodes = new ConcurrentHashMap<>(5);
    protected final GlobalYmlConfigParams params;

    public ManageCenter(GlobalYmlConfigParams params) {
        this.params = params;
    }

    private synchronized static int getCont() {
        return cont++;
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
        nodes.put(nodeAddress, new NodeInfo().setNodeHost(nodeAddress));
        log.info("节点已注册 {} 当前节点数量 {} 节点表 {}", nodeAddress, nodes.size(), nodes.keySet().stream().collect(Collectors.joining("|")));
    }

    public static void complete(String taskId, String host) {
        TaskStatus taskStatus = TaskStatusTable.find(taskId);
        if (taskStatus != null && taskStatus.getRenderNode().equals(host)) {
            TaskStatusTable.completeTask(Integer.valueOf(taskId));
            nodes.get(host).getRunningMap().remove(taskId);
            nodes.get(host).getCompleteMap().put(Integer.valueOf(taskId), JSON.parseObject(taskStatus.getTaskInfo(), TaskInfo.class));
        }
    }

    /**
     * 任务收集
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void taskCollection() {
        /**
         * 节点可用检测
         */
        Set<String> hosts = nodes.keySet();
        for (String host : hosts) {
            try {
                NodeCommand.checkNodeAvailable(host, params.getNodePort());
            } catch (Exception e) {
                nodes.remove(host);
                TaskStatusTable.disableNode(host);
            }
        }
        int cont = new Random().nextInt(50);
        for (int i = 0; i < cont; i++) {
            TaskInfo taskInfo = new TaskInfo(getCont(), new Random().nextInt(60) * 1000);
            TaskStatusTable.addTask(taskInfo.getId(), JSON.toJSONString(taskInfo));
        }
        if (!TaskStatusTable.isEmpty() && nodes.isEmpty()) {
            log.info("无节点,等待下次检查 当前任务数量 {}", TaskStatusTable.waitList().size());
            return;
        }
        /**
         * 处理超时任务
         */
        retryList(TaskStatusTable.outTime(MIN * 1));
        /**
         * 处理新增加任务
         */
        distributeList(TaskStatusTable.waitList());
    }

    private List<NodeInfo> nodeOrderByTaskNumber() {
        if (nodes.isEmpty()) {
            throw new RuntimeException("节点数量为空");
        }
        List<NodeInfo> list = nodes.values().stream().sorted(Comparator.comparingInt(o -> (o.getWaitMap().size() + o.getRunningMap().size()))).collect(Collectors.toList());
        return list;
    }

    private void retryList(List<TaskStatus> outTime) {
        outTime.stream().forEach(a -> {
            retry(a);
        });
    }

    private void retry(TaskStatus a) {
        Integer taskId = a.getTaskId();
        String taskInfo = a.getTaskInfo();
        String oldHost = a.getRenderNode();
        List<NodeInfo> nodeInfos = nodeOrderByTaskNumber();
        String newHost = nodeInfos.get(0).getNodeHost();
        if (oldHost.equals(newHost)) {
            if (nodeInfos.size() > 1) {
                newHost = nodeInfos.get(1).getNodeHost();
            } else {
                log.info("当前无其他节点 {} 超时 {}", a.getTaskId(), System.currentTimeMillis() - a.getSendTime());
                return;
            }
        }
        nodes.get(oldHost).getWaitMap().remove(taskId);
        NodeCommand.dropTask(oldHost, params.getNodePort(), String.valueOf(taskId));
        boolean retry = TaskStatusTable.retry(taskId, newHost);
        if (retry) {
            log.info("重试成功");
            nodes.get(newHost).getWaitMap().put(taskId, JSON.parseObject(taskInfo, TaskInfo.class));
            NodeCommand.addTask(newHost, params.getNodePort(), String.valueOf(taskId), taskInfo);
        } else {
            log.info("二次重试失败");
        }
    }

    private void distributeList(List<TaskStatus> taskStatuses) {
        taskStatuses.stream().forEach(a -> {
            distribute(a);
        });
    }

    private void distribute(TaskStatus a) {
        String nodeHost = nodeOrderByTaskNumber().get(0).getNodeHost();
        TaskStatusTable.sendTask(a.getTaskId(), nodeHost);
        NodeCommand.addTask(nodeHost, params.getNodePort(), String.valueOf(a.getTaskId()), a.getTaskInfo());
    }

}

