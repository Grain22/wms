package tools;

import lombok.NonNull;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author laowu
 * @version 4/12/2019 10:40 AM
 */
@SuppressWarnings("unused")
public final class StringUtils {
    /**
     * 中文转换为Unicode编码
     * translate chinese to unicode encoding
     *
     * @param chinese
     * @return 编码转换结果
     */
    public String getUnicode(final String chinese) {
        char[] utfBytes = chinese.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    /**
     * translate unicode to chinese default
     *
     * @param unicodeStr
     * @return
     */
    public static String decodeUnicode(final String unicodeStr) {
        int start = 0;
        int end;
        final StringBuilder buffer = new StringBuilder();
        while (start > -1) {
            end = unicodeStr.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = unicodeStr.substring(start + 2, unicodeStr.length());
            } else {
                charStr = unicodeStr.substring(start + 2, end);
            }
            /* 16进制parse整形字符串。*/
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(Character.toString(letter));
            start = end;
        }
        return buffer.toString();
    }

    /**
     * check if phone
     *
     * @param mobile string by int
     * @return true or false
     */
    public static boolean isMobile(@NonNull String mobile) {
        return Pattern.matches("^(1(([34578][0-9])))\\d{8}$", mobile);
    }

    /**
     * join string list by separator
     *
     * @param list      string list
     * @param separator separator in string
     * @return string after join
     */
    public static String join(List<String> list, String separator) {
        return list.stream().collect(Collectors.joining(separator));
    }

    /**
     * get random string
     *
     * @return year month
     */
    public static String getRandomString() {
        Random r = new Random();
        return (DateUtils.getTimeStringLong() + String.valueOf(r.nextLong())).replaceAll("-", "");
    }

    /**
     * under line to camel case
     *
     * @param string ab_cd_ef
     * @return abCdEf
     */
    public static String underLineToCamelCase(String string) {
        String[] split = string.split("_");
        if (split.length == 1) {
            return string;
        } else {
            int i = 0;
            StringBuilder str = new StringBuilder();
            for (String s : split) {
                if (i++ == 0) {
                    str.append(s);
                } else {
                    str.append(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
                }
            }
            return str.toString();
        }
    }

    public static boolean contains(String string, String check) {
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static String decapitalize(String name) {
        if (name != null && name.length() != 0) {
            if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
                return name;
            } else {
                char[] chars = name.toCharArray();
                chars[0] = Character.toLowerCase(chars[0]);
                return new String(chars);
            }
        } else {
            return name;
        }
    }

    public static String capitalize(String name) {
        if (name != null && name.length() != 0) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return name;
        }
    }
}
