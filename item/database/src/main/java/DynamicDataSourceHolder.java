/**
 * @author laowu
 * @version 6/5/2019 5:04 PM
 */
public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }
}
