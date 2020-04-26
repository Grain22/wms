import tools.net.AddressUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class Test {

    private static final List MAP = new ArrayList();

    private static final int count_length = 14;

    private static final int MAX_VALUE = new Double(Math.pow(2, count_length) - 1).intValue();

    private static String getSerial(int i) {
        return String.format("%" + count_length + "s", Integer.toBinaryString(i)).replace(" ", "0");
    }

    public static void run(String[] args) throws IOException {
        int count = 0;
        String ip = AddressUtils.ipToBinary("255.255.255.255").substring(24);
        for (int i = 0; i < 550; i++) {
            long l = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() == l) {
                    String serial = getSerial(count++);
                    String s = "" + Long.toBinaryString(l) + ip + serial;
                    System.out.println(s.length() + " " + Long.toBinaryString(l) + " " + ip + " " + serial + " " + count);

                    MAP.add(Long.parseLong(s, 2));
                } else {
                    break;
                }
            }
            count = 0;
            System.out.println(MAP.size());
            MAP.clear();
        }
    }
}

