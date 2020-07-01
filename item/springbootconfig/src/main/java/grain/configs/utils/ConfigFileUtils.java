package grain.configs.utils;

import grain.Application;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wulifu
 */
@Slf4j
public class ConfigFileUtils {

    private List<File> getConfigFiles(@NotBlank String prefix, @NotBlank String suffix) {
        List<File> configFiles = new ArrayList<>();
        String dir = System.getProperty("user.dir");
        File file = new File(dir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileInside : files) {
                if (fileInside.getName().startsWith(prefix) && fileInside.getName().endsWith(suffix)) {
                    configFiles.add(fileInside);
                }
            }
        }
        if (configFiles.isEmpty()) {
            log.info("未在项目目录加载到数据库配置文件,在项目包内部进行检索");

        } else {
            log.info("加载到 {} 个 数据库配置文件", configFiles.size());
        }
        return configFiles;
    }

    public static void main(String[] args) {
        URL resource = Application.class.getResource("/");
        System.out.println("");
    }
}
