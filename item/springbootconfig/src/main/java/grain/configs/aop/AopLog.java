package grain.configs.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tools.net.AddressUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wulifu
 */
@Aspect
@Component
@Slf4j
public class AopLog {

    @Pointcut("execution(* grain..controller..*(..))")
    public void aopCut() {
    }

    @Around("aopCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        boolean throwError = false;
        String name = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        log.info("URL           {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD   {}", request.getMethod());
        log.info("IP            {}", AddressUtils.getIPAddress(request));
        log.info("target        {}", name);
        log.info("method        {}", methodName);
        log.info("args          {}", args);
        Object result = null;
        try {
            result = jp.proceed();
            return result;
        } catch (Throwable e) {
            throwError = true;
            log.info(e.getMessage());
            return "error";
        } finally {
            log.info("{} {}", throwError ? "fail" : "success", result);
        }
    }

}
