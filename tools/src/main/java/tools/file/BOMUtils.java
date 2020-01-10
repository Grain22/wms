package tools.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author wulifu
 */
public class BOMUtils {

    static String examplefilepath ="D:/code/idea/ebupt/ebupt/liantong/vrbt-openinterface/1.3.1/src/main/java/com/ebupt/vrbt/OpenInterface/pojo/UserDepot.java";

    public static void removeBom(String filepath){
        try {
            if (!filepath.contains("java")) {
                return;
            }
            File file = new File(filepath);
            if(!file.isFile()) {
                return;
            }
            byte[] fileBytes = org.apache.commons.io.FileUtils.readFileToByteArray(file);
            if (fileBytes[0] == -17 && fileBytes[1] == -69 && fileBytes[2] == -65) {
                byte[] nbs = new byte[fileBytes.length - 3];
                System.arraycopy(fileBytes,3,nbs,0,nbs.length);
                org.apache.commons.io.FileUtils.writeByteArrayToFile(file,nbs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String parent = "D:/code/idea/ebupt/ebupt/liantong/vrbt-openinterface";
        ArrayList<String> allFilePaths = FileUtils.getAllFilePaths(new File(parent), new ArrayList<>());
        System.out.println(allFilePaths);
        allFilePaths.forEach(a->removeBom(a));
    }
}
