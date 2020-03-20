package grain.bean;

import lombok.Data;

import java.util.Map;

/**
 * @author wulifu
 */
@Data
public class ServerInfo {
    private Map<String, String> all;
    private Map<String, String> waitForFileTransfer;
    private Map<String, String> waitForRender;
    private Map<String, String> inRender;
    private Map<String, String> complete;
    private Map<String, String> cancel;
}
