package net.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wulifu
 */
public class ClientHandler extends SimpleChannelInboundHandler<byte[]> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) throws Exception {
        System.out.println(bytes);
        System.out.println("fdasfdsafdsa");
    }
}
