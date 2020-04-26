package tools.net;

import lombok.Data;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * @author wulifu
 */
public class HttpRequestUtils {
    public static String sendRequest(HttpRequestInfo info) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(info.getUrl());
        method.getParams().setContentCharset("UTF-8");
        if (!Objects.isNull(info.getHeader())) {
            Map<String, String> header = info.getHeader();
            header.forEach(method::addRequestHeader);
        }
        if (!Objects.isNull(info.getBody())) {
            RequestEntity entity = new StringRequestEntity(info.getBody(), null, null);
            method.setRequestEntity(entity);
        }
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    @Data
    public static class HttpRequestInfo {
        String url;
        Map<String, String> header;
        String body;
        ArrayList<File> files;
    }

}
