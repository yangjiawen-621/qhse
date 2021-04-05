package com.wlhse.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SendEmails {

    public SendEmails() {
        // TODO Auto-generated constructor stub
    }

    /**
     * enable:是否校验（或者授权）：true
     * debug：是否打印控制台消息
     * sendAddress:发件人地址
     * to：邮件接收者地址
     * subject:邮件主题
     * context：邮件内容
     * path:文本路径
     * owner:自己的邮箱账号
     * ownerStmp:自己邮箱的stmp客户端授权码

     */
    public void sendEmails(String enable, String debug, String owner, String to, String subject, String context,
                           String path, String ownerStmp) throws UnsupportedEncodingException, javax.mail.MessagingException {

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.163.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", enable);// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", debug);// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(owner));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(to) });
        // 设置邮件标题
        message.setSubject(subject);

        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();

        // 消息
        messageBodyPart.setText(context);

        // 创建多重消息
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);
        // 附件部分
        messageBodyPart = new MimeBodyPart();
        // 设置要发送附件的文件路径
        DataSource source = new FileDataSource(path);
        messageBodyPart.setDataHandler(new DataHandler(source));
        //设置当前发送邮件时间
        message.setSentDate(new Date());
        // messageBodyPart.setFileName(filename);
        // 处理附件名称中文（附带文件路径）乱码问题
        messageBodyPart.setFileName(MimeUtility.encodeText(path));
        multipart.addBodyPart(messageBodyPart);
        // 发送完整消息
        message.setContent(multipart);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect(owner, ownerStmp);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}