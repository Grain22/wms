package net.socket.server1.handler;

import net.socket.constants.Msg;

import java.io.IOException;
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
                for (int i = 0; i < bytes.length; i++) {
                    System.out.print(bytes[i]);
                }
                receive.setData(0, 99, bytes);
                byte[] data = receive.getData(0, 10);
                System.out.println("");
                for (int i = 0; i < data.length; i++) {
                    System.out.print(data[i]);
                }
                System.out.println("");
                socket.getOutputStream().write(data);
                socket.getOutputStream().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
