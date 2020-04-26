package rmi.constant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Himpl extends UnicastRemoteObject implements Handler {

    public Himpl() throws RemoteException {
        super();
    }

    @Override
    public String getMsg(int id) throws RemoteException {
        return String.valueOf(id);
    }
}
