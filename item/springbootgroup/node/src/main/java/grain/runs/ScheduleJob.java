package grain.runs;

import grain.configs.GlobalParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wulifu
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduleJob {

    protected final GlobalParams params;

    public ScheduleJob(GlobalParams params) {
        this.params = params;
    }

    @Scheduled(fixedDelay = 1000 * 60)
    public void nodeInfoCommit() {
    }
}
