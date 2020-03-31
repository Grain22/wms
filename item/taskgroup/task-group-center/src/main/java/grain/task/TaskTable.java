package grain.task;

import grain.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author wulifu
 */
public class TaskTable {
    private static List<TaskInfo> taskInfos = new CopyOnWriteArrayList<>();

    public static int inQueue(Node node) {
        return Math.toIntExact(taskInfos.stream().filter(taskInfo -> taskInfo.getNodeId().equals(node.getNodeId())).count());
    }

    public static void addTasks(ArrayList<Task> tasks) {
        tasks.forEach(TaskTable::addTask);
    }

    public static void addTask(Task task) {
        synchronized (taskInfos) {
            taskInfos.add(new TaskInfo().setStatus(TaskInfo.waiting).setTask(task).setTaskId(task.taskId));
        }
    }

    public static List<TaskInfo> waitingTask() {
        synchronized (taskInfos) {
            return taskInfos.stream().filter(a -> a.getStatus() == TaskInfo.waiting).collect(Collectors.toList());
        }
    }

    public static List<TaskInfo> sortPriorityDate(List<TaskInfo> list){
        list.sort((o1,o2)->{
            if (o1.getTask().getPriority() == o2.getTask().getPriority()) {
                return o1.getTask().getAddedDate().compareTo(o2.getTask().getAddedDate());
            }
            return o1.getTask().getPriority() - o2.getTask().getPriority();
        });
        return list;
    }
}
