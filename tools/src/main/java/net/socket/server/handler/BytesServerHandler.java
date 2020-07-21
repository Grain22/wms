package net.socket.server.handler;

import tools.data.ByteUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author laowu
 */
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
                System.out.println(new String(body));
                byte[] bytes = "{\"returnCode\":0}".getBytes();
                byte[] bytes1 = new byte[10 + bytes.length];
                for (int i1 = 0; i1 < ByteUtils.intToBytes(bytes.length + 10).length; i1++) {
                    bytes1[i1] = ByteUtils.intToBytes(bytes.length + 10)[i1];
                }
                for (int a = 0; a < bytes.length; a++) {
                    bytes1[a + 10] = bytes[a];
                }
                socket.getOutputStream().write(bytes1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
