package pressuretest;


import com.alibaba.fastjson.JSON;
import tools.CustomerLogger;
import tools.net.HttpRequestUtils;
import tools.thread.CustomThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author wulifu
 */
public class BootStrap {

    static CustomerLogger logger = CustomerLogger.getLogger(BootStrap.class);
    static int cont = 0;

    private static synchronized int getCont() {
        return cont++;
    }

    public static void main(String[] args) {
        try {
            HttpRequestUtils.HttpRequestInfo init = init();
            ExecutorService threadPool = CustomThreadPool.createThreadPool("并发连接测试", false, Thread.NORM_PRIORITY, 5000, 5000, 500);
            long l = System.currentTimeMillis() + 5000;
            logger.log("begin time " + l);
            for (int i = 0; i < 5000; i++) {
                threadPool.submit(new HttpRequestSend(l, init));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean checkIsEnd(String s) {
        return "+".equals(s);
    }

    public static HttpRequestUtils.HttpRequestInfo init() throws IOException {
        String httpPrefix = "http://";
        String httpsPrefix = "https://";
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        HttpRequestUtils.HttpRequestInfo httpRequestInfo = new HttpRequestUtils.HttpRequestInfo();
        String string;
        do {
            do {
                logger.log("输入请求地址 默认http");
                string = re.readLine();
                if (!string.contains(httpPrefix) && !string.contains(httpsPrefix)) {
                    httpRequestInfo.setUrl(httpPrefix + string);
                }
                logger.log("输入的请求地址为 " + httpRequestInfo.getUrl() + " 确认输入 (+)");
                string = re.readLine();
            } while (!checkIsEnd(string));
            do {
                logger.log("输入请求头 格式 (key:value) 退出输入(+)");
                Map<String, String> headers = new HashMap<>(3);
                string = re.readLine();
                if (checkIsEnd(string)) {
                    break;
                }
                while (true) {
                    try {
                        String[] split = string.split(":");
                        headers.put(split[0], split[1]);
                        logger.log("确认请求头 " + JSON.toJSONString(headers) + " 退出输入 (+)");
                        string = re.readLine();
                        if (checkIsEnd(string)) {
                            break;
                        }
                    } catch (Exception e) {
                        logger.log("输入数据 有问题 " + string);
                    }
                }
                httpRequestInfo.setHeader(headers);
                logger.log("确认请求信息 " + JSON.toJSONString(headers) + " 确认输入 (+)");
                string = re.readLine();
            } while (!checkIsEnd(string));
            do {
                logger.log("输入请求体 退出输入(+)");
                string = re.readLine();
                if (checkIsEnd(string)) {
                    break;
                }
                httpRequestInfo.setBody(string);
                logger.log("输入的请求体为 " + string + " 确认输入 (+)");
                string = re.readLine();
            } while (!checkIsEnd(string));
            logger.log("确认请求信息 " + JSON.toJSONString(httpRequestInfo) + " 确认输入 (+)");
            string = re.readLine();
        } while (!checkIsEnd(string));
        logger.log(JSON.toJSONString(httpRequestInfo));
        return httpRequestInfo;
    }

    static class HttpRequestSend implements Runnable {

        long beginTime;
        HttpRequestUtils.HttpRequestInfo info;
        int cont;

        public HttpRequestSend(long beginTime, HttpRequestUtils.HttpRequestInfo info) {
            this.beginTime = beginTime;
            this.info = info;
            this.cont = getCont();
        }

        @Override
        public void run() {
            String s;
            try {
                long l = beginTime - System.currentTimeMillis();
                logger.log(l);
                if (l < 0) {
                    l = 0;
                }
                Thread.sleep(l);
                s = HttpRequestUtils.sendRequest(info);
                logger.log(cont + " " + s);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(cont + " " + e.getCause());
            }
        }
    }


}
