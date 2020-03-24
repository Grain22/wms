package grain.runs;

import com.alibaba.fastjson.JSON;
import grain.configs.GlobalParams;
import grain.constants.Strings;
import grain.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

    @Scheduled(fixedDelay = 1000*60)
    public void nodeInfoCommit() {
    }
}
