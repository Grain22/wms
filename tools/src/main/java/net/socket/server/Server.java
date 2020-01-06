package net.socket.server;

import tools.thread.CustomThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    /**
     * 5/13/2019 端口
     */
    int port;
    /**
     * 5/13/2019 clients
     */
    List<Socket> clients;
    /**
     * 5/13/2019 server
     */
    ServerSocket server;
    /**
     * 5/13/2019 clients thread pool
     */
    ExecutorService socketTest = CustomThreadPool.createThreadPool("socketTest");

    public static void main(String[] args) {
        /** 5/13/2019 启动服务 */
        new Server();
    }

    private void init() throws IOException {
        port = 9999;
        clients = new ArrayList<>();
        server = new ServerSocket(port);
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = server.accept();
            clients.add(socket);
            socketTest.submit(new ConnectionHandle(socket));
        }
    }

    public Server() {
        try {
            init();
            listen();
        } catch (Exception ex) {
        }
    }

    class ConnectionHandle implements Runnable {
        Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        public String msg;

        public ConnectionHandle(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                msg = socket.getInetAddress() + " " + clients.size();
                sendMsg();
                byte[] byteData = new byte[99];
                while ((msg = br.readLine()) != null) {
                    msg = socket.getInetAddress() + " " + msg;
                    sendMsg();
                }
            } catch (Exception ex) {

            }
        }

        public void sendMsg() {
            try {
                System.out.println(msg);
                for (int i = clients.size() - 1; i >= 0; i--) {
                    pw = new PrintWriter(clients.get(i).getOutputStream(), true);
                    pw.println(msg);
                    pw.flush();
                }
            } catch (Exception ex) {
            }
        }
    }

}