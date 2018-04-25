package test;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

/**
 */
public class Test {
    public static void main2(String[] args) {
        try {
            String username = "123456@163.com";
            String password = "******";
            username = "634790417@qq.com";
            password = "******";
            new ReceiveMailHandler().receiveMail(username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");

        //以下步骤跟一般的JavaMail操作相同
        Session session = Session.getDefaultInstance(props, null);

        //请将红色部分对应替换成你的邮箱帐号和密码
        URLName urln = new URLName("pop3", "pop.qq.com", 995, null,
                "634790417@qq.com", "******");
        Store store = null;
        Folder inbox = null;
        try {
            store = session.getStore(urln);
            store.connect();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            Message[] messages = inbox.getMessages();
            inbox.fetch(messages, profile);
            System.out.println("收件箱的邮件数：" + messages.length);
            for (int i = 0; i < messages.length; i++) {
                //邮件发送者
                String from = decodeText(messages[i].getFrom()[0].toString());
                InternetAddress ia = new InternetAddress(from);
//                System.out.println("FROM:" + ia.getPersonal() + '(' + ia.getAddress() + ')');
                //邮件标题
                System.out.println("TITLE:" + messages[i].getSubject());
                //邮件大小
//                System.out.println("SIZE:" + messages[i].getSize());
                //邮件发送时间
//                System.out.println("DATE:" + messages[i].getSentDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inbox.close(false);
            } catch (Exception e) {
            }
            try {
                store.close();
            } catch (Exception e) {
            }
        }
    }

    protected static String decodeText(String text)
            throws UnsupportedEncodingException {
        if (text == null)
            return null;
        if (text.startsWith("=?GB") || text.startsWith("=?gb"))
            text = MimeUtility.decodeText(text);
        else
            text = new String(text.getBytes("ISO8859_1"));
        return text;
    }


    public static void main(String[] args) throws Exception {
        String user = "634790417@qq.com";// 邮箱的用户名
        String password = "******"; // 邮箱的密码

        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", "imap.qq.com");

        Session session = Session.getInstance(prop);

        int total = 0;
        IMAPStore store = (IMAPStore) session.getStore("imap"); // 使用imap会话机制，连接服务器
        store.connect(user, password);
        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
        folder.open(Folder.READ_WRITE);
        // 获取总邮件数
        total = folder.getMessageCount();
        System.out.println("-----------------共有邮件：" + total
                + " 封--------------");
        // 得到收件箱文件夹信息，获取邮件列表
        System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
        Message[] messages = folder.getMessages();
        int messageNumber = 0;
        for (Message message : messages) {
            System.out.println("发送时间：" + message.getSentDate());
            System.out.println("主题：" + message.getSubject());
            System.out.println("内容：" + message.getContent());
            Flags flags = message.getFlags();
            if (flags.contains(Flags.Flag.SEEN))
                System.out.println("这是一封已读邮件");
            else {
                System.out.println("未读邮件");
            }
            System.out.println("========================================================");
            System.out.println("========================================================");
            //每封邮件都有一个MessageNumber，可以通过邮件的MessageNumber在收件箱里面取得该邮件
            messageNumber = message.getMessageNumber();
        }
        Message message = folder.getMessage(messageNumber);
        System.out.println(message.getContent() + message.getContentType());
        // 释放资源
        if (folder != null)
            folder.close(true);
        if (store != null)
            store.close();

    }
}