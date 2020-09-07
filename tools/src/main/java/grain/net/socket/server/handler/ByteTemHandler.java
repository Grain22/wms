package grain.net.socket.server.handler;

import java.net.Socket;
import java.util.Random;

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
            Random random = new Random();
            while (true) {
                byte[] header = new byte[100];
                socket.getInputStream().read(header);
                for (int i = 0; i < header.length; i++) {
                    System.out.print(header[i]);
                }
                System.out.println();

                int i = random.nextInt(10);
                System.out.print("int " + i+" ");
                if (i == 1) {
                    System.out.println("close write");
                    socket.getOutputStream().close();
                } else if (i == 3) {
                    System.out.println("close read");
                    socket.getInputStream().close();
                } else if (i == 5) {
                    System.out.println("system wait");
                    Thread.sleep(1000);
                } else if (i == 7) {
                    System.out.println("close socket");
                    socket.close();
                }
                byte[] bytes1 = new byte[10];
                for (int n = 0; n < bytes1.length; n++) {
                    bytes1[n] = header[n];
                }
                bytes1[8] = 48;
                socket.getOutputStream().write(bytes1);
                for (int n = 0; n < bytes1.length; n++) {
                    System.out.print(bytes1[n]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
