package grain.configs.auto;

import grain.Msg;
import grain.command.Command;
import grain.configs.GlobalParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wulifu
 */
@Slf4j
@Component
public class AutoRun {

    protected final GlobalParams params;

    @Value("${server.port:8080}")
    private String port;

    public AutoRun(GlobalParams params) {
        this.params = params;
    }

    @PostConstruct
    public void run() {
        try {
            String register = Command.register(params.getHostAddress(), params.getHostPort(), port);
            params.setNodeId((String) Msg.parse(register).getData());
            log.info("节点注册 {}", register);
        } catch (Exception e) {
            log.info("主服务器未找到");
            throw new RuntimeException("主服务器未找到");
        }
    }
}
