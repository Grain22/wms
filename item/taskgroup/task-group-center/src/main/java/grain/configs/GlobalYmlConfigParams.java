package grain.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wulifu
 */
@Component
@ConfigurationProperties(prefix = "self.properties")
@Data
public class GlobalYmlConfigParams {

    private String tem;
    private String rootPath;

    private String nodePort;

}
