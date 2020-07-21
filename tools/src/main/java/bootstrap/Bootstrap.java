package bootstrap;

import com.jcraft.jsch.JSchException;
import net.socket.server.Server;

public class Bootstrap {
    public static void main(String[] args) throws JSchException {
        Server.run(args);
    }
}
