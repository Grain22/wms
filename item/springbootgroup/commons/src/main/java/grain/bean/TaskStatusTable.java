package grain.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wulifu
 */
@Data
@Slf4j
public class TaskStatusTable {

    private static List<TaskStatus> list = new ArrayList<>();

    public static synchronized void addTask(Integer taskId, String taskInfo) {
        if (isEmpty(taskId)) {
            list.add(new TaskStatus().setTaskId(taskId).setTaskInfo(taskInfo).setTaskStatus(TaskStatus.WAIT_TO_SEND).setAddTime(System.currentTimeMillis()));
            return;
        }
        log.info("任务id已存在");
    }

    public static synchronized void sendTask(Integer taskId, String node) {
        if (isEmpty(taskId)) {
            log.info("任务不存在");
            return;
        }
        Iterator<TaskStatus> iterator = list.iterator();
        while (iterator.hasNext()) {
            TaskStatus taskStatus = iterator.next();
            if (taskId.equals(taskStatus.taskId)) {
                taskStatus.setTaskStatus(TaskStatus.SEND_TO_NODE);
                taskStatus.setRenderNode(node);
                taskStatus.setSendTime(System.currentTimeMillis());
                break;
            }
        }
    }

    public static synchronized boolean retry(Integer taskId, String node) {
        if (isEmpty(taskId)) {
            log.info("任务不存在");
            return false;
        }
        Iterator<TaskStatus> iterator = list.iterator();
        while (iterator.hasNext()) {
            TaskStatus next = iterator.next();
            if (taskId.equals(next.taskId)) {
                if (next.retry) {
                    iterator.remove();
                    return false;
                }
                next.setRenderNode(node).setSendTime(System.currentTimeMillis()).setRetry(true);
                return true;
            }
        }
        return false;
    }

    public static synchronized void completeTask(Integer taskId) {
        if (isEmpty(taskId)) {
            log.info("任务不存在");
            return;
        }
        list.removeIf(a -> a.getTaskId().equals(taskId));
    }

    public static synchronized List<TaskStatus> listByNode(String node) {
        return list.stream().filter(a -> a.getRenderNode().equals(node)).collect(Collectors.toList());
    }

    private static boolean isEmpty(Integer taskId) {
        return list.stream().filter(a -> a.getTaskId().equals(taskId)).collect(Collectors.toList()).isEmpty();
    }

    public static synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    public static synchronized List<TaskStatus> outTime(long time) {
        return list.stream().filter(a -> a.getSendTime() != 0 && a.getRenderNode() != null && a.getTaskStatus().equals(TaskStatus.SEND_TO_NODE) && (System.currentTimeMillis() - a.getSendTime()) > time).collect(Collectors.toList());
    }

    public static synchronized List<TaskStatus> waitList() {
        return list.stream().filter(a -> a.getTaskStatus().equals(TaskStatus.WAIT_TO_SEND)).collect(Collectors.toList());
    }

    public static TaskStatus find(String taskId) {
        for (TaskStatus a : list) {
            if (a.getTaskId().equals(Integer.valueOf(taskId))) {
                return a;
            }
        }
        return null;
    }

    public static synchronized void disableNode(String host) {
        for (TaskStatus a : list) {
            if (a.getRenderNode().equals(host)) {
                a.setRetry(false).setTaskStatus(TaskStatus.WAIT_TO_SEND).setSendTime(0).setRenderNode(null);
            }
        }
    }
}
