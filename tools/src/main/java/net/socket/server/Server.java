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
        port = 9998;
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
        public Msg msg = new Msg();

        public ConnectionHandle(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    byte[] bytes = new byte[99];
                    dataInputStream.read(bytes);
                    msg.setData(0, 99, bytes);
                    System.out.println(DataUtils.getInt(msg.getData(3, 4)));
                    System.out.println(DataUtils.getString(msg.getData(81, 17)).trim());
                    System.out.println(DataUtils.getString(msg.getData(28, 20)).trim());
                    Msg back = new Msg();
                    back.setData(0, 3, msg.getData(0, 3));
                    back.setData(3, 4, msg.getData(3, 4));
                    back.setData(7, 1, msg.getData(7, 1));
                    back.setData(8, 1, DataUtils.getBytes("0"));
                    back.setData(9, 1, msg.getData(98, 1));
                    dataOutputStream.write(back.getMsg());
                    dataOutputStream.flush();
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
                int read = socket.getInputStream().read(byteData);
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