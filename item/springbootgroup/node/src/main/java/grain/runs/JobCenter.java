package grain.runs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wulifu
 */
public class JobCenter {

    private static Map<Integer, Object> taskList = new ConcurrentHashMap<>();

}
