package grain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wulifu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskInfo {
    private int taskId;
    private String nodeId;
    private Task task;
    private int status;
    int retryTimes;

    public static final int waiting = 1;
    public static final int sent = 2;
    public static final int rendering = 3;
    public static final int completed = 4;
    public static final int canceled = 5;
    public static final int error = 6;
}
