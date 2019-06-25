package eurekaProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EntranceProvider {
    public static void main(String[] args) {
        SpringApplication.run(EntranceProvider.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/test")
    public String getPort(){
        return port;
    }
}
