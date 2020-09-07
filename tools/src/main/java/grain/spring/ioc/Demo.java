package grain.spring.ioc;

import grain.spring.ioc.context.IocContext;
import grain.spring.ioc.controller.DemoCont;

/**
 * @author laowu
 * @version 5/7/2019 6:07 PM
 */
public class Demo {

    public static void run(String[] args) {
        DemoCont demoCont = (DemoCont) IocContext.APPLICATION_CONTEXT.get(DemoCont.class);
        demoCont.get();
    }

}
