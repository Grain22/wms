package net.socket.server;

import net.Msg;
import net.utils.DataUtils;
import org.codehaus.plexus.util.StringInputStream;
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

        public ConnectionHandleForHeart(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[20];
                    inputStream.read(bytes);
                    receive.setData(0, 20, bytes);
                    socket.getOutputStream().write(receive.getData(0, 5));
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