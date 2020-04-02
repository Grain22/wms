package grain.configs.schedule;

import grain.configs.GlobalYmlConfigParams;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import tools.thread.CustomThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wulifu
 */
public class ScheduleConfig implements SchedulingConfigurer {

    protected final GlobalYmlConfigParams params;

    public ScheduleConfig(GlobalYmlConfigParams params) {
        this.params = params;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    }
}
