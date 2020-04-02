package grain.node;

import grain.task.TaskInfo;

/**
 * @author wulifu
 */
public class NodeCommand {
    public static boolean checkAvailable(Node node) {
        try {
            // todo 节点可用检查
            /* 发送请求 获取节点标签 (id)*/
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean sendTask(Node firstOne, TaskInfo a) {
        try {
            // todo 任务发送至节点
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean cancel(TaskInfo task) {
        try {
            // todo 取消旧任务节点
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
