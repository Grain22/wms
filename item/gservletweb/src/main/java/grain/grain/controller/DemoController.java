package grain.grain.controller;

import grain.grain.service.DemoService;
import spring.framework.annotation.GController;
import spring.framework.annotation.GInject;
import spring.framework.annotation.GRequestMapping;
import spring.framework.annotation.GRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author laowu
 * @version 5/13/2019 4:19 PM
 */
@GController
@GRequestMapping("/demo")
public class DemoController {
    @GInject
    private DemoService demoService;

    @GRequestMapping("/getMsg")
    public void getInfo(HttpServletRequest request, HttpServletResponse response, @GRequestParam("name") String name) {
        String info = demoService.getInfo(name);
        try {
            response.getWriter().write(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
