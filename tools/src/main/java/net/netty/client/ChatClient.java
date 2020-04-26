package net.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author laowu
 */
public class ChatClient {
    private String host;
    private int port;
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void run(String[] args) {
        new ChatClient("localhost", 9000).run();
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());
            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String s = input.readLine();
                channel.writeAndFlush(s + "\n");
            }
        } catch (Exception ignore) {
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
