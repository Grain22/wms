package grain.runs;

import grain.constants.Strings;
import grain.utils.RequestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author wulifu
 */
public class CenterCommand {

    public static void cancelTask(String host, String port, String taskId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("taskId", taskId);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node/").append(Strings.cancelTask).toString(), map);
    }

    public static void dropTask(String host, String port, String taskId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("taskId", taskId);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node/").append(Strings.dropTask).toString(), map);
    }

    public static boolean checkNodeAvailable(String host, String port, String distributeNodeHost) {
        String s = RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/node/").append(Strings.dropTask).toString(), null);
        return true;
    }

    public static void completeTask(String host,String port,String taskId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("taskId", taskId);
        RequestUtils.sendPost(new StringBuilder(RequestUtils.HTTP_PREFIX).append(host).append(":").append(port).append("/api/center/").append(Strings.taskComplete).toString(), map);
    }
}
