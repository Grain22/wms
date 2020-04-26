import HtmlUnitClient.Client;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

/**
 * @author laowu
 * @version 4/11/2019 6:33 PM
 * the web crawler
 */
public class Crawler {

    public static void run(String[] args) throws IOException {
        WebClient client = Client.getClient();
        Page page = client.getPage("https://www.baidu.com");
        System.out.println(page);
    }

}
