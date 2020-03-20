package grain.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulifu
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class NodeInfo {
    String nodeHost;
    Map<Integer, TaskInfo> runningMap = new HashMap<>();
    Map<Integer, TaskInfo> completeMap = new HashMap<>();
    Map<Integer, TaskInfo> waitMap = new HashMap<>();
    Map<Integer, TaskInfo> errorMap = new HashMap<>();
    Map<Integer, TaskInfo> cancelMap = new HashMap<>();
    long updateTime;
}