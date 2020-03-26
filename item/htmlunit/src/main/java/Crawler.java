import HtmlUnitClient.Client;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author laowu
 * @version 4/11/2019 6:33 PM
 * the web crawler
 */
public class Crawler {
    private static List<String> task = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        WebClient client = Client.getClient();
        Page page = client.getPage("https://www.baidu.com");
        System.out.println(page);
    }

}
