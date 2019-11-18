package sel.cont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sel.serv.service.ServiceExample;

/**
 * @author laowu
 * @version 6/5/2019 10:38 AM
 */
@Controller
public class ControllerExample {
    @Autowired
    private ServiceExample serviceExample;

    @ResponseBody
    @RequestMapping("/test/getVal")
    public String getVal() {
        return serviceExample.getVal();
    }
}