package grain.controller;

import grain.constants.Msg;
import grain.constants.Strings;
import grain.runs.ManageCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.net.AddressUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author laowu
 */
@Slf4j
@RestController
@RequestMapping(Strings.center)
public class ManagerController {

    @PostMapping(Strings.REGISTER)
    public Msg register(String info, HttpServletRequest request, HttpServletResponse response) {
        String ipAddress = AddressUtils.getIPAddress(request);
        ManageCenter.addNode(ipAddress);
        return Msg.success();
    }

    @PostMapping(Strings.taskComplete)
    public Msg completeTask(String taskId, HttpServletRequest request) {
        try {
            //file.transferTo(new File("localFilePath"));
            String ipAddress = AddressUtils.getIPAddress(request);
            ManageCenter.complete(taskId, ipAddress);

            log.info("task success {}", taskId);
            return Msg.success();
        } catch (Exception e) {
            return Msg.error(Msg.code_file_transfer_error, e.getMessage());
        }
    }

    @PostMapping(Strings.taskError)
    public Msg taskError() {
        return Msg.success();
    }

}
