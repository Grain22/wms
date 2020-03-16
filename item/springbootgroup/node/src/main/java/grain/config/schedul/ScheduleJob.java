package grain.config.schedul;

import org.springframework.scheduling.annotation.Scheduled;
import spring.ioc.annotation.Component;

@Component
public class ScheduleJob {

    @Scheduled(fixedDelay = 1000 * 60 * 30)
    public void nodeInfoCommit() {

    }
}
