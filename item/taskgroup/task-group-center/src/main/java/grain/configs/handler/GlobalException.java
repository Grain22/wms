package grain.configs.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wulifu
 */
@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ResponseStatus
    @ExceptionHandler
    public String exceptionHandler(Exception e) {
        return "error";
    }
}
