package bootstrap;

import com.jcraft.jsch.JSchException;
import rmi.server.Server;

/**
 * @author wulifu
 */
public class Bootstrap {
    public static void main(String[] a) throws JSchException {
        Server.run();
    }
}
