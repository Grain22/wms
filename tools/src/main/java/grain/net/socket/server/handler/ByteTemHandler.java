package grain.net.socket.server.handler;

import lombok.extern.slf4j.Slf4j;

import java.net.Socket;
import java.util.Random;

/**
 * @author wulifu
 */
@Slf4j
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
                StringBuilder sb = new StringBuilder("");
                for (byte b : header) {
                    sb.append(b).append(" ");
                }
                log.info(sb.toString());
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
