package grain.designpattern.delegate.query;

import grain.designpattern.delegate.bus.service.BusinessService;
import grain.designpattern.delegate.bus.service.EJBServiceImpl;
import grain.designpattern.delegate.bus.service.JMSServiceImpl;

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
