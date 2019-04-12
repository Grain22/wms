import java.util.LinkedList;
import java.util.List;

/**
 * @author laowu
 * @version 4/11/2019 6:33 PM
 * the web crawler
 */
public class Crawler {
    private static List<String> task = new LinkedList<>();

    public static void main(String[] args) {
        initial();
        doCrawler();
    }

    private static void initial() {
        task.add("first");
    }

    private static void doCrawler() {
        System.out.println(task.stream().findFirst().get());
    }
}
