package grain.configs.exceptions;

/**
 * @author laowu
 */
public class RequestLimitException extends Exception {

    public RequestLimitException() {
        super("请求次数超过规定限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
