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
                    /** 5/13/2019   webClient.setWebConnection(
                     new WebConnectionWrapper(webClient) {
                    @Override public WebResponse getResponse(WebRequest request) throws IOException {
                    WebResponse response = super.getResponse(request);
                    if (request.getUrl().toExternalForm().contains("GBK")) {
                    String content = response.getContentAsString(Charset.forName("GBK"));
                    WebResponseData data = new WebResponseData(content.getBytes("UTF-8"),
                    response.getStatusCode(), response.getStatusMessage(), response.getResponseHeaders());
                    response = new WebResponse(data, request, response.getLoadTime());
                    }
                    return response;
                    }
                    }
                     );*/

                }
            }
        }
        return webClient;
    }

}
