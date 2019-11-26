package grain.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import spring.ioc.annotation.Component;

import java.util.Arrays;

/**
 * @author grain
 */
@Aspect
@Component
@Log4j2
public class AspectExample {
    @Pointcut("execution(public * grain.controller.*.*(..))")
    public void pointcutExample() {
    }

    @Around("pointcutExample()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {

        //和JoinPoint一样，ProceedingJoinPoint也可以获取
        //连接点方法的实参
        Object[] args = proceedingJoinPoint.getArgs();
        //连接点方法的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //连接点方法所在的对象
        Object targetObj = proceedingJoinPoint.getTarget();
        String targetClassName = targetObj.getClass().getName();

        System.out.println(Arrays.toString(args));

        Object proceed = null;

        long start = System.currentTimeMillis();

        try {
            // before
            proceed = proceedingJoinPoint.proceed();
            // after
        } catch (Throwable throwable) {
            // throw exception
            throwable.printStackTrace();
        } finally {
            // finally
        }
        start -= System.currentTimeMillis();
        System.out.println(start);
        return proceed;
    }

}
