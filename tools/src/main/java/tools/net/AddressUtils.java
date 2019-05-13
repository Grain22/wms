package tools.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author laowu
 * @version 5/10/2019 1:08 PM
 */
public class AddressUtils {
    private static InetAddress inetAddress = null;

    static {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static String getHostAddress() {
        return inetAddress.getHostAddress();
    }

    public static String getHostName() {
        return inetAddress.getHostName();
    }

    /**
     * 获取本机
     * @return
     */
    public static List<String> getAddresses(){
        List<String> res = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements()) {
                    InetAddress nextElement = addresss.nextElement();
                    String hostAddress = nextElement.getHostAddress();
                    res.add(hostAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}