import tools.bean.DateUtils;
import tools.file.FileUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class Test {

    public static void main(String[] args) {
        String data = "C:\\Users\\wulifu\\Desktop\\tem\\test\\word.data";
        String vrbt = "C:\\Users\\wulifu\\Desktop\\tem\\test\\vrbt.data";
        String crbt = "C:\\Users\\wulifu\\Desktop\\tem\\test\\crbt.data";
        System.out.println("testBegin");

        List<String> d = FileUtils.readFromFile(data);
        List<String> v = FileUtils.readFromFile(vrbt);
        List<String> c = FileUtils.readFromFile(crbt);
        System.out.println("test");
        Map<String, String[]> collect = d.stream().collect(Collectors.toMap(a -> a.split(",")[1], a -> a.split(",")));
        ArrayList<String> res = new ArrayList<>();
        v.stream().map(s -> s.split("\\|")).forEach(split -> res.add("" + (res.size()+1) + "|" + collect.get(split[2])[0] + "|vrbt|" + split[0]+"|"));
        v.stream().map(vt -> vt.split("\\|")).forEach(split -> res.add("" + (res.size()+1) + "|" + collect.get(split[2])[0] + "|crbt|" + split[0]+"|"));
        FileUtils.writeToFile(d.stream().map(a->""+(a.split(",")[0])+"|"+(a.split(",")[2])+"|"+ DateUtils.formatDate(new Date(),"yyyyMMDDHHmmss")+"|").collect(Collectors.toList()), "C:\\Users\\wulifu\\Desktop\\tem\\test","vrbt_year_keyword.data");
    }

}

