package net.netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import net.utils.DataUtils;
import net.socket.constants.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wulifu
 */
public class Client {

    public static void main(String[] args) throws Exception {
        net.netty.client.Client client = new net.netty.client.Client("127.0.0.1", 9999);
        client.start();
        Channel channel = client.getChannel();
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        while (true) {
            msg = re.readLine();
            if ("".equals(msg.trim())) {
                continue;
            } else if (msg.equals(Constants.command + Constants.END)) {
                break;
            }
            byte[] bytes = DataUtils.getBytes(msg);
            channel.writeAndFlush(Unpooled.wrappedBuffer(bytes));
        }
    }
}
