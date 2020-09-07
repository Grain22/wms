package grain.configs.exceptions.exceptions;


import grain.configs.ResultInfo;
import grain.configs.exceptions.AbstractCustomerExceptions;

public class ParamNotFormatException extends AbstractCustomerExceptions {

    public ParamNotFormatException() {
        super(ResultInfo.UNKNOWN);
    }
}
