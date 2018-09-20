package net.dxs.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender mailSender;

	public void sayHello() {
		System.out.println("Hello World");
	}

	/**
	 * 发送简单邮件
	 * 
	 * @param to      发送给
	 * @param subject 发送主题
	 * @param content 发送正文
	 */
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(from);

		mailSender.send(message);
	}

	/**
	 * 发送HTML邮件
	 * 
	 * @param to      发送给
	 * @param subject 发送主题
	 * @param content 发送正文
	 * @throws MessagingException
	 */
	public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.setFrom(from);

		mailSender.send(message);
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param to       发送给
	 * @param subject  发送主题
	 * @param content  发送正文
	 * @param filePath 附件路径
	 * @throws MessagingException
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.setFrom(from);

		FileSystemResource file = new FileSystemResource(new File(filePath));
		String filename = file.getFilename();
		helper.addAttachment(filename, file);
		helper.addAttachment(filename + "_test", file);

		mailSender.send(message);
	}

	/**
	 * 发送带静态图片的邮件
	 * 
	 * @param to      发送给
	 * @param subject 发送主题
	 * @param content 发送正文
	 * @param rscPath 图片路径
	 * @param rscId   图片ID
	 */
	public void sendInlinResourceMail(String to, String subject, String content, String rscPath, String rscId) {

		logger.info("发送静态图片邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			helper.setFrom(from);

			FileSystemResource file = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, file);
			helper.addInline(rscId, file);
			logger.info("发送静态图片邮件成功！");
		} catch (MessagingException e) {
			logger.error("发送静态图片邮件失败：", e);
		}

		mailSender.send(message);
	}

}
