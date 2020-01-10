package tools.thread;

import java.util.concurrent.*;

/**
 * @author laowu
 * @version 5/10/2019 6:22 PM
 */
public class CustomThreadPool {


    public static boolean is_daemon = false;
    public static int maximum_pool_size = 20;
    public static int core_pool_size = 5;
    public static long keep_alive_time = 0L;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    public static ExecutorService createThreadPool(String namePrefix) {
        return createThreadPool(namePrefix, is_daemon, Thread.NORM_PRIORITY);
    }

    public static ExecutorService createThreadPool(String namePrefix, boolean isDaemon, int priority) {
        ThreadFactory threadFactory = new CustomThreadFactoryBuilder()
                .setNamePrefix(namePrefix)
                .setDaemon(isDaemon)
                .setPriority(priority)
                .build();

        return new ThreadPoolExecutor(
                core_pool_size,
                maximum_pool_size,
                keep_alive_time,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

}
