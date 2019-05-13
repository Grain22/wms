package tools.net;

import tools.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLUtils {

    /**
     * download file from path
     * InputStream inputStream = url.openStream();
     * another way : get input stream directly
     *
     * @param from resource
     * @param to local path
     */
    public static void downloads(String from, String to) {
        try {
            URL url = new URL(from);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(30000);
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            byte[] data = bos.toByteArray();
            File save = FileUtils.getFile(to);
            FileOutputStream fileOutputStream = new FileOutputStream(save);
            fileOutputStream.write(data);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("file download fail :" + e.getMessage());

        }
    }

}
