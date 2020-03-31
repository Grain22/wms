package grain;

import grain.node.Node;
import grain.node.NodeCommand;
import grain.node.NodeTable;
import grain.task.Task;
import grain.task.TaskInfo;
import grain.task.TaskTable;
import org.apache.catalina.DistributedManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tools.bean.DateUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wulifu
 */
@EnableAsync
@EnableScheduling
public class NodeManager {

    private static int trustTryTimes = 2;

    private static int id = 0;

    private static synchronized int getId() {
        return id++;
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
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(100); i++) {
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


    public void taskDistribute(){
        /**
         * 新加入任务
         */
        List<TaskInfo> taskInfos = TaskTable.waitingTask();

        /**
         * 任务排序
         */
        taskInfos = TaskTable.sortPriorityDate(taskInfos);

        DistributedManager

    }




    /**
     * 可用节点检查
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 60)
    public void nodeAvailableCheck() {
        NodeTable.nodes.forEach(node -> checkNodeCanReach(node));
    }

    /**
     * 节点信任度检查
     *
     * @param node 节点信息
     * @return
     */
    private int checkNodeCanReach(Node node) {
        if (NodeCommand.checkAvailable(node)) {
            /**
             * 节点可达
             */node.setNodeLastCheckDateTime(DateUtils.getTimeStringLong());
            return Node.normal;
        } else if (node.getNodeRetryTimes() < trustTryTimes) {
            /**
             * 节点失联次数处于可信任范围
             */
            node.setNodeRetryTimes(node.getNodeRetryTimes() + 1);
            return Node.wait;
        } else {
            /**
             * 节点放弃
             */
            node.setNodeAvailable(false);
            return Node.drop;
        }
    }
}
