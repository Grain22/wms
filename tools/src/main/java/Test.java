import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import tools.PinyinUtils;
import tools.file.FileUtils;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class Test {

    public static void main(String[] args) {

        long l = 199902;
        l = Math.round(l * 1.05);
        System.out.println(l);
    }
}

