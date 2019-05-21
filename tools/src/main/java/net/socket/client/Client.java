package net.socket.client;

import net.socket.constants.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author laowu
 * @version 5/10/2019 3:56 PM
 */
public class Client {

    private Socket socket = null;

    public static void main(String[] args) {
        new Client();
    }

    private Client() {
        try {
            int port = 8888;
            socket = new Socket("127.0.0.1", port);
            Constants.socket_client.submit(new ClientSender());
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String msg1;
            while ((msg1 = br.readLine()) != null) {
                System.out.println(msg1);
            }
        } catch (Exception ignored) {
        }
    }

    class ClientSender implements Runnable {
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
