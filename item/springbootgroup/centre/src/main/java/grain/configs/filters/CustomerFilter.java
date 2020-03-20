package grain.configs.filters;

import grain.configs.GlobalYmlConfigParams;
import grain.constants.Strings;
import grain.runs.ManageCenter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.context.annotation.Configuration;
import tools.net.AddressUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpMethod.OPTIONS;

/**
 * @author laowu
 */
@Slf4j
@Configuration
public class CustomerFilter implements Filter {

    private final GlobalYmlConfigParams params;

    public CustomerFilter(GlobalYmlConfigParams params) {
        this.params = params;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        characterEncoding(request, response);
        setHeaders(request, response);
        if (!crossOriginResourceSharing(request, response)) {
            log.info("cors return");
            return;
        }
      /*  if (!specialMethodFilter(request, response)) {
            log.info("special method return");
            return;
        }*/
        if (!authentication(request, response)) {
            log.info("authentication return");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean specialMethodFilter(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String method = req.getServletPath();
        if (method.equals("/"+Strings.REGISTER)) {
            log.info("节点注册请求");
            ManageCenter.addNode(AddressUtils.getIPAddress(req));
            resp.setStatus(HttpStatus.SC_OK);
            resp.getWriter().write("OPTIONS returns OK");
            return false;
        }
        log.info("非特殊方法,正常执行");
        return true;
    }

    private void characterEncoding(ServletRequest request, ServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            log.info("set character encoding fail");
        }
    }

    private void setHeaders(ServletRequest request, ServletResponse response) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Headers",
                "Authorization,DNT" +
                        ",X-CustomHeader" +
                        ",Keep-Alive" +
                        ",User-Agent" +
                        ",X-Requested-With" +
                        ",If-Modified-Since" +
                        ",Cache-Control" +
                        ",Content-Type" +
                        ",userId" +
                        ",token" +
                        ",charset" +
                        ",X-Content-Type-Options" +
                        ",Access-Control-Allow-Origin" +
                        ",Access-Control-Allow-Methods" +
                        ",Access-Control-Max-Age,Access-Control-Allow-Credentials");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Content-Type", "application/json");
        resp.setHeader("charset", "UTF-8");
        resp.setHeader("X-Content-Type-Options", "nosniff");
        //resp.setHeader("Access-Control-Allow-Headers", "*");
    }

    private boolean crossOriginResourceSharing(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String method = req.getMethod();
        if (method.equals(OPTIONS.name())) {
            log.info("请求预检,直接返回成功结果");
            resp.setStatus(HttpStatus.SC_OK);
            resp.getWriter().write("server OK");
            return false;
        }
        return true;
    }

    private static String NULL = "null";

    private boolean authentication(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        return true;
    }

}
