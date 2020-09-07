package eurekaService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wulifu
 */
@EnableEurekaServer
@SpringBootApplication
public class EntranceService {
    public static void main(String[] args) {
        SpringApplication.run(EntranceService.class, args);
    }
}
