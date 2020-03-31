package tools;

import tools.bean.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static tools.bean.ByteUtils.byteArrayToHexString;

/**
 * @author laowu
 * @version demo
 * 2019/7/3 17:34
 */
public class DigestUtil {
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static String sha1Digests(String input) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA1);
            resultString = byteArrayToHexString(md.digest(stringByte(input)));
        } catch (Exception ignore) {
        }
        return resultString;
    }

    public static String sha256Digests(String input) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            resultString = byteArrayToHexString(md.digest(stringByte(input)));
        } catch (Exception ignore) {
        }
        return resultString;
    }

    public static String md5Digests(String input) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            resultString = byteArrayToHexString(md.digest(stringByte(input)));
        } catch (Exception ignore) {
        }
        return resultString;
    }
    private static byte[] stringByte(String input) {
        return input.trim().getBytes(StandardCharsets.UTF_8);
    }
}
