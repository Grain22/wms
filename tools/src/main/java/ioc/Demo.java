package ioc;

import ioc.context.IocContext;
import ioc.controller.DemoCont;

/**
 *  @author     laowu
 *  @version    5/7/2019 6:07 PM
 *
*/
public class Demo {

    public static void main(String[] args) {
        DemoCont demoCont = (DemoCont) IocContext.APPLICATION_CONTEXT.get(DemoCont.class);
        demoCont.get();
    }

}
