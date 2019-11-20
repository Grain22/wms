package grain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author laowu
 * @version 6/5/2019 11:15 AM
 */
@Configuration
@EnableJpaRepositories(basePackages = {"grain.dao"})
@EntityScan(basePackages = {"grain.entity"})
@EnableScheduling
@SpringBootApplication
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
