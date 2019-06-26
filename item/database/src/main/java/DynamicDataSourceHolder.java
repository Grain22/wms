/**
 * @author laowu
 * @version 6/5/2019 5:04 PM
 */
public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void putDataSource(String name) {
        HOLDER.set(name);
    }

    public static String getDataSouce() {
        return HOLDER.get();
    }

    public static final void close(){
        HOLDER.remove();
    }
}
