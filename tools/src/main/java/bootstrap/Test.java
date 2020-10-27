package bootstrap;

import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.thread.CustomThreadPool;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
    static {
        System.out.println(System.getProperty("user.dir") + File.separator + "logback.xml");
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, System.getProperty("user.dir") + File.separator + "logback.xml");
    }

    static Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor write_log = CustomThreadPool.createThreadPool("write log",64,64,true,3000);
        for (int i = 0; i < Long.SIZE; i++) {
            write_log.submit(() -> {
                while (true) {
                    log.info("write by {}", Thread.currentThread().getName());
                }
            });
        }
        Thread.sleep(30000);
        System.out.println("return");
    }
}
