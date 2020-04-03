package grain.configs.auto;

import grain.configs.GlobalParams;
import grain.constants.Strings;
import grain.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wulifu
 */
@Slf4j
@Component
public class AutoRun {

    protected final GlobalParams params;

    public AutoRun(GlobalParams params) {
        this.params = params;
    }

    @PostConstruct
    public void run() {
        String s = RequestUtils.sendPost(RequestUtils.getUrl(params.getHostAddress(), params.getHostPort(), "/api/" + Strings.center + "/" + Strings.REGISTER), null);
        log.info("节点注册 {}", s);
    }
}
