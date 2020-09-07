package grain.configs.handler;


import com.alibaba.fastjson.JSONException;
import grain.configs.ResultInfo;
import grain.configs.exceptions.AbstractCustomerExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author wulifu
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        String s;
        if (e instanceof AbstractCustomerExceptions) {
            s = ((AbstractCustomerExceptions) e).getReturn();
        } else if (e instanceof NullPointerException) {
            s = ResultInfo.system(e.getMessage());
        } else if (e instanceof JSONException) {
            s = ResultInfo.system(e.getMessage());
        } else if (e instanceof SQLException) {
            s = ResultInfo.system(e.getMessage());
        } else {
            s = ResultInfo.system(e.getMessage());
        }
        log.warn("exception catch {} return {}", e.getMessage(), s);
        return s;
    }
}
