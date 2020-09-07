package grain.net.socket.server;

import grain.net.socket.constants.Constants;
import grain.net.socket.server.handler.ByteTemHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laowu
 * @version 5/10/2019 11:12 AM
 */
public class Server {

    public static List<Socket> clients = new ArrayList<>();
    ServerSocket server;


    public Server() {
        try {
            init();
            listen();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void run(String[] args) {
        new Server();
    }

    private void init() throws IOException {
        server = new ServerSocket(9002);
//        server.setSoTimeout(1000);
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = server.accept();
//            socket.setSoTimeout(1000);
            clients.add(socket);
            //Constants.clients.submit(new ChatServerHandler(socket));
            Constants.clients.submit(new ByteTemHandler(socket));
        }
    }

}