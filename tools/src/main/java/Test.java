import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import tools.ftp.sftp.SftpSession;

import java.io.*;

/**
 * @author laowu
 * @version 5/10/2019 12:06 PM
 */
public class Test {
    public static void main(String[] args) throws IOException, JSchException {
        String path = "C:\\Users\\wulifu\\Downloads\\data.out";
        SftpSession sftpSession = new SftpSession("10.1.63.26", "19222", "gansu", "1qaz@WSX");
        ChannelSftp channel1 = sftpSession.getChannel();
        new Thread(() -> {
            try {
                channel1.cd("/home/gansu/grain");
                File file = new File(path);
                System.out.println("begin 1");
                channel1.put(new FileInputStream(file), "temfile001.out", ChannelSftp.OVERWRITE);
                System.out.println("end 1");
                channel1.disconnect();
            } catch (SftpException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
        ChannelSftp channel2 = sftpSession.getChannel();
        new Thread(() -> {
            try {
                channel2.cd("/home/gansu/grain");
                File file = new File(path);
                System.out.println("begin 21");
                channel2.put(new FileInputStream(file), "temfile002.out", ChannelSftp.OVERWRITE);
                System.out.println("end 12");
                channel2.disconnect();
            } catch (SftpException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
        ChannelSftp channel3 = sftpSession.getChannel();
        new Thread(() -> {
            try {
                channel3.cd("/home/gansu/grain");
                File file = new File(path);
                System.out.println("begin 13");
                channel3.put(new FileInputStream(file), "temfile003.out", ChannelSftp.OVERWRITE);
                System.out.println("end 13");
                channel3.disconnect();
            } catch (SftpException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("");
    }
}