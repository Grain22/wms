package analysis;

import entity.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * @author laowu
 * @version 4/11/2019 6:58 PM
 */
public class AnalysisPage {

    public static ArrayList<String> searchPage(String page) {
        ArrayList<String> results = new ArrayList<>();
        Document doc = Jsoup.parse(page);
        return results;
    }

    public static String findNextPageUrl(String loadedPage) {
        Document doc = Jsoup.parse(loadedPage);
        return null;
    }

    public static Goods getData(ArrayList<String> pages) {
        Goods good = new Goods();
        Document doc = Jsoup.parse(pages.get(0));
        return good;
    }
}
