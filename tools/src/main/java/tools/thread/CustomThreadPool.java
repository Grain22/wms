package tools.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laowu
 * @version 5/10/2019 6:22 PM
 */
public class CustomThreadPool {


    public static boolean is_daemon_false = false;
    public static int maximum_pool_size = 20;
    public static int core_pool_size = 5;
    public static long keep_alive_time = 0L;


    public static ThreadPoolExecutor createThreadPool(String namePrefix) {
        return createThreadPool(namePrefix, is_daemon_false, Thread.NORM_PRIORITY);
    }

    public static ThreadPoolExecutor createThreadPool(String namePrefix, int core, int max) {
        return createThreadPool(namePrefix,
                is_daemon_false,
                Thread.NORM_PRIORITY,
                core,
                max,
                keep_alive_time);
    }

    public static ThreadPoolExecutor createThreadPool(String namePrefix, boolean isDaemon, int priority) {
        return createThreadPool(namePrefix,
                isDaemon,
                priority,
                core_pool_size,
                maximum_pool_size,
                keep_alive_time);
    }

    public static ThreadPoolExecutor createThreadPool(String namePrefix, boolean isDaemon, int priority, int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        ThreadFactory threadFactory = new CustomThreadFactoryBuilder()
                .setNamePrefix(namePrefix)
                .setDaemon(isDaemon)
                .setPriority(priority)
                .build();
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(102400),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
