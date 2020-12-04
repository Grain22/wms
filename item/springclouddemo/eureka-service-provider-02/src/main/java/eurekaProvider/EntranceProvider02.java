package eurekaProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class EntranceProvider02 {
    @Value("${server.port}")
    String port;

    public static void main(String[] args) {
        SpringApplication.run(EntranceProvider02.class, args);
    }

    @RequestMapping("/test")
    public String getPort() {
        return port;
    }
}

