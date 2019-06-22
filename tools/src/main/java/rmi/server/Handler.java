package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author laowu
 * @version demo
 * @date 2019/6/21 18:31
 */
public interface Handler extends Remote {
    String getMsg(int id) throws RemoteException;
}
