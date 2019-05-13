package HtmlUnitClient;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author laowu
 * @version 5/13/2019 12:13 PM
 */
public class Page {
    public static String getPage(String url) throws IOException {
        WebClient webClient = Client.getClient();
        HtmlPage page = webClient.getPage(url);
        return page.asXml();
    }

    public static Document getDom(String page) {
        Document parse = Jsoup.parse(page);
        return parse;
    }
}
