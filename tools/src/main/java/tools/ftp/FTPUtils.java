package tools.ftp;

import org.omg.CORBA.PRIVATE_MEMBER;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpClientProvider;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;
import sun.net.ftp.impl.DefaultFtpClientProvider;
import uav.flightrouteana.Point;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.stream.Stream;

/**
 * @author grain
 */
public class FTPUtils {

    public static FtpClient getFTPClient(String HOST, String PORT, String NAME, String PWD) throws IOException, FtpProtocolException {
        FtpClient ftpClient = DefaultFtpClientProvider.provider().createFtpClient();

        SocketAddress socketAddress = new InetSocketAddress(HOST, Integer.parseInt(PORT));
        ftpClient.connect(socketAddress);
        if (ftpClient.isConnected()) {
            throw new RuntimeException("ftp path error");
        }
        ftpClient.login(NAME, PWD.toCharArray());
        if (ftpClient.isLoggedIn()) {
            ftpClient.close();
            throw new RuntimeException("account error");
        }

        ftpClient.setConnectTimeout(60000);
        ftpClient.setBinaryType();

        return ftpClient;
    }

}
