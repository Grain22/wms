package grain.net.socket.server.handler;

import java.net.Socket;
import java.util.Random;

/**
 * @author wulifu
 */
public class ByteTemHandler implements Runnable {
    Socket socket;
    int readLength;

    public ByteTemHandler(Socket s,int readLength) {
        socket = s;
        this.readLength = readLength;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] header = new byte[readLength];
                socket.getInputStream().read(header);
                for (int i = 0; i < header.length; i++) {
                    System.out.print(header[i]);
                }
                System.out.println();
                byte[] bytes1 = new byte[10];
                System.arraycopy(header, 0, bytes1, 0, bytes1.length);
                bytes1[8] = 48;
                socket.getOutputStream().write(bytes1);
            }
        } catch (Exception e) {
            System.out.println(socket.getLocalPort()+""+e.getMessage());
        }
    }
}
