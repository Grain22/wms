package org.grain.tools.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Grain
 */
public class CustomThreadFactoryBuilder {
    private String namePrefix = null;
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;

    private static ThreadFactory build(CustomThreadFactoryBuilder builder) {
        final String namePrefix = builder.namePrefix;
        final Boolean daemon = builder.daemon;
        final int priority = builder.priority;
        final AtomicLong count = new AtomicLong(0);
        //jdk8中还是优先使用lamb表达式
        return (Runnable runnable) -> {
            Thread thread = new Thread(runnable);
            if (namePrefix != null) {
                thread.setName(namePrefix + "-" + count.getAndIncrement());
            }
            if (daemon != null) {
                thread.setDaemon(daemon);
            }
            thread.setPriority(priority);
            return thread;
        };
    }

    public CustomThreadFactoryBuilder setNamePrefix(String namePrefix) {
        if (namePrefix == null) {
            throw new NullPointerException();
        }
        this.namePrefix = namePrefix;
        return this;
    }

    public CustomThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public CustomThreadFactoryBuilder setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY));
        }

        this.priority = priority;
        return this;
    }

    public ThreadFactory build() {
        return build(this);
    }
}

