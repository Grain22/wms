package designpattern.delegate.query;

import designpattern.delegate.bus.service.BusinessService;
import designpattern.delegate.bus.service.EJBService;
import designpattern.delegate.bus.service.JMSService;

public class Query {
    private static final String EJB = "ejb";

    public BusinessService getService(String serType) {
        if (serType.equals(EJB)) {
            return new EJBService();
        } else {
            return new JMSService();
        }
    }
}
