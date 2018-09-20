package net.dxs.service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Resource
	MailService mailService;

	@Resource
	TemplateEngine templateEngine;

	@Test
	public void sayHelloTest() {
		mailService.sayHello();
	}

	@Test
	public void sendSimpleMailTest() {
		mailService.sendSimpleMail("lijian_17@163.com", "这是第一封邮件", "搭建好，这是我的第一封邮件");
	}

	@Test
	public void sendHtmlMailTest() throws MessagingException {
		String content = "<html><body><h3>hello world，这是一封html邮件<h3></body></html>";
		mailService.sendHtmlMail("lijian_17@163.com", "这是一封html邮件", content);
	}

	@Test
	public void sendAttachmentsMailTest() throws MessagingException {
		String filePath = "C:\\Users\\lijian\\Desktop\\Java正则表达式Pattern和Matcher详解.java";
		mailService.sendAttachmentsMail("lijian_17@163.com", "这是一封带附件的邮件", "这是一封带附件的邮件正文", filePath);
	}

	@Test
	public void sendInlinResourceMailTest() {
		String imgPath = "C:\\Users\\lijian\\Desktop\\1.png";
		String rscId = "img001";
		String content = "<html><body>这是带图片的邮件：<img src=\'cid:" + rscId + "\' /><img src='cid:" + rscId
				+ "' /></body></html>";
		mailService.sendInlinResourceMail("lijian_17@163.com", "这是一封带图片的邮件", content, imgPath, rscId);
	}

	@Test
	public void testTemplateMailTest() throws MessagingException {
		Context context = new Context();
		context.setVariable("id", "007");

		String emailContent = templateEngine.process("thymeleaf/emailTemplate", context);

		mailService.sendHtmlMail("lijian_17@163.com", "这是一封模板引擎邮件", emailContent);
	}
}
