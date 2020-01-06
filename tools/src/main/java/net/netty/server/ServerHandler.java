package net.netty.server;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wulifu
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        System.out.println(s);
        channel.writeAndFlush(Unpooled.copiedBuffer("fdsa" + s, CharsetUtil.UTF_8));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        System.out.println("[SERVER] - " + incoming.remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("[SERVER] - " + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("[SERVER] - " + incoming.remoteAddress() + "掉线");
    }
}
