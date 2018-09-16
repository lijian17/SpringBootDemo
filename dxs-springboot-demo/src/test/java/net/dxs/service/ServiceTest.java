package net.dxs.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Resource
	MailService mailService;
	
	@Test
	public void sayHelloTest() {
		mailService.sayHello();
	}
	
	@Test
	public void sendSimpleMailTest() {
		mailService.sendSimpleMail("lijian_17@163.com", "这是第一封邮件", "搭建好，这是我的第一封邮件");
	}
}
