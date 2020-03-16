package net.socket.server;

import net.Msg;
import net.utils.DataUtils;
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

    int port;
    List<Socket> clients;
    ServerSocket server;
    ExecutorService socketTest = CustomThreadPool.createThreadPool("socketTest");

    public static void main(String[] args) {
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
            socketTest.submit(new ConnectionHandleForHeart(socket));
        }
    }

    public Server() {
        try {
            init();
            listen();
        } catch (Exception ex) {
        }
    }

    class ConnectionHandleForHeart implements Runnable {
        Socket socket;
        Msg receive = new Msg();
        Msg send = new Msg();

        public ConnectionHandleForHeart(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[99];
                    inputStream.read(bytes);
                    receive.setData(0, 99, bytes);
                    byte[] sendData = new byte[10];
                    send.setData(0, 3, DataUtils.getData(bytes, 0, 3));
                    send.setData(3, 4, DataUtils.getData(bytes, 3, 4));
                    send.setData(7, 1, DataUtils.getData(bytes, 7, 1));
                    send.setData(8, 1, DataUtils.getData(bytes, 8, 1));
                    send.setData(9, 1, DataUtils.getData(bytes, 9, 1));
                    socket.getOutputStream().write(send.getData(0, 10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ConnectionHandleForChat implements Runnable {
        Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        public String msg;

        public ConnectionHandleForChat(Socket s) {
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