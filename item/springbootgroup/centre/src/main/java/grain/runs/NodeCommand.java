package grain.runs;

import grain.constants.NodeInfo;
import grain.constants.Strings;
import grain.utils.RequestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author wulifu
 */
public class NodeCommand {

    public static void addTask(String host, String port, String taskInfo) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("info", taskInfo);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node").append(Strings.addTask).toString(), map);
    }

    public static void cancelTask(String host, String port, String taskId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("taskId", taskId);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node").append(Strings.cancelTask).toString(), map);
    }

    public static void dropTask(String host, String port, String taskId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("taskId", taskId);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node").append(Strings.dropTask).toString(), map);
    }

    public static boolean checkNodeAvailable(String host, String port,String distributeNodeHost) {
        String s = RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node").append(Strings.dropTask).toString(), null);
        return true;
    }
}
