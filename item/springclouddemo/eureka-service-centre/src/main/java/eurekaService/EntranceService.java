package eurekaService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EntranceService {
    public static void run(String[] args) {
        SpringApplication.run(EntranceService.class, args);
    }
}
