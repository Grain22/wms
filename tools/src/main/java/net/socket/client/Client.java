package net.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author laowu
 * @version 5/10/2019 3:56 PM
 */
public class Client {

    public int port = 8888;
    Socket socket = null;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            socket = new Socket("127.0.0.1", port);
            new Thread(new ClientSender()).start();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            String msg1;
            while ((msg1 = br.readLine()) != null) {

                System.out.println(msg1);
            }
        } catch (Exception e) {
        }
    }

    class ClientSender implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                String msg2;
                while (true) {
                    msg2 = re.readLine();
                    pw.println(msg2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
