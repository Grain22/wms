package rmi.constant;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author laowu
 * @version demo
 * @date 2019/6/21 18:31
 */
public interface Handler extends Remote {
    /**
     * rmi 测试
     *
     * @param id id
     * @return string
     * @throws RemoteException
     */
    String getMsg(int id) throws RemoteException;
}
