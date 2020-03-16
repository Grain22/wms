package net.socket.client;

import net.socket.constants.Constants;
import net.utils.DataUtils;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * @author laowu
 * @version 5/10/2019 3:56 PM
 */
public class Client {

    private Socket socket = null;

    int port = 9999;

    String host = "127.0.0.1";

    public static void main(String[] args) {
        new Client();
    }

    private Client() {
        try {
            socket = new Socket(host, port);
            while (true) {
                byte[] bytes = new byte[20];
                for (int i = 0; i < bytes.length ; i++) {
                    bytes[i] = (byte) (new Random().nextInt());
                }
                for (byte aByte : bytes) {
                    System.out.print(aByte);
                }
                System.out.println("aaaaaaaaaaaaaaaaa");
                socket.getOutputStream().write(bytes);
                socket.getOutputStream().flush();
                byte[] read = new byte[5];
                socket.getInputStream().read(read);
                for (byte aByte : read) {
                    System.out.print(aByte);
                }
                System.out.println("");
                Thread.sleep(5000);
                //socket.close();
            }
            /*Constants.socket_client.submit(new ClientSenderForData());
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String msg1;
            while ((msg1 = br.readLine()) != null) {
                System.out.println(msg1);
            }*/
        } catch (Exception ignored) {
        }
    }

    class ClientSenderForData implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    byte[] bytes = new byte[99];
                    socket.getOutputStream().write(bytes);
                    byte[] read = new byte[10];
                    socket.getInputStream().read(read);
                    String ipId = DataUtils.getString(DataUtils.getData(bytes, 0, 3));
                    Integer messageId = DataUtils.getInt(DataUtils.getData(bytes, 3, 4));
                    String cmd = DataUtils.getString(DataUtils.getData(bytes, 7, 1));
                    String result = DataUtils.getString(DataUtils.getData(bytes, 8, 1));
                    String endFlag = DataUtils.getString(DataUtils.getData(bytes, 9, 1));
                    //socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class ClientSenderForChat implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                String msg;
                while (true) {
                    msg = re.readLine();
                    if ("".equals(msg.trim())) {
                        continue;
                    } else if (msg.equals(Constants.command + Constants.END)) {
                        break;
                    }
                    pw.println(msg);
                }
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
