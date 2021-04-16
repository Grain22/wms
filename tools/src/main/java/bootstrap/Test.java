package bootstrap;

import ch.qos.logback.classic.util.ContextInitializer;
import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.ftp.pool.sftp.PooledSftpChannelBuilder;
import tools.thread.CustomThreadPool;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
    static {
        System.out.println(System.getProperty("user.dir") + File.separator + "logback.xml");
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, System.getProperty("user.dir") + File.separator + "logback.xml");
    }

    static Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor writeLog = CustomThreadPool.createThreadPool("write log", 64, 64, true, 3000);
        GenericObjectPool<ChannelSftp> build = PooledSftpChannelBuilder.build();
        for (int i = 0; i < Long.SIZE; i++) {
            writeLog.submit(() -> {
                try {
                    log.info("borrow");
                    ChannelSftp channelSftp = build.borrowObject();
                    int i1 = new Random().nextInt(10000);
                    log.info("sleep "+i1);
                    Thread.sleep(i1);
                    build.returnObject(channelSftp);
                    log.info("return");
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
            });
        }
        Thread.sleep(30000);
        System.out.println("return");
    }
}
