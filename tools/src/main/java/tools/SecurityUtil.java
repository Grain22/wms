package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author laowu
 */
public class SecurityUtil {
    /**
     * MD5
     *
     * @param input
     * @return
     */
    public static byte[] toMD5(byte[] input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(input);
        return md.digest();
    }

}
