package grain.runs;

import grain.bean.ServerInfo;
import grain.constants.TaskInfo;
import tools.thread.CustomThreadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wulifu
 */
public class JobCenter {
    private static ThreadPoolExecutor task_handle = CustomThreadPool.createThreadPool("task handle");
    private static Map<Integer, Object> taskList = new ConcurrentHashMap<>();
    private static ServerInfo serverInfo = new ServerInfo();

    public static synchronized ServerInfo getServerInfo() {
        return serverInfo;
    }

    public static void addTask(Integer id, TaskInfo taskInfo) {
        taskList.put(id, taskInfo);
        //serverInfo.getWaitForFileTransfer().put()
    }

}
