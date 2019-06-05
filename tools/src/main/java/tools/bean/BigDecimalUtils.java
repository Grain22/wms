package tools.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author laowu
 */
public class BigDecimalUtils {
    public static final String getRateInTwoBigdecimal(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b1.add(b2), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP).toString() + "/" + b2.divide(b1.add(b2), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static boolean isEmpty(BigDecimal temp) {
        return temp == null || temp.compareTo(new BigDecimal(0)) == 0;
    }

    public static BigDecimal safe(Object zero) {
        if (Objects.isNull(zero)) {
            return BigDecimal.ZERO;
        }
        if (zero instanceof BigDecimal) {
            return (BigDecimal) zero;
        } else if (zero instanceof String) {
            if("".equals(zero)){
                return BigDecimal.ZERO;
            }
            return new BigDecimal((String) zero);
        } else if (zero instanceof BigInteger) {
            return new BigDecimal((BigInteger) zero);
        } else if (zero instanceof Number) {
            return new BigDecimal(((Number) zero).doubleValue());
        } else {
            throw new ClassCastException("Not possible to coerce [" + zero + "] from class " + zero.getClass() + " into a BigDecimal.");
        }
    }
}
