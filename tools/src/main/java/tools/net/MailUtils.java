package tools.net;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.Data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * @author laowu
 */
public class MailUtils {

    static String account = "wulifu@ebupt.com";
    static String password = "wt7sSCnoZAMuar2g";
    static String sender = "wulifu@ebupt.com";
    static String receiver = "laowu875054886@live.com";
    static String alias = "wulifu";
    static String subject = "测试";
    static String content = "测试消息";

    public static void main(String[] args) throws Exception {
        Session session = Session.getDefaultInstance(setTencentExEmail(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        PasswordAuthentication pa = new PasswordAuthentication(account, password);
                        return pa;
                    }
                });
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sender, alias));
        mimeMessage.addRecipient(Message
                        .RecipientType
                        .TO,
                new InternetAddress(
                        receiver
                ));

        //设置主题
        mimeMessage.setSubject(subject);
        mimeMessage.setSentDate(new Date());
        //设置内容
        mimeMessage.setText(content);
        mimeMessage.saveChanges();
        //发送
        Transport.send(mimeMessage);
    }


    static String MAIL_TRANSPORT_PROTOCOL = "smtp";
    static String MAIL_SMTP_HOST = "smtp.exmail.qq.com";
    static String MAIL_SMTP_PORT = "465";
    static String MAIL_SMTP_AUTH = "true";

    public static Properties setTencentExEmail() {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        //服务器
        prop.setProperty("mail.smtp.host", MAIL_SMTP_HOST);
        //端口
        prop.setProperty("mail.smtp.port", MAIL_SMTP_PORT);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH);
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        return prop;
    }
}
