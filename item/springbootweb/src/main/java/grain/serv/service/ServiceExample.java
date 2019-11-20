package grain.serv.service;


import grain.dao.UserInfoMapper;
import grain.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import grain.entity.BeanExample;


/**
 * @author laowu
 * @version 6/5/2019 3:17 PM
 */
@Service
public class ServiceExample {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public String getVal(){
        BeanExample beanExample = new BeanExample().setTestBeanValue("test");
        return beanExample.getTestBeanValue();
    }

    public Iterable<UserInfo> getAll(){
        return userInfoMapper.findAll();
    }

}
