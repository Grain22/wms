package ioc.controller;

import ioc.annotation.Component;
import ioc.annotation.Inject;
import ioc.service.DemoService;

/**
 *  @author     laowu
 *  @version    5/7/2019 2:49 PM
 *
*/
@Component
public class DemoCont {

    @Inject
    private DemoService demoService;

    public void get(){
        System.out.println(demoService.get());
        System.out.println(demoService.hashCode());
    }

}
