package grain.cont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import grain.serv.service.ServiceExample;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laowu
 * @version 6/5/2019 10:38 AM
 */
@RestController
@RequestMapping("test")
public class ControllerExample {
    /*@Controller need @ResponseBody*/
    /*@RestController do not need @ResponseBody*/

    @Autowired
    private ServiceExample serviceExample;

    @RequestMapping("getVal")
    public String getVal() {
        return serviceExample.getVal();
    }

    @RequestMapping("getData")
    public String getData(){
        return serviceExample.getAll().toString();
    }
}