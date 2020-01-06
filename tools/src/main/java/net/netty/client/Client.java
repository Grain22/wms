package net.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wulifu
 */
public class Client {
    private String host = "127.0.0.1";
    private int port = 9999;
    private Channel channel;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ClientHandler());
                    }
                });
        //发起异步连接请求，绑定连接端口和host信息
        final ChannelFuture future = b.connect(host, port).sync();
        future.addListener((ChannelFutureListener) arg -> {
            if (future.isSuccess()) {
                System.out.println(future.channel().remoteAddress());
            } else {
                future.cause().printStackTrace();
                group.shutdownGracefully(); //关闭线程组
            }
        });
        this.channel = future.channel();
    }

    public Channel getChannel() {
        return channel;
    }
}
