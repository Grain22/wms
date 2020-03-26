package distributed.task.framework.job;

/**
 * @author wulifu
 * 工作类,继承
 */
public interface Job extends Runnable{
    @Override
    void run();
}
