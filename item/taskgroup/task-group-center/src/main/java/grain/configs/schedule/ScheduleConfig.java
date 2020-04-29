package grain.configs.schedule;

import grain.configs.GlobalYmlConfigParams;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

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
