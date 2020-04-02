package grain.run;

import com.alibaba.fastjson.JSON;
import grain.task.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wulifu
 */
@Slf4j
public class RunTask {

    Task task;

    public RunTask(Task task) {
        this.task = task;
    }

    public void runTask() {
        runTask(this.task);
    }

    public static void runTask(Task task) {
        try {
            log.info("正在执行 任务 {} 任务内容 {}", task.getTaskId(), JSON.toJSONString(task));
            Thread.sleep(task.getTaskLong());
        } catch (Exception e) {

        }
    }
}