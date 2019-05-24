import java.io.File;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class test {
    public static void main(String[] args) {
        String path = "C:\\Users\\laowu\\IdeaProjects\\wms\\gservletweb\\target\\gservletweb\\test.html";
        File file = new File(path);
        System.out.println(file.length());
    }
}
