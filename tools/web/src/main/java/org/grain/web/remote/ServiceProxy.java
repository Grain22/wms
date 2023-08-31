package org.grain.web.remote;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.grain.web.spring.framework.annotation.GRequestMapping;

import java.io.StringReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;


@Slf4j
@SuppressWarnings({"unchecked", "unused"})
public class ServiceProxy {
    private static final String SEPARATOR = "/";


    private static String packageUrl(String path) {
        if (Objects.isNull(path) || path.length() == 0) {
            return SEPARATOR;
        }
        if (!path.startsWith(SEPARATOR)) path += SEPARATOR + path;
        if (!path.endsWith(SEPARATOR)) path += path + SEPARATOR;
        return path;
    }

    private static String[] packageUrl(String... paths) {
        if (Objects.isNull(paths)) return new String[0];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = packageUrl(paths[i]);
        }
        return paths;
    }

    public static <T> T getClass(Class<T> tClass, HttpClient httpClient, String serverPath) {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            log.info("service interface {} method {}", method.getDeclaringClass().getName(), method.getName());
            String[] basicUrl = new String[0];
            if (method.getDeclaringClass().isAnnotationPresent(GRequestMapping.class)) {
                basicUrl = packageUrl(method.getAnnotation(GRequestMapping.class).value());
            }
            String[] targetUrl;
            if (method.isAnnotationPresent(GRequestMapping.class)) {
                targetUrl = method.getAnnotation(GRequestMapping.class).value();
            } else {
                throw new RuntimeException("not support other request method , only post");
            }
            log.info("service interface {} method {} baseUrl {} targetUrl {}", method.getDeclaringClass().getName(), method.getName(), String.join("|", basicUrl), String.join("|", targetUrl));
            String finalParamString = "";
            if (method.isAnnotationPresent(SpecialParam.class)) {
                log.info("use special param generator method");
                finalParamString = method.getAnnotation(SpecialParam.class).value().getDeclaredConstructor().newInstance().invoke(method, args).toString();
            } else {
                log.info("make request use default");
                finalParamString = new DefaultServiceProxyParamHandler().invoke(method, args);
            }
            log.info("generator complete , final param string is {}", finalParamString);
            List<URI> uris = new ArrayList<>(((basicUrl.length == 0) ? 1 : basicUrl.length) * targetUrl.length);
            if (basicUrl.length == 0) {
                Arrays.stream(targetUrl).forEach(a -> uris.add(URI.create(a)));
            }
            String response = null;
            for (URI uri : uris) {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .timeout(Duration.ofMinutes(2))
                        .header(Attributes.Name.CONTENT_TYPE.toString(), "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();
                HttpClient build = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .connectTimeout(Duration.ofMinutes(1))
                        .authenticator(Authenticator.getDefault())
                        .build();
                HttpResponse<String> send = build.send(request, HttpResponse.BodyHandlers.ofString());
                if (send.statusCode() == HttpServletResponse.SC_OK) {
                    response = send.body();
                    break;
                } else {
                    log.warn("uri {} request send fail ", uri.toString());
                }
            }
            if (Objects.isNull(response))
                throw new RuntimeException("服务响应失败");
            if (method.isAnnotationPresent(SpecialResponse.class)) {
                return method.getAnnotation(SpecialResponse.class).value().getDeclaredConstructor().newInstance().invoke(method, response);
            } else {
                JsonReader reader = Json.createReader(new StringReader(response));
                return "";
            }
        };
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, invocationHandler);
    }
}
