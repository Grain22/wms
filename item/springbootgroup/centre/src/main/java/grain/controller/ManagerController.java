package grain.controller;

import grain.constants.Strings;
import grain.runs.ManageCenter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.net.AddressUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author laowu
 */
@RestController
@RequestMapping("center")
public class ManagerController {

    @GetMapping(Strings.REGISTER)
    public void register(String info, HttpServletRequest request, HttpServletResponse response) {
        String ipAddress = AddressUtils.getIPAddress(request);
        ManageCenter.addNode(ipAddress);
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().write("register success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
