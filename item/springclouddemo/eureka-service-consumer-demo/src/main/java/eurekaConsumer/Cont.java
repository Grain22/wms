package eurekaConsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Cont {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    @HystrixCommand(fallbackMethod = "errorHandler")
    public String test(){
        String url = "http://provider/test";
        return restTemplate.getForObject(url, String.class);
    }

    @RequestMapping("/testError")
    @HystrixCommand(fallbackMethod = "errorHandler")
    public String testError(){
        String url = "http://provider/test1";
        return restTemplate.getForObject(url, String.class);
    }

    public String errorHandler(){
        return "something wrong";
    }
}
