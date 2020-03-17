package grain.controller;

import grain.constants.Strings;
import grain.runs.ManageCenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tools.net.AddressUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author laowu
 */
@Controller
@RequestMapping("center")
public class ManagerController {

    @PostMapping(Strings.REGISTER)
    public void register(String info, HttpServletRequest request) {
        String ipAddress = AddressUtils.getIPAddress(request);
        ManageCenter.addNode(ipAddress);
    }
}
