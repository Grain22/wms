package net.socket.server.handler;

import net.socket.constants.Msg;

import java.net.Socket;

/**
 * @author laowu
 */
public class BytesServerHandler implements Runnable {
    Socket socket;
    Msg receive = new Msg();

    public BytesServerHandler(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] bytes = new byte[99];
                socket.getInputStream().read(bytes);
                receive.setData(0, 99, bytes);
                socket.getOutputStream().write(receive.getData(0, 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
