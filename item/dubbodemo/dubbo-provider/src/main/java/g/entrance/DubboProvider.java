package g.entrance;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DubboProvider {
    public static void main(String[] args) {
        SpringApplication.run(DubboProvider.class, args);
    }
}
