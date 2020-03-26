package rmi.client;

import rmi.constant.Constants;
import rmi.constant.Handler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author laowu
 * @date 2019/6/21 18:48
 */
public class Client {
    public static void main(String[] args) {
        try {
            Handler handler = (Handler) Naming.lookup("rmi://localhost:" + Constants.port + "/handler");
            System.out.println(handler.getMsg(2));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
