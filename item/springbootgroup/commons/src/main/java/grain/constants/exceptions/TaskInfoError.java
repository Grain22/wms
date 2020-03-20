package grain.constants.exceptions;

import org.springframework.util.StopWatch;

/**
 * @author wulifu
 */
public class TaskInfoError extends RuntimeException {
    public TaskInfoError() {
        super("任务信息异常");
    }

    public TaskInfoError(String string) {
        super("任务信息异常: " + string);
    }
}
