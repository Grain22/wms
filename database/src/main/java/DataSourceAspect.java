import annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author laowu
 * @version 4/15/2019 5:48 PM
 */
public class DataSourceAspect {
    public void before(JoinPoint point) {
        // 实体类
        Object target = point.getTarget();
        // 方法名
        String method = point.getSignature().getName();
        // 方法参数
        Object[] args = point.getArgs();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
                System.out.println(data.value());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
