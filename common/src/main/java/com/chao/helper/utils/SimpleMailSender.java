package com.chao.helper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by think on 2017/1/16.
 */
public class SimpleMailSender {

    /**
     * 日志打印
     */
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMailSender.class);

    private MimeMessage message;
    private Session session;
    private Transport transport;

    private String mailHost = "";
    private String sender_username = "";
    private String sender_password = "";

    private Properties properties = new Properties();

    /*
     * 初始化方法
     */
    public SimpleMailSender(boolean debug) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
//		try {
//			properties.load(in);
//			this.mailHost = properties.getProperty("mail.smtp.host");
//			this.sender_username = properties.getProperty("mail.sender.username");
//			this.sender_password = properties.getProperty("mail.sender.password");
//		} catch (IOException e) {
//			LOG.error("读取配置文件异常：" + e);
//		}
        this.mailHost = "mail.s-shell.com";
        this.sender_username = "zhangge@s-shell.com";
        this.sender_password = "z123456";
        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     *
     * @param subject
     *            邮件主题
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址，多个使用;分隔
     * @param attachment
     *            附件
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) throws MessagingException, UnsupportedEncodingException {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(MimeUtility.encodeText("北京银贝壳支付") + "<" + sender_username + ">");
            message.setFrom(from);

            // 收件人
            String[] split = receiveUser.split(";");
            //InternetAddress[] to = new InternetAddress[split.length];
            Address[] adds = new InternetAddress[split.length];
            Address add = null;
            for(int i = 0; i < adds.length; i++){
                add = new InternetAddress(split[i]);
                adds[i] = add;
            }

            message.setRecipients(Message.RecipientType.TO, adds);
            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                // messageBodyPart.setFileName("=?GBK?B?" +
                // enc.encode(attachment.getName().getBytes()) + "?=");

                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
        }  finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleMailSender se = new SimpleMailSender(true);
        // File affix = new File("c:\\测试-test.txt");
        //File affix = new File("/Users/macadmin/Downloads/mail_tool/bin/test/Test.class");
        try {
            se.doSendHtmlEmail("邮件主题", "邮件内容", "545824821@qq.com; zhangge@s-shell.com", null);
        } catch (UnsupportedEncodingException | MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
