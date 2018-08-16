package net.dxs.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.dxs.pojo.User;

@Controller
public class UserController {

	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser() {
		User u = new User();
		u.setName("lijian");
		u.setPassword("123456");
		u.setAge(18);
		u.setBirthday(new Date());
		u.setDesc(null);
		return u;
	}

}
