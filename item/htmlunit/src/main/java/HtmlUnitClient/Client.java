package HtmlUnitClient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

import java.util.Objects;

/**
 * @author laowu
 * @version 5/13/2019 11:46 AM
 */
public class Client {

    private static WebClient webClient;

    public static WebClient getClient() {
        if (Objects.isNull(webClient)) {
            synchronized (webClient) {
                if (Objects.isNull(webClient)) {
                    webClient = new WebClient(BrowserVersion.CHROME);
                    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
                    webClient.setJavaScriptTimeout(100000);
                    webClient.waitForBackgroundJavaScript(600 * 1000);
                    WebClientOptions options = webClient.getOptions();
                    options.setCssEnabled(false);
                    options.setJavaScriptEnabled(true);
                    options.setActiveXNative(false);
                    options.setThrowExceptionOnScriptError(false);
                    options.setThrowExceptionOnFailingStatusCode(false);
                }
            }
        }
        return webClient;
    }

}
