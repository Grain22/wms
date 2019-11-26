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
    private static String HOST = "10.1.63.26";
    private static String PORT = "19222";
    private static String NAME = "sichuan";
    private static String PWD = "1qaz@WSX";

    private static FtpClient ftpClient = null;

    public static FtpClient getFTPClient() throws IOException, FtpProtocolException {
        ftpClient = DefaultFtpClientProvider.provider().createFtpClient();

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

    public static void main(String[] args) throws IOException, FtpProtocolException {
        getFTPClient();
        try {
            Stream.of(ftpClient.listFiles("/")).forEach(System.out::println);
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
