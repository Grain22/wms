package org.grain.net.socket.constants;

import org.grain.tools.thread.CustomThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laowu
 */
public class Constants {
    public static final String END = "end";
    public static int POINT = 10000;
    public static String command = "///";
    public static int port = 9999;
    public static String host = "127.0.0.1";
    public static ThreadPoolExecutor clients;

    static {
        clients = CustomThreadPool.createThreadPool("socket_clients", 32, 32, false, 3000);
        Runnable runnable = new Runnable() {
            public final ThreadPoolExecutor threadPoolExecutor = clients;

            @Override
            public void run() {
                try {
                    if (Thread.interrupted()) {
                        return;
                    }
                    Thread.sleep(TimeUnit.DAYS.toMillis(1));
                    threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
                } catch (Exception ignore) {
                    System.out.println("dynamic update threadPoolExecutor corePoolSize failed");
                }
            }
        };
        clients.execute(runnable);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            clients.remove(runnable);
            clients.shutdown();
        }));
    }
}
