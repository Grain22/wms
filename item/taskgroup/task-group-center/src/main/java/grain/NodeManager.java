package grain;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import grain.node.Node;
import grain.node.NodeCommand;
import grain.node.NodeTable;
import grain.task.Task;
import grain.task.TaskInfo;
import grain.task.TaskTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wulifu
 */
@Slf4j
@EnableAsync
@EnableScheduling
public class NodeManager {

    private static int id = 0;

    private static synchronized int getId() {
        return id++;
    }

    private static long timeOut = 1000 * 60 * 30;

    public static void increaseTimeOutLimit() {
        timeOut *= 1.05;
    }

    /**
     * 任务收集
     * 定时 每 分钟
     * 加入任务表
     * 状态等待中
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void taskCollect() {
        int hundred = 100;
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(hundred); i++) {
            try {
                SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
                int wait = instanceStrong.nextInt(100);
                tasks.add(new Task().setTaskId(getId()).setTaskLong(wait * 100));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        TaskTable.addTasks(tasks);
    }

    /**
     * 任务分配
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void taskDistribute() {
        /*
         * 新加入任务收集
         */
        List<TaskInfo> taskInfos = TaskTable.waitingTask();
        /*
         * 任务排序
         * 排序方式 优先级 > 加入时间
         */
        taskInfos = TaskTable.sortPriorityDate(taskInfos);
        /*
         * 节点任务分配
         */
        distributeTasks(taskInfos);
    }

    private void distributeTasks(List<TaskInfo> taskInfos) {
        if (NodeTable.isEmpty()) {
            /*无节点 跳过分配 继续等待*/
            log.info("当前无节点 等待下个任务周期");
            return;
        }
        taskInfos.forEach(this::distributeTask);
    }

    private void distributeTask(TaskInfo taskInfo) {
        Node firstOne = NodeTable.firstOne();
        Node secondOne = NodeTable.secondOne();
        log.info("任务 {} 发送到 {}", JSON.toJSONString(taskInfo), JSON.toJSONString(firstOne));
        if (NodeCommand.sendTask(firstOne, taskInfo)) {
            log.info("任务 {} 发送到 {} 成功", JSON.toJSONString(taskInfo), JSON.toJSONString(firstOne));
            taskInfo.sendSuccess(firstOne);
        } else if (firstOne.getNodeId().equals(secondOne.getNodeId())) {
            log.info("节点唯一 再次尝试 {}", JSON.toJSONString(firstOne));
            reDistributeTask(taskInfo, firstOne);
        } else {
            log.info("尝试顺位节点 {}", JSON.toJSONString(secondOne));
            reDistributeTask(taskInfo, secondOne);
            /*节点下调置信度*/
            firstOne.decreasePriority();
        }
    }

    private void reDistributeTask(TaskInfo taskInfo, Node node) {
        taskInfo.retry(node);
        if (NodeCommand.sendTask(node, taskInfo)) {
            log.info("任务 {} 发送到 {} 成功", JSON.toJSONString(taskInfo), JSON.toJSONString(node));
            taskInfo.sendSuccess(node);
        } else {
            log.info("任务 {} 发送到 {} 失败", JSON.toJSONString(taskInfo), JSON.toJSONString(node));
            taskInfo.fail();
        }
    }

    /**
     * 超时任务处理
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void taskOutTimeCheck() {
        /*所有超时任务*/
        List<TaskInfo> outTimeTasks = TaskTable.outTime(timeOut);
        /*超时任务节点归并*/
        Map<String, List<TaskInfo>> map = new HashMap<>(2);
        outTimeTasks.forEach(a -> map.merge(a.getNodeId(), ImmutableList.of(a), (ov, nv) -> {
            ov.addAll(nv);
            return ov;
        }));
        /*可用节点*/
        List<Node> available = NodeTable.available();
        /*超时任务节点 匹配 可用任务节点*/
        List<String> outTimeTasksNodeAvailable = map.keySet().stream().filter(a -> available.stream().map(Node::getNodeId).collect(Collectors.toList()).contains(a)).collect(Collectors.toList());
        if (outTimeTasksNodeAvailable.size() == available.size()) {
            /*所有可用节点都有超时任务*/
            NodeManager.increaseTimeOutLimit();
        } else {
            /*不存在超时任务节点*/
            List<Node> collect = available.stream().filter(a -> !outTimeTasksNodeAvailable.contains(a.getNodeId())).collect(Collectors.toList());
            for (Node node : collect) {
                node.increasePriority();
            }
            /*超时任务重分配*/
            for (TaskInfo outTimeTask : outTimeTasks) {
                NodeCommand.cancel(outTimeTask);
                distributeTask(outTimeTask);
            }
        }
    }


    /**
     * 节点优先度
     * 据情况上调或下调优先级
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void nodePriorityCheck() {
        /*平均节点任务数量*/
        int average = TaskTable.nodeAverage();
        for (Node node : NodeTable.available()) {
            /*节点队列中数量*/
            int i = TaskTable.inQueueCount(node);
            if (i < average - 2) {
                /*比平均还少两个以上,上调优先度*/
                node.increasePriority();
            } else if (i > average + 2) {
                /*比平均还多两个以上,下调优先度*/
                node.decreasePriority();
            }
        }
    }


    /**
     * 节点检查
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void nodeAvailableCheck() {
        NodeTable.NODES.forEach(this::checkNodeCanReach);
        List<String> disable = NodeTable.disable();
        if (!disable.isEmpty()) {
            TaskTable.disableNodeTask(disable);
        }
    }

    private int checkNodeCanReach(Node node) {
        int trustTryTimes = 2;
        if (NodeCommand.checkAvailable(node)) {
            /*节点可达 更新检查信息*/
            node.refreshCheckDate();
            return Node.normal;
        } else if (node.getNodeRetryTimes() < trustTryTimes) {
            /*节点失联次数处于可信任范围*/
            node.nodeWait();
            return Node.wait;
        } else {
            /*节点放弃*/
            node.unBelieve();
            return Node.drop;
        }
    }
}
