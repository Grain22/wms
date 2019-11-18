package sel.serv.service;


import org.springframework.stereotype.Service;
import sel.entity.TB;

/**
 * @author laowu
 * @version 6/5/2019 3:17 PM
 */
@Service
public class ServiceExample {
    public String getVal(){
        TB tb = new TB().setTestBeanValue("test");
        return tb.getTestBeanValue();
    }
}
