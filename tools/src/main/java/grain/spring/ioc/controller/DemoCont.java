package grain.spring.ioc.controller;

import grain.spring.ioc.annotation.Component;
import grain.spring.ioc.annotation.Inject;
import grain.spring.ioc.service.DemoService;

/**
 * @author laowu
 * @version 5/7/2019 2:49 PM
 */
@Component
public class DemoCont {

    @Inject
    private DemoService demoService;

    public void get() {
        System.out.println(demoService.get());
        System.out.println(demoService.hashCode());
    }

}
