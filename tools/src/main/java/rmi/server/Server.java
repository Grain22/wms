package rmi.server;

import rmi.constant.Constants;
import rmi.constant.Handler;
import rmi.constant.Himpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            Handler handler = new Himpl();
            LocateRegistry.createRegistry(Constants.port);
            Naming.bind("rmi://localhost:"+Constants.port+"/handler",handler);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
