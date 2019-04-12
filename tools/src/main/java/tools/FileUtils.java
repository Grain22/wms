package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.io.File.separator;

/**
 * @author laowu
 */
public class FileUtils {

    static CustomerLogger logger = CustomerLogger.getLogger(FileUtils.class);

    private static boolean append = true;

    private static final String def_path = separator + "temporary";
    private static final String def_file_name = DateUtils.getTimeString() + ".log";
    private static final String def_file = def_path + separator + def_file_name;

    public static void writeToFile(String string) {
        writeToFile(Collections.singletonList(string));
    }

    public static void writeToFile(List<String> strings) {
        writeToFile(strings, null);
    }

    /**
     * write to file
     *
     * @param strings
     * @param filePath path
     * @param fileName name
     */
    public static void writeToFile(List<String> strings, String filePath, String fileName) {
        writeToFile(strings, filePath + separator + fileName);
    }

    public static void writeToFile(List<String> strings, String pathWithName) {
        try {
            File file;
            if (Objects.isNull(pathWithName)) {
                pathWithName = def_file;
            }
            file = new File(pathWithName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, append);
            for (String tem : strings) {
                writer.write(tem + "\n");
            }
            writer.close();
            logger.log("write to file : " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read from file
     *
     * @param pathWithName
     * @return
     */
    private static List<String> readFromFile(String pathWithName) {
        String encoding = "utf8";
        return readFromFile(pathWithName, encoding);
    }

    private static List<String> readFromFile(String pathWithName, String encoding) {
        File file = new File(pathWithName);
        if (file.isFile() && file.exists()) {
            List<String> list = new ArrayList<>();
            try (InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding)) {
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        } else {
            throw new RuntimeException("找不到指定的文件");
        }
    }
}
