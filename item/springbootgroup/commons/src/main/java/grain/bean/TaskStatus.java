package grain.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskStatus {
    Integer taskId;
    String taskInfo;
    Integer taskStatus;
    String renderNode;
    long addTime;
    long sendTime;
    boolean retry = false;
    public static final int WAIT_TO_SEND = 1;
    public static final int SEND_TO_NODE = 2;
    public static final int renderSuccess = 3;
    public static final int renderError = 4;
    public static final int renderOutTime = 5;
}
