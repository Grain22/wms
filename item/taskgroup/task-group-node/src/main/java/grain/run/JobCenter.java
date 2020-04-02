package grain.run;

import grain.task.Task;
import tools.bean.DateUtils;
import tools.thread.CustomThreadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author wulifu
 */
public class JobCenter {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Job> jobs = new PriorityBlockingQueue<Job>();
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(2).setTaskId(1)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(1).setTaskId(2)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(3).setTaskId(3)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(2).setTaskId(4)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(3).setTaskId(5)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(1).setTaskId(6)));
        jobs.add(new Job(new Task().setAddedDate(DateUtils.getTimeStringLong()).setPriority(2).setTaskId(7)));
        Job take = jobs.take();
        Job take1 = jobs.take();
        Job take2 = jobs.take();
        Job take3 = jobs.take();
        Job take4 = jobs.take();
        Job take5 = jobs.take();
        Job take6 = jobs.take();
    }

    protected static ThreadPoolExecutor task_pool = null;
    private static Map<Integer, Future> task_future = new HashMap<>();

    public static void init() {
        task_pool = new ThreadPoolExecutor(
                4,
                8,
                0,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(10240),
                CustomThreadPool.createThreadFactory("task pool", false, Thread.NORM_PRIORITY),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static Executor getExecutor() {
        if (Objects.isNull(task_pool)) {
            init();
        }
        return task_pool;
    }

    public static boolean addTask(Task task) {
        BlockingQueue<Runnable> queue = task_pool.getQueue();
        queue.forEach(a -> {
            ((Job) a).increasePriority();
        });
        task_pool.submit(new Job(task));
        return true;
    }

}
