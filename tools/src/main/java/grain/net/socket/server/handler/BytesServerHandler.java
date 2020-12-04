package grain.net.socket.server.handler;

import lombok.extern.slf4j.Slf4j;
import tools.data.ByteUtils;

import java.net.Socket;

/**
 * @author laowu
 */
@Slf4j
public class BytesServerHandler implements Runnable {
    Socket socket;

    public BytesServerHandler(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] header = new byte[10];
                socket.getInputStream().read(header);
                byte[] data = ByteUtils.getData(header, 0, 4);
                int i = ByteUtils.bytesToInt(data);
                System.out.println("header length " + i + " " + data[2] + " " + data[3]);
                byte[] body = new byte[i - 10];
                socket.getInputStream().read(body);
                log.info(new String(body));
                byte[] bytes = "{\"returnCode\":0}".getBytes();
                byte[] bytes1 = new byte[10 + bytes.length];
                byte[] bytes2 = ByteUtils.intToBytes(bytes.length + 10);
                System.arraycopy(bytes2, 0, bytes1, 0, bytes2.length);
                System.arraycopy(bytes, 0, bytes1, 10, bytes.length);
                socket.getOutputStream().write(bytes1);
                Thread.yield();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
