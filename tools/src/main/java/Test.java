import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class Test {
    public static void main(String[] args) throws IOException {
        File fileParent = new File("C:\\Users\\wulifu\\Desktop\\tem\\cherset");
        File[] files = fileParent.listFiles();
        System.out.println("");
        for (File file : files) {
            System.out.println(file.exists());
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            System.out.println(file.exists());
            inputStreamReader.close();
            System.out.println(file.exists());
            System.out.println(file.exists());
        }
    }


}