package sel.cont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sel.serv.service.Service;

/**
 * @author laowu
 * @version 6/5/2019 10:38 AM
 */
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Service service;

    @ResponseBody
    @RequestMapping("/test/getVal")
    public String getVal() {
        return service.getVal();
    }
}