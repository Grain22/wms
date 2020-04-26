package tools.bean.math;

import java.math.BigInteger;
import java.util.Stack;

/**
 * 数字工具类
 *
 * @author wulifu
 */
public class NumberUtils {

    public static final String DIGIT_STRING = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";
    private static final char[] DIGIT_CHARS = DIGIT_STRING.toCharArray();
    private static final BigInteger INTEGER0 = new BigInteger("0");

    /**
     * 10进制转任意进制
     */
    public static String numToRadix(String number, int radix) {
        if (radix < 0 || radix > DIGIT_STRING.length()) {
            radix = DIGIT_STRING.length();
        }
        BigInteger bigNumber = new BigInteger(number);
        BigInteger bigRadix = new BigInteger(radix + "");
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (!bigNumber.equals(INTEGER0)) {
            stack.add(DIGIT_CHARS[bigNumber.remainder(bigRadix).intValue()]);
            bigNumber = bigNumber.divide(bigRadix);
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0" : result.toString();
    }

    /**
     * 任意进制转10进制
     */
    public static String radixToNum(String number, int radix) {
        if (radix < 0 || radix > DIGIT_STRING.length()) {
            radix = DIGIT_STRING.length();
        }
        if (radix == 10) {
            return number;
        }

        char[] ch = number.toCharArray();
        int len = ch.length;

        BigInteger bigRadix = new BigInteger(radix + "");
        BigInteger result = new BigInteger("0");
        BigInteger base = new BigInteger("1");

        for (int i = len - 1; i >= 0; i--) {
            BigInteger index = new BigInteger(DIGIT_STRING.indexOf(ch[i]) + "");
            result = result.add(index.multiply(base));
            base = base.multiply(bigRadix);
        }

        return result.toString();
    }
}
