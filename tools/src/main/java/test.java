import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
@Log4j2
public class test {
    public static void main(String[] args) {
        log.log(Level.TRACE,"fdas");
        log.log(Level.DEBUG,"fdsa");
    }
}
