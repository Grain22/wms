package tools.bean;

import java.util.Hashtable;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author laowu
 */
public class MapUtils {
    /**
     * merge map by customer method
     * @param merge
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public static <K,V> void mapMerge(Map merge ,K key, V value) {
        BiFunction biFunction = new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                return null;
            }
        };
        merge.merge(key, value, (k, v) ->  biFunction);
    }
}
