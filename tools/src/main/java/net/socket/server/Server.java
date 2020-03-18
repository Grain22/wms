package net.socket.server;

import net.socket.constants.Constants;
import net.socket.server.handler.ChatServerHandler;
import tools.thread.CustomThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author laowu
 * @version 5/10/2019 11:12 AM
 */
public class Server {

    ServerSocket server;
    public static List<Socket> clients;


    public static void main(String[] args) {
        new Server();
    }

    private void init() throws IOException {
        server = new ServerSocket(Constants.port);
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = server.accept();
            clients.add(socket);
            Constants.clients.submit(new ChatServerHandler(socket));
        }
    }

    public Server() {
        try {
            init();
            listen();
        } catch (Exception ex) {
        }
    }

}