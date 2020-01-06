package net.netty;

/**
 * @author wulifu
 */
public class Server {
    public static void main(String[] args) throws Exception {
        net.netty.server.Server server = new net.netty.server.Server();
        server.bind(9999);
    }
}
