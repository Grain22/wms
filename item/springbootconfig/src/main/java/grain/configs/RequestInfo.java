package grain.configs;

/**
 * @author wulifu
 * 使用该接口实现的相关方法若不不使用 {@code @RequestBody} 或者 {@code @RestController} 等
 * spring框架等级返回值handler 时
 * 会自动介入返回值调整方法
 * @see grain.configs.resolver.ResultInfoHandlerMethodReturnValueHandler
 */
public interface RequestInfo {
}
