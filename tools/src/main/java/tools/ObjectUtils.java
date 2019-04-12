package tools;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ObjectUtils {
    public static boolean isNull(Object o){
        return o == null;
    }

    public static <V> V safe(V v) {
        if (Objects.isNull(v)) {
            throw new RuntimeException(v.getClass().getName() +"传入数据不应为空");
        }
        return v;
    }

    public static <V> V safe(V v, String tip) {
        if (Objects.isNull(v)) {
            StringBuilder msg =new StringBuilder();
            if (!Objects.isNull(tip)) {
                msg.append(tip);
            } else {
                msg.append(v.getClass().getName() + "传入数据不应为空");
            }
            throw new RuntimeException(msg.toString());
        }
        return v;
    }

    public static Stream<Long> range(final long start, long length, int step) {
        Supplier<Long> seed = new Supplier<Long>() {
            private long next = start;
            @Override
            public Long get() {
                long nextTem = next;
                next += step;
                return nextTem;
            }
        };
        return Stream.generate(seed).limit(length);
    }
}
