package net.socket.client;

import net.socket.client.handler.ChatHandler;
import net.socket.constants.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            socket = new Socket(Constants.host, Constants.port);
            new ChatHandler(socket).run();
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
}
