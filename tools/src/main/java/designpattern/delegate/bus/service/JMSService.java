package designpattern.delegate.bus.service;

/**
 *  @author     laowu
 *  @version    5/6/2019 2:05 PM
 *
*/
public class JMSService implements BusinessService {
    @Override
    public void doProcessing() {
        System.out.println("jms");
    }
}
