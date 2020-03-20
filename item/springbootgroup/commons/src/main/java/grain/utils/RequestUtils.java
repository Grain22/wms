package grain.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author wulifu
 */
public class RequestUtils {
    public static final String HTTP_PREFIX = "http://";

    public static String getUrl(String host, String port, String target) {
        return HTTP_PREFIX + host + ":" + port + "/" + target;
    }

    public static String sendPost(String url, MultiValueMap<String,? extends Object> keyValues) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, ? extends Object>> mapHttpEntity = new HttpEntity<>(keyValues, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, mapHttpEntity, String.class);
        return stringResponseEntity.getBody();
    }

    public static <T> T sendPost(String url, MultiValueMap<String, ? extends Object> keyValues, Class<T> clazz) {
        String s = sendPost(url, keyValues);
        return JSON.parseObject(s, clazz);
    }
}
