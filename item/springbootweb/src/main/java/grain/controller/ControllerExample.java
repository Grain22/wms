package grain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import grain.service.ServiceExample;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laowu
 * @version 6/5/2019 10:38 AM
 */
@RestController
@RequestMapping("test")
public class ControllerExample {
    @Autowired
    private ServiceExample serviceExample;

    @RequestMapping("getVal")
    public String getVal() {
        return serviceExample.getVal();
    }

    /*@Transactional(isolation = Isolation.READ_UNCOMMITTED)*/
    @RequestMapping("getData")
    public String getData(){
        return serviceExample.getAll().toString();
    }
}