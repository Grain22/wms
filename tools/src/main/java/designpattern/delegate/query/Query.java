package designpattern.delegate.query;

import designpattern.delegate.bus.service.BusinessService;
import designpattern.delegate.bus.service.EJBServiceImpl;
import designpattern.delegate.bus.service.JMSServiceImpl;

public class Query {
    private static final String EJB = "ejb";

    public BusinessService getService(String serType) {
        if (serType.equals(EJB)) {
            return new EJBServiceImpl();
        } else {
            return new JMSServiceImpl();
        }
    }
}
