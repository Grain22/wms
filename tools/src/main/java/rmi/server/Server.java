package rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static int port = 8888;
    public static void main(String[] args) {
        try {
            Handler handler = new Himpl();

            LocateRegistry.createRegistry(port);
            Naming.bind("rmi://localhost:"+port+"/handler",handler);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
