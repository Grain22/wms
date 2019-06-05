package tools.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author laowu
 * @version 5/7/2019 12:22 PM
 */
@SuppressWarnings("unused")
public class ObjectUtils {
    public static boolean isNull(Object o) {
        return o == null;
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

    public static <T> T deepClone(T t) {
        try {
            /**字节流对象*/
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            /**开始转换该对象*/
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            /**写到当前类，当然也可以写入文件*/
            oos.writeObject(t);
            /**字节输出流*/
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            /**输出该对象*/
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            return (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("克隆出错" + Arrays.toString(e.getStackTrace()));
            return t;
        }
    }
}