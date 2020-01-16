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
        transVrbtCrbt(args);
    }

    private static void checkFont() {
        String font = "C:\\Users\\wulifu\\Desktop\\font";
        String fontSave = "C:\\Users\\wulifu\\Desktop\\tem\\font";
        File file = new File(font);
        File[] files = file.listFiles();
        Map<String, File> collect = Arrays.stream(files).collect(Collectors.toMap(a -> a.getName(), a -> a));
        String jsont = "{\"code\":2000,\"desc\":\"success\",\"token\":\"token\",\"data\":[{\"id\":42,\"userId\":1,\"categoryId\":-1,\"name\":\"SIMKAI\",\"nameCN\":\"%E6%A5%B7%E4%BD%93\",\"relativePath\":\"font/202001_1_ckK7WiyISZXMTOjd2jhJTDOGeD6N1SgD1nnFGqMoV98pWEaBdL.TTF\",\"createDate\":\"20200114 10:29:04\"},{\"id\":43,\"userId\":1,\"categoryId\":-1,\"name\":\"SIMHEI\",\"nameCN\":\"%E9%BB%91%E4%BD%93\",\"relativePath\":\"font/202001_1_1xcVXLL4jKbh4xi6r7118X0KPb3VH4Iw72kNdnHAhBKS9Cc2Vu.TTF\",\"createDate\":\"20200114 10:29:50\"},{\"id\":44,\"userId\":1,\"categoryId\":-1,\"name\":\"xianer\",\"nameCN\":\"%E8%B4%A4%E4%BA%8C%E4%BD%93\",\"relativePath\":\"font/202001_1_OlQnUIWbgWbNCspy46WSyFJh9ZRRMbVhEkNK2SD2LGZxoEkU1b.ttf\",\"createDate\":\"20200114 14:16:13\"},{\"id\":45,\"userId\":1,\"categoryId\":-1,\"name\":\"zhengrui\",\"nameCN\":\"%E6%AD%A3%E9%94%90%E9%BB%91%E4%BD%93\",\"relativePath\":\"font/202001_1_setgln7xlIpmma7HHTgTa0vFkzeXkF5nwTdQEyZvemnZFeKbxM.ttf\",\"createDate\":\"20200114 14:20:28\"},{\"id\":49,\"userId\":1,\"categoryId\":-1,\"name\":\"siyuanrouhei\",\"nameCN\":\"%E6%80%9D%E6%BA%90%E6%9F%94%E9%BB%91\",\"relativePath\":\"font/202001_1_EQihkvHkxV8UAE9E3m24gpKfugwzlDDstmn77OnDngCOBkZvJk.ttf\",\"createDate\":\"20200115 10:07:27\"},{\"id\":47,\"userId\":1,\"categoryId\":-1,\"name\":\"FandolKai\",\"nameCN\":\"FandolKai\",\"relativePath\":\"font/202001_1_MuigDEszmKEYXrcxVxl5mRQbqQxM6ZApiu3atXJj4yWZF3t0SA.otf\",\"createDate\":\"20200114 14:32:48\"},{\"id\":48,\"userId\":1,\"categoryId\":-1,\"name\":\"Genheiti\",\"nameCN\":\"%E6%80%9D%E6%BA%90%E7%9C%9F%E9%BB%91%E4%BD%93\",\"relativePath\":\"font/202001_1_XYN0aJG4uJHmN6kwNBnNcDulKxzGN4QdFOcsmqtNbPuR6DUWKq.ttf\",\"createDate\":\"20200114 14:48:36\"},{\"id\":59,\"userId\":1,\"categoryId\":-1,\"name\":\"OPPOSans\",\"nameCN\":\"OPPOSans\",\"relativePath\":\"font/202001_1_eXoPAnyUUEHRF2oWEpqZX5v6nDQj5u9lx3LUTr1bbvPn5QDw85.ttf\",\"createDate\":\"20200115 11:23:02\"},{\"id\":51,\"userId\":1,\"categoryId\":-1,\"name\":\"anzhuobiaozhun\",\"nameCN\":\"%E5%AE%89%E5%8D%93%E6%A0%87%E5%87%86%E4%B8%AD%E6%96%87\",\"relativePath\":\"font/202001_1_9C3pXVIG5zGTfZWNRseYB3pUMzYWHipBfQPpszom7KrEQy0WJy.TTF\",\"createDate\":\"20200115 10:17:13\"},{\"id\":52,\"userId\":1,\"categoryId\":-1,\"name\":\"pangmenzhengdao\",\"nameCN\":\"%E5%BA%9E%E9%97%A8%E6%AD%A3%E9%81%93%E6%A0%87%E9%A2%98%E4%BD%93\",\"relativePath\":\"font/202001_1_dCwaeEh65ryPps85oEGXpzIjguUBm3SbTtr0rnlBIyEVJKB0Bu.TTF\",\"createDate\":\"20200115 10:19:05\"},{\"id\":53,\"userId\":1,\"categoryId\":-1,\"name\":\"wenquanyi\",\"nameCN\":\"%E6%96%87%E6%B3%89%E9%A9%BF%E6%AD%A3%E9%BB%91\",\"relativePath\":\"font/202001_1_72qNjI4MsU9eJgJJtK8QUPzH9vWJTezUG4NA3PhKnjwuwFtPfB.TTF\",\"createDate\":\"20200115 10:20:16\"},{\"id\":61,\"userId\":1,\"categoryId\":-1,\"name\":\"ZKGaoDuanHei\",\"nameCN\":\"%E7%AB%99%E9%85%B7%E9%AB%98%E7%AB%AF%E9%BB%91\",\"relativePath\":\"font/202001_1_CSHlmWyuQWTvGWiVTNEYfNChyRygvRsqUvRB2IRF9TzLGyBXgJ.ttf\",\"createDate\":\"20200115 11:25:17\"},{\"id\":62,\"userId\":1,\"categoryId\":-1,\"name\":\"ZKKuHei\",\"nameCN\":\"%E7%AB%99%E9%85%B7%E9%85%B7%E9%BB%91%E4%BD%93\",\"relativePath\":\"font/202001_1_zGRlFu1ODAidhFDwEXU6kvU4bg0GA0emMaXwNU4M4t6O0JHkVV.ttf\",\"createDate\":\"20200115 11:27:50\"},{\"id\":63,\"userId\":1,\"categoryId\":-1,\"name\":\"ZKKuaile\",\"nameCN\":\"%E7%AB%99%E9%85%B7%E5%BF%AB%E4%B9%90%E4%BD%93\",\"relativePath\":\"font/202001_1_u846vrMLH6hXXEQOut3OxEmWpJrCSsMMJdzFTMsH64Um5ecHjw.ttf\",\"createDate\":\"20200115 11:28:25\"},{\"id\":64,\"userId\":1,\"categoryId\":-1,\"name\":\"ZKHuangYou\",\"nameCN\":\"%E7%AB%99%E9%85%B7%E5%BA%86%E7%A7%91%E9%BB%84%E6%B2%B9%E4%BD%93\",\"relativePath\":\"font/202001_1_ENZRXb5QjtJyw5BaBISlEj6O5PBtbEgiC70L3HmuG1UI4jRMaA.ttf\",\"createDate\":\"20200115 11:29:35\"},{\"id\":65,\"userId\":1,\"categoryId\":-1,\"name\":\"ZKWenYi\",\"nameCN\":\"%E7%AB%99%E9%85%B7%E6%96%87%E8%89%BA%E4%BD%93\",\"relativePath\":\"font/202001_1_0BQzKdhJmHAVP2nk5kS78Yefjb4ijk8nqD17814Hh8FgSCGLqw.ttf\",\"createDate\":\"20200115 11:30:10\"},{\"id\":67,\"userId\":1,\"categoryId\":-1,\"name\":\"yuanjiemingchao\",\"nameCN\":\"%E6%BA%90%E7%95%8C%E6%98%8E%E6%9C%9D\",\"relativePath\":\"font/202001_1_fZe6EXcPCZQAvGcnN6ChwYfuJrUqGynZlDhc2CT5cHPTOTSpUV.ttf\",\"createDate\":\"20200115 13:00:10\"},{\"id\":68,\"userId\":1,\"categoryId\":-1,\"name\":\"quanzi\",\"nameCN\":\"%E5%85%A8%E5%AD%97%E5%AD%97%E4%BD%93\",\"relativePath\":\"font/202001_1_uq3I0jCUTkPoSLVGNAa9yiQHLtIMqUlPOsP735bxEKFNLDo76j.ttf\",\"createDate\":\"20200115 13:06:12\"},{\"id\":69,\"userId\":1,\"categoryId\":-1,\"name\":\"FZFangSongFanTi\",\"nameCN\":\"%E6%96%B9%E6%AD%A3%E4%BB%BF%E5%AE%8B%E7%B9%81%E4%BD%93\",\"relativePath\":\"font/202001_1_z5aT4XvhSLxBwSU1s0OaG2RhCqJ7jEpGkGOKAQlI9ATswwh0Lm.ttf\",\"createDate\":\"20200115 13:15:50\"},{\"id\":70,\"userId\":1,\"categoryId\":-1,\"name\":\"FZFangSongJianTi\",\"nameCN\":\"%E6%96%B9%E6%AD%A3%E4%BB%BF%E5%AE%8B%E7%AE%80%E4%BD%93\",\"relativePath\":\"font/202001_1_5N38wgHZr4v1x1fZkvQYd3zHtFwLLEf94jHnPzNYT175CietMe.TTF\",\"createDate\":\"20200115 13:16:51\"},{\"id\":72,\"userId\":1,\"categoryId\":-1,\"name\":\"FZHeiTiJianTi\",\"nameCN\":\"%E6%96%B9%E6%AD%A3%E9%BB%91%E4%BD%93%E7%AE%80%E4%BD%93\",\"relativePath\":\"font/202001_1_zAVLAszmxDyfh4EnUX7OxauNVW58pfRB9q3TUneHe3QSybsuDn.TTF\",\"createDate\":\"20200115 13:18:28\"}]}";
        JSONObject jsonObject = JSON.parseObject(jsont);
        JSONArray array = jsonObject.getJSONArray("data");
        for (int i = 0; i < array.size(); i++) {
            try {
                JSONObject inside = array.getJSONObject(i);
                String nameCN = URLDecoder.decode(inside.getString("nameCN"),"utf-8");
                String namePinYin = PinyinUtils.getPingYin(nameCN);
                String pathName = inside.getString("relativePath").split("/")[1];
                if(collect.containsKey(pathName)){
                    String local = fontSave + "\\" + namePinYin + "." + pathName.split("\\.")[1];
                    org.apache.commons.io.FileUtils.copyFile(collect.get(pathName),new File(local));
                }
            } catch (Exception ignore) {
            }
        }
    }

    public static void transVrbtCrbt(String[] args) {
        String desktop = "C:\\Users\\wulifu\\Desktop\\";
        String data = "C:\\Users\\wulifu\\Desktop\\tem\\test\\word.data";
        String vrbt = "C:\\Users\\wulifu\\Desktop\\tem\\test\\vrbt.data";
        String crbt = "C:\\Users\\wulifu\\Desktop\\tem\\test\\crbt.data";
        String r = "C:\\Users\\wulifu\\Desktop\\tem\\test\\res.data";
        System.out.println("testBegin");

        List<String> d = FileUtils.readFromFile(data);
        List<String> v = FileUtils.readFromFile(vrbt);
        List<String> c = FileUtils.readFromFile(crbt);
        System.out.println("test");
        Map<String, String[]> collect = d.stream().collect(Collectors.toMap(a -> a.split(",")[1], a -> a.split(",")));
        ArrayList<String> res = new ArrayList<>();
        v.stream().map(s -> s.split("\\|")).forEach(split -> res.add("" + (res.size() + 1) + "|" + collect.get(split[2])[0] + "|vrbt|" + split[0] + "|"));
        c.stream().map(vt -> vt.split("\\|")).forEach(split -> res.add("" + (res.size() + 1) + "|" + collect.get(split[2])[0] + "|crbt|" + split[0] + "|"));

        System.out.println("");
        FileUtils.writeToFile(res, r);
    }

    public static void tes() {
        String desktop = "C:\\Users\\wulifu\\Desktop\\";
        String imgParent = desktop + "images\\images\\";
        String data = "C:\\Users\\wulifu\\Desktop\\tem\\test\\word.data";
        List<String> d = FileUtils.readFromFile(data);
        for (String tem : d) {
            String name = tem.split(",")[2];
            File file = new File(imgParent + name + ".jpg");
            System.out.println(file.exists() + " name " + name);
        }
    }

}

