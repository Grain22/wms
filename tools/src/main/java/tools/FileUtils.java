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
@SuppressWarnings("unused")
public class FileUtils {

    private static CustomerLogger logger = CustomerLogger.getLogger(FileUtils.class);

    private static boolean append = true;

    private static final String DEF_PATH = separator + "temporary";
    private static final String DEF_FILE_NAME = DateUtils.getTimeString() + ".log";
    private static final String DEF_FILE = DEF_PATH + separator + DEF_FILE_NAME;

    public static void writeToFile(String string) {
        writeToFile(Collections.singletonList(string));
    }

    private static void writeToFile(List<String> strings) {
        writeToFile(strings, null);
    }

    /**
     * write to file
     *
     * @param strings string s
     * @param filePath path
     * @param fileName name
     */
    public static void writeToFile(List<String> strings, String filePath, String fileName) {
        writeToFile(strings, filePath + separator + fileName);
    }

    private static void writeToFile(List<String> strings, String pathWithName) {
        try {
            File file;
            if (Objects.isNull(pathWithName)) {
                pathWithName = DEF_FILE;
            }
            file = new File(pathWithName);
            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
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
     * @param pathWithName path name
     * @return string list
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

    private static boolean deleteDir(File dir) {
        if (!dir.exists()) {
            return false;
        }
        if (dir.isDirectory()) {
            String[] childs = dir.list();
            if (childs != null) {
                for (String child : childs) {
                    boolean success = deleteDir(new File(dir, child));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        return dir.delete();
    }
}
