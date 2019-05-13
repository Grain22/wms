package tools.thread;

import java.util.concurrent.*;

/**
 * @author laowu
 * @version 5/10/2019 6:22 PM
 */
public class CustomThreadPool {


    private static boolean isDaemon = false;


    public static ExecutorService createThreadPool(String namePrefix) {
        return createThreadPool(namePrefix, isDaemon, Thread.MAX_PRIORITY);
    }

    public static ExecutorService createThreadPool(String namePrefix, boolean isDaemon, int priority) {
        ThreadFactory threadFactory = new CustomThreadFactoryBuilder()
                .setNamePrefix(namePrefix)
                .setDaemon(isDaemon)
                .setPriority(priority)
                .build();

        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return executorService;
    }

}
