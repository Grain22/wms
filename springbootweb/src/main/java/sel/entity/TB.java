package sel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author laowu
 * @version 6/5/2019 3:04 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EntityScan
public class TB {
    private String testBeanValue;
}
