package com.wandeyun.wuyi.website.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine  templateEngine;



    @Test
    public void sendSimpleMail() throws Exception{
        emailService.sendSimpleMail("2644794564@qq.com","时光网"," 嗨，你好45555啊，我的天哪，我是大大大");
    }

    @Test
    public void sendHtmlMailTest()throws Exception{
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "<a>万德有惊喜哦</a>\n"+
                "<button >登录</button>\n"+
                "</body>\n" +
                "</html>";
        emailService.sendHtmlMail("15297841949@163.com","交个朋友",content);
    }

    @Test
    public  void sendAttachmentsMail()throws  Exception{
        String filePath="C:\\Users\\Administrator\\Pictures\\QQ浏览器截图\\20180730160459.png";
        emailService.sendAttachmentsMail("15297841949@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail()throws  Exception{
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Administrator\\Pictures\\QQ浏览器截图\\20180730160459.png";
//673123088
        emailService.sendInlineResourceMail("673123088@qq.com", "主题：快来找我玩", content, imgPath, rscId);

    }

    //使用thymeleaf模板引擎编写邮件样式模板发送
    @Test
    public void sendTemplateMail()throws  Exception{
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "2"); //容器导入模板传入要渲染的参数
        String emailContent = templateEngine.process("email", context);  //绑定模板email.html  和参数容器 context

        emailService.sendHtmlMail("15297841949@163.com","主题：这是模板邮件",emailContent);
    }




}
