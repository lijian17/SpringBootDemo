package net.dxs.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

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

}
