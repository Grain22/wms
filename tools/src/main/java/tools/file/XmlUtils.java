package tools.file;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author laowu
 * @version 6/3/2019 4:18 PM
 */
public class XmlUtils extends FileUtils {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream inputStream = FileUtils.getResource("webconfig.xml");
            Document document = db.parse(inputStream);
            System.out.println("fdasfdas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
