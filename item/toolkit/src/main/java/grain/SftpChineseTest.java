package grain;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import org.grain.tools.ftp.sftp.SftpSession;

import java.io.File;
import java.nio.file.Files;

/**
 * @author grain
 * sftp 中文名测试
 */
public class SftpChineseTest {
    public static void main(String[] args) throws Exception {
        SftpSession gansu = new SftpSession("10.1.63.26", "19222", "gansu", "1qaz@WSX");
        Session session = gansu.getSession();
        ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect();
        File file = new File("C:\\Users\\grain\\Desktop\\3.docx");
        if (file.exists()) {
            sftp.put(Files.newInputStream(file.toPath()), "./3.docx");
        }
    }
}
