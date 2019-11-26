package grain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author laowu
 * @version 6/5/2019 11:15 AM
 */
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
