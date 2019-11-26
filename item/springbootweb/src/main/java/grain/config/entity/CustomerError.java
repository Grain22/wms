package grain.config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author grain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EntityScan
public class CustomerError extends Error {

    private Long code;
    private String message;
    private String url;
    private String serverName;

}
