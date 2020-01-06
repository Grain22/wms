package net.utils;


/**
 * @author wulifu
 */
public class DataUtils {

    public static byte[] getBytes(String str) {
        return str.getBytes();
    }

    public static String getString(byte[] bytes) {
        return new String(bytes);
    }

    public static byte[] getBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (num >> 24);
        bytes[1] = (byte) (num >> 16);
        bytes[2] = (byte) (num >> 8);
        bytes[3] = (byte) (num);
        return bytes;
    }

    public static int getInt(byte[] bytes) {
        int num = 0;
        num += (bytes[0] << 24);
        num += (bytes[1] << 24 >>> 8);
        num += (bytes[2] << 24 >>> 16);
        num += (bytes[3] << 24 >>> 24);
        return num;
    }

    public static byte[] padRight(byte[] original, int length, byte padding) {
        if (length <= original.length) {
            return original;
        }
        byte[] padded = new byte[length];
        for (int i = 0; i < original.length; i++) {
            padded[i] = original[i];
        }
        for (int i = original.length; i < length; i++) {
            padded[i] = padding;
        }
        return padded;
    }

    public static byte[] padRight(String original, int length, byte padding) {
        return padRight(original.getBytes(), length, padding);
    }
}
