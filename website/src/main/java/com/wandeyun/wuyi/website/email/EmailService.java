package com.wandeyun.wuyi.website.email;

public interface EmailService {

    //发送简单邮件
    void sendSimpleMail(String to, String subject, String content);

    //发送html邮件
    void sendHtmlMail(String to, String subject, String content);

    //发送带附件的邮件
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    //发送带静态资源的邮件
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
