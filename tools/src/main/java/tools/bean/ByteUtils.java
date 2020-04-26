package tools.bean;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wulifu
 */
public class ByteUtils {

    public static byte[] longToBytes(long num) {
        byte[] src = new byte[8];
        src[0] = (byte) (num & 0xFF);
        src[1] = (byte) ((num >> 8) & 0xFF);
        src[2] = (byte) ((num >> 16) & 0xFF);
        src[3] = (byte) ((num >> 24) & 0xFF);
        src[3] = (byte) ((num >> 32) & 0xFF);
        src[3] = (byte) ((num >> 40) & 0xFF);
        src[3] = (byte) ((num >> 48) & 0xFF);
        src[3] = (byte) ((num >> 56) & 0xFF);
        return src;
    }

    public static long bytesToLong(byte[] src) {
        return (src[0] & 0xFF)
                | ((src[1] & 0xFF) << 8)
                | ((src[2] & 0xFF) << 16)
                | ((src[3] & 0xFF) << 24)
                | ((src[4] & 0xFF) << 32)
                | ((src[5] & 0xFF) << 40)
                | ((src[6] & 0xFF) << 48)
                | ((src[7] & 0xFF) << 56);
    }

    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) (value & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[3] = (byte) ((value >> 24) & 0xFF);
        return src;
    }

    public static byte[] charsToBytes(char[] chars) {
        Charset cs = StandardCharsets.UTF_8;
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    public static int bytesToInt(byte[] src) {
        return (src[0] & 0xFF)
                | ((src[1] & 0xFF) << 8)
                | ((src[2] & 0xFF) << 16)
                | ((src[3] & 0xFF) << 24);
    }

    public static char[] bytesToChars(byte[] bytes) {
        Charset cs = StandardCharsets.UTF_8;
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    public static byte[] charToBytes(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static char bytesToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }

    private static String byteToHexString(byte b) {
        final String[] hexDigits = {
                "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "a", "b", "c", "d", "e", "f"};
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String bytesToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
}
