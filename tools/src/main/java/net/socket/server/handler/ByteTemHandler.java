package net.socket.server.handler;

import java.net.Socket;

/**
 * @author wulifu
 */
public class ByteTemHandler implements Runnable {
    Socket socket;

    public ByteTemHandler(Socket s) {
        socket = s;
    }
    @Override
    public void run() {
        try {
            while (true) {
                byte[] header = new byte[100];
                socket.getInputStream().read(header);
                for (int i = 0; i < header.length; i++) {
                    System.out.print(header[i]);
                }
                System.out.println();
                byte[] bytes1 = new byte[10];
                for (int i = 0; i < bytes1.length; i++) {
                    bytes1[i] = header[i];
                }
                socket.getOutputStream().write(bytes1);
                for (int i = 0; i < bytes1.length; i++) {
                    System.out.print(bytes1[i]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
