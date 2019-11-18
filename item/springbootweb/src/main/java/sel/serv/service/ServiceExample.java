package sel.serv.service;


import org.springframework.stereotype.Service;
import sel.entity.BeanExample;

/**
 * @author laowu
 * @version 6/5/2019 3:17 PM
 */
@Service
public class ServiceExample {
    public String getVal(){
        BeanExample beanExample = new BeanExample().setTestBeanValue("test");
        return beanExample.getTestBeanValue();
    }
}
