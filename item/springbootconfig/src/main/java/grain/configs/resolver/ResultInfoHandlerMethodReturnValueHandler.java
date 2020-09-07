package grain.configs.resolver;


import grain.configs.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
@Configuration
public class ResultInfoHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter parameter) {
        return ResultInfo.class.isAssignableFrom(parameter.getParameterType()) || Objects.requireNonNull(parameter.getMethod()).isAnnotationPresent(ToResultString.class);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        try {
            log.debug("resolve method {} for parameter {}", Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)).getMethod(), returnType.getParameterName());
            if (ResultInfo.class.isAssignableFrom(returnType.getParameterType())) {
                log.debug("resolve by interface ResultInfo");
                assert returnValue != null;
                writer.write(((ResultInfo) returnValue).json());
            } else if (returnType.hasParameterAnnotation(ToResultString.class)) {
                log.debug("resolve by annotation @ToJson");
                ToResultString parameterAnnotation = returnType.getParameterAnnotation(ToResultString.class);
                try {
                    Objects.requireNonNull(parameterAnnotation);
                    ToResultStringInterface toResultStringInterface = parameterAnnotation.clazz().newInstance();
                    writer.write(toResultStringInterface.toJsonString(returnValue));
                } catch (Exception e) {
                    throw new RuntimeException("定义返回方式并不能满足要求");
                }
            } else {
                log.warn("un catch return value type");
            }
        } catch (Exception e) {
            writer.write(ResultInfo.system(e.getMessage()));
        }
        writer.flush();
    }
}
