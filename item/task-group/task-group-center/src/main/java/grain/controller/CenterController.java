package grain.controller;

import grain.Msg;
import grain.NodeManager;
import grain.Strings;
import grain.task.TaskTable;
import jakarta.servlet.http.HttpServletRequest;
import org.grain.tools.net.AddressUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author grain
 */
@RequestMapping(Strings.CENTER)
@RestController
public class CenterController {

    @PostMapping(Strings.REGISTER)
    public Msg register(String port, HttpServletRequest request) {
        String ipAddress = AddressUtils.getIPAddress(request);
        String register = NodeManager.register(ipAddress, port);
        return Msg.success(register);
    }

    @PostMapping(Strings.TASK_COMPLETE)
    public Msg completeTask(String nodeId, String taskId) {
        try {
            boolean complete = TaskTable.complete(taskId, nodeId);
            return Msg.success(complete);
        } catch (Exception e) {
            return Msg.error(Msg.code_file_transfer_error, e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    @PostMapping(Strings.TASK_ERROR)
    public Msg taskError(String nodeId, String taskId) {
        return Msg.success();
    }

}
