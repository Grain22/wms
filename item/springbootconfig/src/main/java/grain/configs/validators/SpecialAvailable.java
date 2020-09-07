package grain.configs.validators;

import java.lang.annotation.*;

/**
 * 当前注解所标注的参数不进行特殊字符过滤
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpecialAvailable {
}
