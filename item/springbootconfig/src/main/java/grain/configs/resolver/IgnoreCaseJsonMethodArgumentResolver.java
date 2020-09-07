package grain.configs.resolver;


import com.alibaba.fastjson.JSON;
import grain.configs.RequestInfo;
import grain.configs.exceptions.exceptions.ParamNotFormatException;
import grain.configs.validators.SpecialAvailable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Configuration
public class IgnoreCaseJsonMethodArgumentResolver implements HandlerMethodArgumentResolver {

    protected final LocalValidatorFactoryBean localValidatorFactoryBean;

    public IgnoreCaseJsonMethodArgumentResolver(LocalValidatorFactoryBean localValidatorFactoryBean) {
        this.localValidatorFactoryBean = localValidatorFactoryBean;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class) || RequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String string = IOUtils.toString(Objects.requireNonNull(webRequest.getNativeRequest(ServletRequest.class)).getInputStream(), StandardCharsets.UTF_8);
        log.info("resolve method {} for parameter {}", Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)).getMethod(), parameter.getParameterName());
        if (parameter.hasParameterAnnotation(SpecialAvailable.class)) {
            log.warn("parameter {} in method {} not remove special chars", parameter.getParameterName(), Objects.requireNonNull(parameter.getMethod()).getName());
        } else {
            string = string.replace("\n", "").replace("\r", "").replace("\t", "");
        }
        if (parameter.getParameterType().isAssignableFrom(String.class)) {
            return string;
        } else if (RequestInfo.class.isAssignableFrom(parameter.getParameterType())) {
            Object o = JSON.parseObject(string, parameter.getParameterType());
            Set<ConstraintViolation<Object>> validate = localValidatorFactoryBean.validate(o);
            if (!validate.isEmpty()) {
                validate.forEach(a -> log.debug("{} {} {}", parameter.getParameterType().getName(), a.getPropertyPath(), a.getMessage()));
                throw new ParamNotFormatException();
            }
            return o;
        }
        throw new ParamNotFormatException();
    }
}
