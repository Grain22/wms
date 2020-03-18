package net.socket.server.handler;

import net.socket.constants.Msg;

import java.io.IOException;
import java.io.InputStream;
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
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[20];
                inputStream.read(bytes);
                receive.setData(0, 20, bytes);
                socket.getOutputStream().write(receive.getData(0, 5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
