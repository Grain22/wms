package grain.configs.resolver;

import grain.configs.GlobalYmlConfigParams;
import grain.configs.annotions.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author wulifu
 */
@Slf4j
@Configuration
public class UserInfoArgumentAutoComplete implements HandlerMethodArgumentResolver {
    private final GlobalYmlConfigParams params;

    public UserInfoArgumentAutoComplete(GlobalYmlConfigParams params) {
        this.params = params;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        String userId = webRequest.getHeader("userId");
        return Integer.valueOf(userId);
    }
}
