package g.serv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import g.serv.DemoService;
import spring.ioc.annotation.Component;

/**
 * @author laowu
 * @version demo
 * 2019/7/1 14:13
 */
@Component
@Service(timeout = 5000)
public class DemoServiceImpl implements DemoService {

    @Override
    public String getValue(){
        return "success";
    }
}
