package grain.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wulifu
 */
@Component
@ConfigurationProperties(prefix = "file")
@Data
public class GlobalYmlConfigParams {
    private String staticResource;
}
