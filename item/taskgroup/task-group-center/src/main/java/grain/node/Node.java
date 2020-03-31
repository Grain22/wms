package grain.node;

import lombok.Data;
import tools.DigestUtil;

/**
 * @author wulifu
 */
@Data
public class Node {

    /**
     * 节点标签
     */
    String nodeId;
    String nodeHost;
    String nodePort;
    String nodeRegisterDateTime;
    String nodeLastCheckDateTime;
    int nodeRetryTimes = 0;
    int nodePriority = 5;
    boolean nodeAvailable = true;

    public static final int normal = 0;
    public static final int wait = 2;
    public static final int drop = 3;
    public static final int uncheck = -1;

    public static final int priority_top = 9;
    public static final int priority_bottom = 0;

    public void setNodeId() {
        this.nodeId = DigestUtil.md5Digests(nodeHost + nodePort + nodeRegisterDateTime);
    }

    public static String getIdByMd5(String nodeHost, String nodePort, String nodeRegisterDateTime) {
        return DigestUtil.md5Digests(nodeHost + nodePort + nodeRegisterDateTime);
    }

    public void increasePriority() {
        if (this.nodePriority < priority_top) {
            this.nodePriority++;
        }
    }
    public void decreasePriority(){
        if (this.nodePriority > priority_bottom) {
            this.nodePriority--;
        }
    }
}
