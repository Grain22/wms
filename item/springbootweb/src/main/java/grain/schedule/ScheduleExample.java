package grain.schedule;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author laowu
 * schedule tasks example
 */
@Component
public class ScheduleExample {

    @Scheduled(cron = "0 */1 * * * *")
    public void excuteExample(){
        System.out.println("spring boot schedule example is on");
    }
}
