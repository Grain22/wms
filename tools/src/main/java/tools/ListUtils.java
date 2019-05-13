package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  @author     laowu
 *  @version    5/7/2019 12:25 PM
 *
*/
@SuppressWarnings("unused")
public class ListUtils {

    public static <V> List<V> auto(List<V> list) {
        if (Objects.isNull(list)) {
            return new ArrayList<>();
        }
        return list;
    }
}
