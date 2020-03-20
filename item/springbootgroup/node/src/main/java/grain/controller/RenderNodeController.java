package grain.controller;

import grain.constants.Msg;
import grain.constants.Strings;
import grain.constants.exceptions.TaskInfoError;
import grain.runs.JobCenter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulifu
 */
@RestController
@RequestMapping(Strings.node)
public class RenderNodeController {

    @PostMapping(Strings.addTask)
    public Msg addTask(String taskId, String taskInfo) {
        try {
            return Msg.success();
        } catch (TaskInfoError e) {
            return Msg.error(Msg.code_task_info_error, e.getMessage());
        } catch (Exception e) {
            return Msg.error(Msg.code_unhandled_error, e.getMessage());
        }
    }

    @PostMapping(Strings.serverStatus)
    public Msg serverStatus(){
        return Msg.success(JobCenter.getServerInfo());
    }
}
