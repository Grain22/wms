package grain.node;

import grain.task.TaskTable;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wulifu
 */
@Data
public class NodeTable {

    public static List<Node> nodes = new CopyOnWriteArrayList<>();

    public static List<Node> sortByPriority() {
        nodes.sort((o1, o2) -> {
            if (TaskTable.inQueue(o1) == TaskTable.inQueue(o2)) {
                return o1.getNodePriority() - o2.getNodePriority();
            }
            return TaskTable.inQueue(o1) - TaskTable.inQueue(o2);
        });
        return nodes;
    }
}
