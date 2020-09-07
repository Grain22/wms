package grain.configs.resolver;

import java.lang.annotation.*;

/**
 * 自动bean to jsonString 参数转换
 * 需要转换方法实现 ToResultStringInterface
 * @see ToResultStringInterface
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToResultString {
      /**
     * 特定json转换方法
     * @return 类名
     */
    Class<? extends ToResultStringInterface> clazz();
}
