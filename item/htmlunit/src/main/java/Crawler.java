import HtmlUnitClient.Client;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import java.io.IOException;

/**
 * @author laowu
 * @version 4/11/2019 6:33 PM
 * the web crawler
 */
public class Crawler {

    public static void main(String[] args) throws IOException {
        run(args);
    }
    public static void run(String[] args) throws IOException {
        WebClient client = Client.getClient();
        HtmlPage page = client.getPage("http://10.1.1.7:8002/index.php?zone=cpzone");
        page.getElementByName("auth_user").setNodeValue("wulifu");
        ((HtmlTextInput)page.getElementByName("auth_user")).setValueAttribute("wulifu");
        ((HtmlPasswordInput)page.getElementByName("auth_pass")).setValueAttribute("8Uo9Z3Qs");
        page.getElementByName("auth_pass").setNodeValue("8Uo9Z3Qs");
        Page accept = page.getElementByName("accept").click();
        System.out.println(HtmlUnitClient.Page.getDom(((HtmlPage) accept).asText()));
        System.out.println(page);
    }

}
