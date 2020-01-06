package net.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wulifu
 */
public class Server {
    public void bind(int port) throws Exception {
        //bossGroup就是parentGroup，是负责处理TCP/IP连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                //绑定客户端连接时候触发操作
                .childHandler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel sh) {
                                //使用ServerHandler类来处理接收到的消息
                                ChannelPipeline pipeline = sh.pipeline();
                                pipeline.addLast(new ServerHandler());
                                pipeline.addLast(new StringEncoder());
                                pipeline.addLast(new StringDecoder());
                            }
                        }
                );
        //绑定监听端口，调用sync同步阻塞方法等待绑定操作完
        ChannelFuture future = sb.bind(port).sync();
        //成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
        future.channel().closeFuture().sync();
    }
}
