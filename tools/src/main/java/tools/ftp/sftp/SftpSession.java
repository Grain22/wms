package tools.ftp.sftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author wulifu
 */
@Slf4j
public class SftpSession {
    Session session = null;
    int count = 0;
    String host;
    String port;
    String username;
    String password;

    private SftpSession() {
    }

    public SftpSession(String host, String port, String username, String password) {
        log.debug("init sftp client {} {} {} {}", host, port, username, password);
        this.host = host;
        this.password = password;
        this.port = port;
        this.username = username;
        getSession();
    }

    private Session getSession() {
        try {
            if (session == null) {
                log.debug("session is null begin to init");
                session = new JSch().getSession(username, host, Integer.parseInt(port));
                session.setPassword(password);
                Properties sshConfig = new Properties();
                sshConfig.put("StrictHostKeyChecking", "no");
                session.setConfig(sshConfig);
                session.connect(6000);
                log.debug("session check connection {}", session.isConnected());
            }
        } catch (Exception e) {
            log.error("session create failed case by {}", e.getMessage());
        }
        return session;
    }

    public void disconnected() {
        session.disconnect();
    }

    public ChannelSftp getChannel() throws JSchException {
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        log.debug("create channel id {}", channel.getId());
        return channel;
    }
}
