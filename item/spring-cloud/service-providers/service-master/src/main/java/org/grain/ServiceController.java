package org.grain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.POST;

@Controller
public class ServiceController {
    @Value("${server.port}")
    Integer port;
    @ResponseBody
    @PutMapping("/getId")
    public Integer getServicePort(){
        return port;
    }

}
