package grain.configs.annotions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     * 最大访问次数
     *
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 访问周期
     *
     * @return
     */
    long time() default 60000 * 24;
}
