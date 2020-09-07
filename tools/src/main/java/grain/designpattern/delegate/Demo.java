package grain.designpattern.delegate;

import grain.designpattern.delegate.bus.delegate.Delegate;
import grain.designpattern.delegate.client.Client;

public class Demo {
    public static void run(String[] args) {
        Delegate delegate = new Delegate();
        delegate.setServiceType("ejb");

        Client client = new Client(delegate);
        client.doTask();

        delegate.setServiceType("fdas");
        client.doTask();
    }
}
