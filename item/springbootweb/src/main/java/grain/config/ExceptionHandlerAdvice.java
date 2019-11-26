package grain.config;

import grain.config.entity.ApiException;
import grain.config.entity.CustomerError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理（转换）
 *
 * @author wangcong
 * @version 1.0.0 2018/4/11
 */
@Log4j2
//@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @Value("${spring.application.name}")
    private String serverName;

    /**
     * 异常转换
     *
     * @param exception
     * @param response
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Error> exception(Exception exception, HttpServletResponse response,
                                           HttpServletRequest request) {
        CustomerError error = new CustomerError();
        error.setServerName(serverName);

        if (exception instanceof ApiException) {
            // api 异常
            ApiException apiException = (ApiException) exception;
            error.setCode(apiException.getCode());
            error.setMessage(exception.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            // controller method argument validation error
            BindingResult result = ((MethodArgumentNotValidException) exception).getBindingResult();

            final String messages = result.getAllErrors().stream()
                    .map(err -> err == null ? "" : err.getDefaultMessage()).collect(Collectors.joining(","));

            error.setCode(400L);
            error.setMessage(messages);
        } else if (exception instanceof ConstraintViolationException) {
            // service方法验证异常
            ConstraintViolationException ce = (ConstraintViolationException) exception;

            final String messages = ce.getConstraintViolations().stream().map(cv -> cv == null ? "" : cv.getMessage())
                    .collect(Collectors.joining(","));

            error.setCode(400L);
            error.setMessage(messages);
        } else {
            // 其他异常
            log.warn("发生未知错误", exception);
            error.setCode(-1L);
            error.setMessage(exception.getMessage());
        }

        error.setMessage(String.format("%s [code:%d]", error.getMessage(), error.getCode()));
        error.setUrl(request.getRequestURL().toString());
        return new ResponseEntity<>(error, HttpStatus.valueOf(response.getStatus()));
    }
}
