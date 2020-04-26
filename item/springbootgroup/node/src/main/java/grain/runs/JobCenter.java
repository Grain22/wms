package grain.runs;

import grain.bean.ServerInfo;
import grain.configs.GlobalParams;
import grain.constants.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.thread.CustomThreadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wulifu
 */
@Slf4j
@Component
public class JobCenter {

    private static ThreadPoolExecutor task_handle = CustomThreadPool.createThreadPool("task handle");
    private static Map<Integer, TaskInfo> taskList = new ConcurrentHashMap<>();
    private static ServerInfo serverInfo = new ServerInfo();
    protected final GlobalParams params;

    public JobCenter(GlobalParams params) {
        this.params = params;
    }

    public static synchronized ServerInfo getServerInfo() {
        return serverInfo;
    }

    public static void completeTask(String host, String port, int id) {
        taskList.remove(id);
        CenterCommand.completeTask(host, port, String.valueOf(id));
    }

    public void addTask(Integer id, TaskInfo taskInfo) {
        taskList.put(id, taskInfo);
        task_handle.submit(new TaskRun(params, taskInfo));
    }

    static class TaskRun implements Runnable {
        private final GlobalParams params;
        private final TaskInfo taskInfo;

        TaskRun(GlobalParams params, TaskInfo taskInfo) {
            this.params = params;
            this.taskInfo = taskInfo;
        }

        @Override
        public void run() {
            try {
                log.info("job {} {}", taskInfo.getId(), taskInfo.getI());
                Thread.sleep(taskInfo.getI());
                JobCenter.completeTask(params.getHostAddress(), params.getHostPort(), taskInfo.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

