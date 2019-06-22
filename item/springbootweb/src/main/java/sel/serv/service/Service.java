package sel.serv.service;


import sel.entity.TB;

/**
 * @author laowu
 * @version 6/5/2019 3:17 PM
 */
@org.springframework.stereotype.Service
public class Service {
    public String getVal(){
        TB tb = new TB().setTestBeanValue("test");
        return tb.getTestBeanValue();
    }
}
