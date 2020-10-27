package grain;

import grain.net.socket.server.Server;

public class Bootstrap {
    public static void main(String[] args) {
        Thread call = new Thread(() -> new Server(new String[]{"9002", "100"}));
        Thread key = new Thread(() -> new Server(new String[]{"9003", "99"}));
        call.start();
        key.start();
    }
}
